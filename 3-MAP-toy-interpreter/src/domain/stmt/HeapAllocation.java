package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.exp.Exp;
import exception.MyException;

public class HeapAllocation implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	static int unique_key = 0;
	String var_name;
	Exp exp;
	
	public HeapAllocation() {}
	
	public HeapAllocation(String var_name, Exp exp) {
		this.var_name = var_name;
		this.exp = exp;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		unique_key++;
		heap.add(unique_key, exp.eval(symTable,heap));
		if(symTable.isDefined(var_name))
			symTable.update(var_name, unique_key);
		else 
			symTable.add(var_name, unique_key);
		return null;
	}
	
	@Override
	public String toString() {
		return "new(" + var_name + ", " + exp.toString() + ")";
	}
}
