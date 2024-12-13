package domain.stmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyTuple;
import domain.exp.Exp;
import exception.MyException;

public class CloseRFile implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	Exp var_file_id;
	
	public CloseRFile() {}
	
	public CloseRFile(Exp var_file_id) {
		this.var_file_id = var_file_id;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = state.getFileTable();
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		int unique_key = var_file_id.eval(symTable,heap);
		MyTuple<String, BufferedReader> tuple = fileTable.lookup(unique_key);
		BufferedReader reader = tuple.getSecond();
		try {
			reader.close();
			fileTable.remove(unique_key);
		} catch (IOException e) {
			throw new MyException("Cannot close BufferedReader.");
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "closeFile(" + var_file_id.toString() + ")";
	}
}
