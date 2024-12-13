package domain.stmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyTuple;
import domain.exp.Exp;
import exception.MyException;

public class ReadFile implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	Exp var_file_id; 
	String var_name;
	
	public ReadFile() {}
	
	public ReadFile(Exp var_file_id, String var_name) {
		this.var_file_id = var_file_id;
		this.var_name = var_name;
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
			int valueFromFile;
			String result = reader.readLine();
			if(result == null)
				valueFromFile = 0;
			else
				valueFromFile = Integer.parseInt(result);
			if(symTable.isDefined(var_name))
				symTable.update(var_name, valueFromFile);
			else 
				symTable.add(var_name, valueFromFile);
		} catch (IOException e) {
			throw new MyException("Cannot read from BufferedReader.");
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "readFile(" + var_file_id.toString() + ", " + var_name + ")";
	}

}
