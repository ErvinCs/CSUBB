package domain.adt;
import java.io.Serializable;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>, Serializable {
	private static final long serialVersionUID = 1L;
	Stack<T> stack;
	
	public MyStack() {
		stack = new Stack<T>();
	}
	
	@Override
	public T pop() {
		return stack.pop();
	}

	@Override
	public void push(T v) {
		stack.push(v);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	@Override
	public String toString() {
		String returnString = "";
		for(T e : stack)
		{
		    returnString = returnString + e.toString() + "\n";
		}
		return returnString;
	}
	
	public Stack<T> getStack() {
		return stack;
	}
}
