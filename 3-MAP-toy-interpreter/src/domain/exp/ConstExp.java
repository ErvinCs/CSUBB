package domain.exp;

import domain.adt.MyIDictionary;

public class ConstExp extends Exp {
	private static final long serialVersionUID = 1L;
	int number;
	
	public ConstExp() {}
	
	public ConstExp(int number) {
		this.number = number;
	}
	
	public int eval(MyIDictionary<String,Integer> symTable, MyIDictionary<Integer,Integer> heap) {
		 return number;
	}
	
	public String toString() {
		return Integer.toString(number);
	}
	
	public int getNumber() {
		return this.number;
	}
}
