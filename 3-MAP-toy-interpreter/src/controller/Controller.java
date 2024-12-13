package controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import domain.PrgState;
import exception.MyException;
import repository.IRepository;
import repository.Repository;

public class Controller {
	IRepository myRepository;
	ExecutorService executor; 
	
	Controller() {}
	
	public Controller(Repository myRepository) {
		this.myRepository = myRepository;
	}
	
	public void addProgram(PrgState newPrg) {
		myRepository.addPrg(newPrg);
	}
	
	Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
			return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey()))
			 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} 
	
	List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
		return inPrgList.stream()
		 .filter(p -> p.isNotCompleted())
		 .collect(Collectors.toList());
	}
	
	public IRepository getRepository() {
		return this.myRepository;
	}
	
	void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
		 //prgList.forEach(prg -> myRepository.logPrgStateExec(prg));
		 
		 List<Callable<PrgState>> callList = prgList.stream()
				 .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
				 .collect(Collectors.toList());

		 List<PrgState> newPrgList = executor.invokeAll(callList).stream()
				 .map(future -> { 
					 try {
						 return future.get();
					 }
					 catch(Exception e) {
						 System.out.println(e.getMessage());
					 }
					return null;
				 })
				 .filter(p -> p!=null)
				 .collect(Collectors.toList());
		 
		 prgList.addAll(newPrgList);
		 prgList.forEach(prg -> myRepository.logPrgStateExec(prg));
		 myRepository.setPrgList(prgList);
	}
	
	public void allStep() {
		 executor = Executors.newFixedThreadPool(2);
		 while(true){
			 List<PrgState> prgList = removeCompletedPrg(myRepository.getPrgList());
			 if(prgList.size() == 0)
				 break;
			 try {
				oneStepForAllPrg(prgList);
			} catch (InterruptedException e) {
				System.out.println("Error returned by one step.");
			}
		 }
		 executor.shutdownNow();
	}
	
	public void allStepGUI() throws MyException {
		 executor = Executors.newFixedThreadPool(2);
		 List<PrgState> prgList = removeCompletedPrg(myRepository.getPrgList());
		 if(prgList.size() == 0) {
			 executor.shutdownNow();
			 throw new MyException("Program finished.");
		 }
		 else {
			try {
				oneStepForAllPrg(prgList);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.shutdownNow();
		 }
	}
	
	public String toString() {
		return myRepository.getPrgList().get(0).getOriginalProgram().toString();
	}
	
}
