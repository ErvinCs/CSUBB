package domain.stmt;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.HashMap;

import domain.PrgState;
import domain.adt.MyDictionary;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyStack;
import domain.adt.MyTuple;
import exception.MyException;

public class ForkStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	IStmt statement;
	
	public ForkStmt() {}
	
	public ForkStmt(IStmt statement) {
		this.statement = statement;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> exeStack = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
    	HashMap<String, Integer> currentSymTable = state.getSymTable().getDictionary();
    	for(String key : currentSymTable.keySet()) {
    		symTable.add(key, currentSymTable.get(key));
    	}
    	MyIList<Integer> out = state.getOut();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = state.getFileTable();
    	MyIDictionary<Integer, Integer> heap = state.getHeap();
    	PrgState newPrgState = new PrgState(exeStack, symTable, out, fileTable, heap, statement);
		return newPrgState;
	}
	
	@Override
	public String toString() { 
		return "Fork("+ statement.toString() + ")";
	}
	
	public IStmt getStatement() {
		return this.statement;
	}
}
