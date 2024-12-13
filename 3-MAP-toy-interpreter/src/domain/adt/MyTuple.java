package domain.adt;

import java.io.Serializable;

public class MyTuple<T1,T2> implements Serializable {
	private static final long serialVersionUID = 1L;
	T1 first;
	T2 second;
	
	public MyTuple() {}
	public MyTuple(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	public void setFirst(T1 first) {
		this.first = first;
	}
	
	public T1 getFirst() {
		return this.first;
	}
	
	public void setSecond(T2 second){
		this.second = second;
	}
	
	public T2 getSecond() {
		return this.second;
	}
}
