import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CycleSearcher {
    private Graph graph;
    private int[][] solution;
    private final int solutionSize;
    private boolean hasSolution;
    private ThreadPoolExecutor executor;
    private List<SearchThread> runnables;

    public CycleSearcher(Graph graph) {
        this.graph = graph;
        this.solution = new int[graph.getSize()][graph.getSize()];
        this.solutionSize = graph.getSize();
        this.hasSolution = false;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(graph.getSize() - 1);
        this.runnables = new ArrayList<>();
    }

    public void run() {
        List<Tuple<Integer, Integer>> possibleSolution = new ArrayList<>();
        for(int i = 0; i < graph.getSize(); i++) {
            boolean foundFirst = false;
            for (int j = 0; j < graph.getSize(); j++) {
                if(graph.getValueAt(i, j) != 0 && !foundFirst) {
                    foundFirst = true;
                    possibleSolution.add(new Tuple<>(i, j));
                } else if (graph.getValueAt(i, j) != 0 && foundFirst) {
                    SearchThread worker = new SearchThread(possibleSolution, i, j, graph);
                    runnables.add(worker);
                    executor.execute(worker);
                }
            }
        }
        if(possibleSolution.size() == solutionSize) {
            System.out.println(possibleSolution.toString());
            if (checkSolution(possibleSolution)) {
                setSolution(possibleSolution);
            }
        }
        if(!hasSolution) {
            for(SearchThread worker : runnables) {
                if(checkSolution(worker.getPossibleSolution())) {
                    setSolution(worker.getPossibleSolution());
                    break;
                }
            }
        }
        executor.shutdown();
        System.out.println(this.getSolutionString());
    }

    private void setSolution(List<Tuple<Integer, Integer>> possibleSolution) {
        hasSolution = true;
        for(Tuple<Integer, Integer> tuple : possibleSolution) {
            solution[tuple.getX()][tuple.getY()] = 1;
        }
    }

    private boolean checkSolution(List<Tuple<Integer, Integer>> possibleSolution) {
        //System.out.println("CheckedMain: " + possibleSolution.toString());
        if(possibleSolution.size() != graph.getSize())
            return false;

        Tuple<Integer, Integer> first = possibleSolution.get(0);
        Tuple<Integer, Integer> last = possibleSolution.get(graph.getSize() - 1);
        if(first.getX() != last.getY())
            return false;

        List<Tuple<Integer, Integer>> aux = new ArrayList<>(possibleSolution);
        while(aux.size() > 1) {
            aux.remove(first);
            boolean match = false;
            for(Tuple<Integer, Integer> next : aux) {
                if(first.getY().equals(next.getX())) {
                    //System.out.println(first.toString() + " - " + next.toString());
                    match = true;
                    first = next;
                    break;
                }
            }
            if(!match)
                return false;
        }

        return true;
    }

    public String getSolutionString() {
        if (!hasSolution)
            return "No Hamiltonian Cycle!";
        else {
            String output = "Solution: \n";
            for(int i = 0; i < solutionSize; i++) {
                for(int j = 0; j < solutionSize; j ++) {
                    output += solution[i][j] + " ";
                }
                output += "\n";
            }
            return output;
        }
    }
}
