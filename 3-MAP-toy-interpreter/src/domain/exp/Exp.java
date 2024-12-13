package domain.exp;

import java.io.Serializable;

import domain.adt.MyIDictionary;
import exception.MyException;

public abstract class Exp implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract int eval(MyIDictionary<String,Integer> symTable, MyIDictionary<Integer,Integer> heap) throws MyException;
	public abstract String toString();
}