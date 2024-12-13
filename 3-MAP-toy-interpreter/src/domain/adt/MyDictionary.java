package domain.adt;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import exception.MyException;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2>, Serializable {
	private static final long serialVersionUID = 1L;
	HashMap<T1, T2> dictionary;
	
	public MyDictionary() {
		dictionary = new HashMap<T1,T2>();
	}

	@Override
	public void add(T1 v1, T2 v2) {
		dictionary.put(v1, v2);
	}
	
	@Override
	public void update(T1 v1, T2 v2) {
		dictionary.put(v1, v2);
	}
	
	@Override
	public void remove(T1 v1) {
		dictionary.remove(v1);
	}

	@Override
	public T2 lookup(T1 id) throws MyException {
		if (dictionary.get(id) != null) {
			return dictionary.get(id);
		}
		throw new MyException("Couldn't find the given id.");
	}

	@Override
	public boolean isDefined(T1 id) {
		if(dictionary.get(id) != null)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		String returnString = "";
		for(HashMap.Entry<T1, T2> e : dictionary.entrySet())
		{
		    returnString = returnString + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().toString() + "\n";
		}
		return returnString;
	}
	
	public HashMap<T1, T2> getDictionary() {
		return dictionary;
	}
	
	@Override
	public void setDictionary(Map<T1, T2> newDictionary) {
		HashMap<T1, T2> hashMapConversion = new HashMap<T1, T2>(newDictionary);
		this.dictionary = hashMapConversion;
	}
}
