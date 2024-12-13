package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.exp.Exp;
import exception.MyException;

public class HeapWriting implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	String var_name;
	Exp exp;
	
	public HeapWriting() {}
	
	public HeapWriting(String var_name, Exp exp) {
		this.var_name = var_name;
		this.exp = exp;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		int address = symTable.lookup(var_name);
		int value = exp.eval(symTable, heap);
		if(heap.isDefined(address))
			heap.update(address, value);
		else
			throw new MyException("Invalid address!");
		return null;
	}
	
	@Override
	public String toString() {
		return "wH(" + var_name + ", " + exp.toString() + ")";
	}
}
