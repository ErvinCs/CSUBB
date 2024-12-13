package controller;

import domain.PrgState;
import repository.IRepository;

import java.util.List;

public interface IController
{
    void addProgram(PrgState newPrg);
    IRepository getRepository();
    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException;
    void allStep();
    void allStepGUI();
}
