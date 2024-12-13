package domain.stmt;

import java.io.Serializable;

import domain.PrgState;
import domain.adt.MyIStack;
import exception.MyException;

public class CompStmt implements IStmt, Serializable {
	private static final long serialVersionUID = 1L;
	IStmt first, second;
	
	public CompStmt() {}
	
	public CompStmt(IStmt first, IStmt second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stack = state.getExeStack();
		stack.push(second);
		stack.push(first);
		return null;
	}

	@Override
	public String toString() {
		return "(" + first.toString() + ";" + second.toString() + ")";
	}
	
	public IStmt getFirst() {
		return this.first;
	}
	
	public IStmt getSecond() {
		return this.second;
	}
}
