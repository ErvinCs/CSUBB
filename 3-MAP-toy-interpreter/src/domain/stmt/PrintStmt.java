package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.exp.Exp;
import exception.MyException;

public class PrintStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	Exp expression;
	
	public PrintStmt() {}
	
	public PrintStmt(Exp expression) {
		this.expression = expression;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIList<Integer> queue = state.getOut();
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		queue.add(expression.eval(symTable,heap));
		return null;
	}

	@Override
	public String toString() {
		return "print(" + expression.toString() + ")";
	}
	
	public Exp getExpression() {
		return this.expression;
	}
}
