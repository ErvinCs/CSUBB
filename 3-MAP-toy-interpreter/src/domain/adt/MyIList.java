package domain.adt;

import java.util.Queue;

public interface MyIList<T> {
	void add(T v);
	T pop();
	String toString();
	public Queue<T> getList();
}
