package domain.exp;

import domain.adt.MyIDictionary;
import exception.MyException;

public class ArithExp extends Exp {
	private static final long serialVersionUID = 1L;
	char op;
	Exp e1, e2;
	
	public ArithExp() {}
	
	public ArithExp(char op, Exp e1, Exp e2) {
		this.op = op;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public int eval(MyIDictionary<String,Integer> symTable, MyIDictionary<Integer,Integer> heap) throws MyException {
		 if (op == '+') return (e1.eval(symTable, heap)+e2.eval(symTable, heap));
		 if (op == '-') return (e1.eval(symTable, heap)-e2.eval(symTable, heap));
		 if (op == '*') return (e1.eval(symTable, heap)*e2.eval(symTable, heap));
		 if (op == '/' && e2.eval(symTable, heap)!=0)
			 return (e1.eval(symTable, heap)/e2.eval(symTable, heap));
		 else
			 if(e2.eval(symTable, heap)==0)
				 throw new MyException("You cannot divide by 0!");
		 throw new MyException("The provided operator is not valid.");
	}
	
	public String toString() {
		return e1.toString() + op + e2.toString();
	}
	
	public char getOp() {
		return this.op;
	}
	
	public Exp getFirst() {
		return this.e1;
	}
	
	public Exp getSecond() {
		return this.e2;
	}
}
