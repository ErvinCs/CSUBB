package domain.stmt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyTuple;
import exception.MyException;

public class OpenRFile implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	static int unique_key = -1;
	String var_file_id, filename;
	
	public OpenRFile() {}
	
	public OpenRFile(String var_file_id, String filename) {
		this.var_file_id = var_file_id;
		this.filename = filename;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = state.getFileTable();
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		for(HashMap.Entry<Integer, MyTuple<String, BufferedReader>> e : fileTable.getDictionary().entrySet()) {
				if(e.getValue().getFirst().equals(filename))
					throw new MyException("Filename already exists in FileTable.");
		}
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(filename));
			MyTuple<String, BufferedReader> tuple = new MyTuple<String, BufferedReader>(filename, buffer);
			unique_key++;
			fileTable.add(unique_key, tuple);
			if(symTable.isDefined(var_file_id))
				symTable.update(var_file_id, unique_key);
			else 
				symTable.add(var_file_id, unique_key);
		} catch (FileNotFoundException e1) {
			throw new MyException("Couldn't create the BufferedReader.");
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "openFile(" + var_file_id + ", " + filename + ")";
	}
}
