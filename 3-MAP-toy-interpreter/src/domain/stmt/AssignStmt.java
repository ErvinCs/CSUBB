package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.exp.*;
import exception.MyException;

public class AssignStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	String id;
	Exp expression;
	
	public AssignStmt() {}
	
	public AssignStmt(String id, Exp expression) {
		this.id = id;
		this.expression = expression;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		int val = expression.eval(symTable,heap);
		if(symTable.isDefined(id))
			symTable.update(id,val);
		else 
			symTable.add(id,val);
		return null;
	}
	
	@Override
	public String toString() {
		 return id + "=" + expression.toString();
	}
	
	public String getId() {
		return this.id;
	}
	
	public Exp getExpression() {
		return this.expression;
	}
}
