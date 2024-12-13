package domain.exp;

import domain.adt.MyIDictionary;
import exception.MyException;

public class HeapRead extends Exp {
	private static final long serialVersionUID = 1L;
	String var_name;
	
	public HeapRead() {}
	
	public HeapRead(String var_name) {
		this.var_name = var_name;
	}

	@Override
	public int eval(MyIDictionary<String, Integer> symTable, MyIDictionary<Integer, Integer> heap) throws MyException {
		int heapAddress = symTable.lookup(var_name);
		return heap.lookup(heapAddress);
	}

	@Override
	public String toString() {
		return "rH(" + var_name + ")";
	}

}
