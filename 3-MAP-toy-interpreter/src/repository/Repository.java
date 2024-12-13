package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyTuple;
import domain.stmt.IStmt;

public class Repository implements IRepository {
	List<PrgState> myPrgState;
	String logFilePath;
	
	public Repository(String logFilePath) {
		myPrgState = new ArrayList<PrgState>();
		this.logFilePath = logFilePath;
	}
	
	@Override
	public void addPrg(PrgState newPrg) {
		myPrgState.add(newPrg);
	}
	
	@Override
	public List<PrgState> getPrgList() {
		return this.myPrgState;
	}
	
	@Override
	public void setPrgList(List<PrgState> newList) {
		this.myPrgState = newList;
	}
	
	@Override
	public void clearFile() throws IOException {
		PrintWriter logFile;
		logFile = new PrintWriter(new FileWriter(logFilePath));
		logFile.close();
	}

	@Override
	public void logPrgStateExec(PrgState myState) {
		try {
			PrintWriter logFile;
			logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
			MyIStack<IStmt> stack = myState.getExeStack();
			MyIDictionary<String,Integer> symTable = myState.getSymTable();
			MyIList<Integer> queue = myState.getOut();
	    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = myState.getFileTable();getClass();
	    	MyIDictionary<Integer, Integer> heap = myState.getHeap();
	    	logFile.println("ID: " + Integer.toString(myState.getId()));
			logFile.println("ExeStack:");
			ArrayList<IStmt> a = new ArrayList<IStmt>(stack.getStack());
			ListIterator<IStmt> li = a.listIterator(a.size());
			while(li.hasPrevious())
			{
				logFile.println("-> " + li.previous().toString());
			}
			logFile.println("SymTable:");
			for(HashMap.Entry<String, Integer> e : symTable.getDictionary().entrySet())
			{
			    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().toString());
			}
			logFile.println("Out:");
			for(Integer e : queue.getList())
			{
			    logFile.println("-> " + e.toString());
			}
			logFile.println("FileTable:");
			for(HashMap.Entry<Integer, MyTuple<String, BufferedReader>> e : fileTable.getDictionary().entrySet())
			{
			    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().getFirst());
			}
			logFile.println("Heap:");
			for(HashMap.Entry<Integer, Integer> e : heap.getDictionary().entrySet())
			{
			    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().toString());
			}
			logFile.println("------------------------------------------");
			logFile.close();
		} catch (IOException e) {
			System.out.println("Error creating the log file.");
		}
	}

	@Override
	public void serializePrgState(PrgState myState) throws IOException {
		FileOutputStream myFile = new FileOutputStream("prgState.ser");
		ObjectOutputStream outStream = new ObjectOutputStream(myFile);
		outStream.writeObject(myState);
		outStream.close();
	}

	@Override
	public PrgState deserializePrgState() throws IOException, ClassNotFoundException {
		FileInputStream myFile = new FileInputStream("prgState.ser");
		ObjectInputStream inStream = new ObjectInputStream(myFile);
		PrgState myState = (PrgState) inStream.readObject();
		inStream.close();
		return myState;
	}
}
