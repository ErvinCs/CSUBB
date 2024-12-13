package domain.adt;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class MyList<T> implements MyIList<T>, Serializable {
	private static final long serialVersionUID = 1L;
	Queue<T> list;
	
	public MyList() {
		list = new LinkedList<T>();
	}

	@Override
	public void add(T v) {
		list.add(v);
	}

	@Override
	public T pop() {
		return list.poll();
	}

	@Override
	public String toString() {
		String returnString = "";
		for(T e : list)
		{
		    returnString = returnString + e.toString() + "\n";
		}
		return returnString;
	}
	
	public Queue<T> getList() {
		return list;
	}
	
}
