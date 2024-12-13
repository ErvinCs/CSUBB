package domain.exp;

import domain.adt.MyIDictionary;
import exception.MyException;

public class BoolExp extends Exp {
	private static final long serialVersionUID = 1L;
	String op;
	Exp e1, e2;
	
	public BoolExp() {}
	
	public BoolExp(String op, Exp e1, Exp e2) {
		this.op = op;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public int eval(MyIDictionary<String,Integer> symTable, MyIDictionary<Integer,Integer> heap) throws MyException {
		 switch(op) {
		 case "<":
			 if(e1.eval(symTable, heap)<e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 case "<=":
			 if(e1.eval(symTable, heap)<=e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 case "==":
			 if(e1.eval(symTable, heap)==e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 case "!=":
			 if(e1.eval(symTable, heap)!=e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 case ">":
			 if(e1.eval(symTable, heap)>e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 case ">=":
			 if(e1.eval(symTable, heap)>=e2.eval(symTable, heap))
				 return 1;
			 else
				 return 0;
		 default:
			 throw new MyException("The provided operator string is not valid."); 
		 }
	}
	
	public String toString() {
		return e1.toString() + op + e2.toString();
	}
	
	public String getOp() {
		return this.op;
	}
	
	public Exp getFirst() {
		return this.e1;
	}
	
	public Exp getSecond() {
		return this.e2;
	}
}
