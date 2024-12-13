package domain.adt;

import java.util.HashMap;
import java.util.Map;

import exception.MyException;

public interface MyIDictionary<T1,T2> {
	void add(T1 v1, T2 v2);
	void update(T1 v1, T2 v2);
	void remove(T1 v1);
	T2 lookup(T1 id) throws MyException;
	boolean isDefined(T1 id);
	String toString();
	public HashMap<T1, T2> getDictionary();
	public void setDictionary(Map<T1,T2> map);
}
