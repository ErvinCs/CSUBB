package problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SequenceSumOperation {
    private Sequence input;
    private Sequence output;
    private ThreadPoolExecutor executor;
    private List<SequenceSumThread> runnables;

    public SequenceSumOperation(Sequence input) {
        this.input = input;
        this.output = new Sequence();
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(input.getSize() - 1);
        this.runnables = new ArrayList<>();
    }

    public void run() {
        output.addNumber(input.getNumberAt(0));
        int previous = input.getNumberAt(0);
        for(int i = 1; i < this.input.getSize(); i++) {
            CountDownLatch latch = new CountDownLatch(1);

            SequenceSumThread worker = new SequenceSumThread(previous, input.getNumberAt(i), latch);
            runnables.add(worker);
            executor.execute(worker);

            try {
                latch.await();
                output.addNumber(worker.getSum());
                previous = worker.getSum();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }


        }
        executor.shutdown();

//        for(SequenceSumThread worker : runnables) {
//            output.addNumber(worker.getSum());
//        }
    }

    @Override
    public String toString() {
        return output.toString();
    }
}

