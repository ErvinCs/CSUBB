package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIStack;
import domain.exp.Exp;
import exception.MyException;

public class IfStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	Exp expression;
	IStmt thenS;
	IStmt elseS;
	
	public IfStmt() {}
	
	public IfStmt(Exp expression, IStmt thenS, IStmt elseS) {
		this.expression = expression;
		this.thenS = thenS;
		this.elseS = elseS;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stack = state.getExeStack();
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		if(expression.eval(symTable,heap)!=0)
			stack.push(thenS);
		else
			stack.push(elseS);
		return null;
	}
	
	@Override
	public String toString() { 
		return "IF("+ expression.toString()+ ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
	}
	
	public Exp getExpression() {
		return this.expression;
	}
	
	public IStmt getThenStmt() {
		return this.thenS;
	}
	
	public IStmt getElseStmt() {
		return this.elseS;
	}
}
