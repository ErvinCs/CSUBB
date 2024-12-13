import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SearchThread extends Thread {
    private Thread thread;
    private final int solutionSize;
    private int[][] solution;
    private boolean hasSolution;
    private List<Tuple<Integer, Integer>> possibleSolution;
    private int i, j;
    private Graph graph;
    private ThreadPoolExecutor executor;
    private List<SearchThread> runnables;

    public SearchThread(List<Tuple<Integer, Integer>> possibleSolution, int i, int j, Graph graph) {
        this.thread = new Thread(this::run);
        this.graph = graph;
        this.hasSolution = false;
        this.solutionSize = graph.getSize();
        this.solution = new int[graph.getSize()][graph.getSize()];
        this.possibleSolution = new ArrayList<>(possibleSolution);
        this.possibleSolution.remove(this.possibleSolution.size() - 1);   //CycleSeacher adds this in the first if branch
        this.i = i;
        this.j = j;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(graph.getSize() - 1);
        this.runnables = new ArrayList<>();
    }

    @Override
    public void run() {
        for(int i = this.i; i < graph.getSize(); i++) {
            boolean foundFirst = false;
            for (int j = this.j; j < graph.getSize(); j++) {
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

    }


    public List<Tuple<Integer, Integer>> getPossibleSolution() {
        return possibleSolution;
    }

    private void setSolution(List<Tuple<Integer, Integer>> possibleSolution) {
        hasSolution = true;
        for(Tuple<Integer, Integer> tuple : possibleSolution) {
            solution[tuple.getX()][tuple.getY()] = 1;
        }
    }

    private boolean checkSolution(List<Tuple<Integer, Integer>> possibleSolution) {
        //System.out.println("CheckedTh: " + possibleSolution.toString());
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
}
