package domain.exp;

import domain.adt.MyIDictionary;
import exception.MyException;

public class VarExp extends Exp {
	private static final long serialVersionUID = 1L;
	String id;
	
	public VarExp() {}
	
	public VarExp(String id) {
		this.id = id;
	}
	
	public int eval(MyIDictionary<String,Integer> symTable, MyIDictionary<Integer,Integer> heap) throws MyException {
		 return symTable.lookup(id);
	}
	
	public String toString() {
		return id;
	}
	
	public String getId() {
		return this.id;
	}
}
