package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIStack;
import domain.exp.Exp;
import exception.MyException;

public class WhileStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	Exp expression;
	IStmt statement;
	
	public WhileStmt() {}
	
	public WhileStmt(Exp expression, IStmt statement) {
		this.expression = expression;
		this.statement = statement;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stack = state.getExeStack();
		MyIDictionary<String,Integer> symTable = state.getSymTable();
		MyIDictionary<Integer,Integer> heap = state.getHeap();
		if(expression.eval(symTable,heap)!=0) {
			stack.push(this);
			stack.push(statement);
		}
		return null;
	}
	
	@Override
	public String toString() { 
		return "WHILE("+ expression.toString()+ "){" + statement.toString() + "}";
	}
	
	public Exp getExpression() {
		return this.expression;
	}
	
	public IStmt getStatement() {
		return this.statement;
	}
}