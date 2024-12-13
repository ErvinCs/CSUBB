package repository;

import java.io.IOException;
import java.util.List;

import domain.PrgState;

public interface IRepository {
	void addPrg(PrgState newPrg);
	public void clearFile() throws IOException;
	public List<PrgState> getPrgList();
	public void setPrgList(List<PrgState> newList);
	void logPrgStateExec(PrgState myState);
	void serializePrgState(PrgState myState) throws IOException;
	PrgState deserializePrgState() throws IOException, ClassNotFoundException;
}
