package domain.adt;

import java.util.Stack;

public interface MyIStack<T> {
	T pop();
	void push(T v);
	boolean isEmpty();
	String toString();
	public Stack<T> getStack();
}
