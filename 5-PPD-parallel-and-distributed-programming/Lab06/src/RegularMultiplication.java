import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RegularMultiplication extends Multiplication {
    private ThreadPoolExecutor executor;
    private List<MultiplicationThread> runnables;
    //Proly need a lock

    public RegularMultiplication(Polynomial p1, Polynomial p2) {
        super(p1 ,p2);
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(p1.getSize() * p2.getSize());
        this.runnables = new ArrayList<>();
    }

    @Override
    public Polynomial multiply(boolean showFlag) {
        this.clearResult();
        long startTime, stopTime, elapsedTime;

        startTime = System.currentTimeMillis();

        for(int i = 0; i < p1.getSize(); i++) {
            for(int j = 0; j < p2.getSize(); j++) {
                result.setCoeficient(i+j, result.getCoeficients()[i+j] + p1.getCoeficients()[i] * p2.getCoeficients()[j]);
            }
        }

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;

        if(showFlag)
            System.out.println("Single-threaded Regular multiplication Time: " + elapsedTime + "ms");

        return this.result;
    }

    @Override
    public Polynomial multiplyMultiTh(boolean showFlag) {
        this.clearResult();
        long startTime, stopTime, elapsedTime;

        startTime = System.currentTimeMillis();

        for(int i = 0; i < p1.getSize(); i++) {
            for(int j = 0; j < p2.getSize(); j++) {
                MultiplicationThread worker = new MultiplicationThread(p1.getCoeficients()[i], p2.getCoeficients()[j], this.result, i, j);
                runnables.add(worker);
                executor.execute(worker);
            }
        }

        executor.shutdown();

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;

        if(showFlag)
            System.out.println("Multi-threaded Regular multiplication Time: " + elapsedTime + "ms");

        return this.result;
    }
}
