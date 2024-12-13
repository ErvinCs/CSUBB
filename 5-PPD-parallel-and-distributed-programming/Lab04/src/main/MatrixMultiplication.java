package main;

import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixMultiplication {
    private Matrix m1;
    private Matrix m2;
    private Matrix m3;
    private Matrix result;
    private Matrix intermediate;
    private ThreadPoolExecutor tp1;
    private ThreadPoolExecutor tp2;
    private List<MultiplicationThread> runnables1;
    private List<MultiplicationThread> runnables2;

    private final Lock lock = new ReentrantLock();
    private final Condition lineReady = lock.newCondition();

    public MatrixMultiplication(Matrix m1, Matrix m2, Matrix m3) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.intermediate = new Matrix(m1.getRows(), m2.getColumns());
        this.result = new Matrix(intermediate.getRows(), m3.getColumns());
        tp1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(m1.getRows() * m2.getColumns());
        tp2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(intermediate.getRows() * m3.getColumns());
        runnables1 = new ArrayList<>();
        runnables2 = new ArrayList<>();
    }

    public void runSingle() throws InterruptedException {
        multiplyIntermediateSingle();
        multiplyResultSingle();
    }

    public void runMulti() throws InterruptedException {
        for(int i = 0; i < this.intermediate.getRows(); i++) {
            multiplyIntermediateMulti(i);
            multiplyResultMulti(i);
        }

        System.out.println("Intermediate:" + this.intermediate.toString());
    }

    public Matrix getResult() {
        return result;
    }

    private void multiplyIntermediateMulti(int row) throws InterruptedException {
        lock.lock();
        try {
            //System.err.println("Starting first multiplication on row<" + row + ">");
            DisableLatch latch = new DisableLatch(intermediate.getRows());
            for (int j = 0; j < this.intermediate.getColumns(); j++) {
                MultiplicationThread worker = new MultiplicationThread(m1, m2, row, j, latch);
                runnables1.add(worker);
                tp1.execute(worker);
            }
            latch.await();
            for (int j = 0; j < this.intermediate.getColumns(); j++) {
                this.intermediate.setData(j, row, (runnables1.get(row*intermediate.getColumns() + j).getResult()));
            }
            //lineReady.signalAll();
            //System.err.println("Finished first multiplication on row<" + row + ">");
        } finally {
            lock.unlock();
        }
    }

    private void multiplyResultMulti(int row) throws InterruptedException {
        lock.lock();
        try {
            //System.err.println("Waiting on second multiplication");
            //lineReady.await();
            //System.err.println("Starting second multiplication<" + row + ">");
            DisableLatch latch = new DisableLatch(result.getRows());
            for (int j = 0; j < this.result.getColumns(); j++) {
                MultiplicationThread worker = new MultiplicationThread(intermediate, m3, row, j, latch);
                runnables2.add(worker);
                tp2.execute(worker);
            }
            latch.await();
            for (int j = 0; j < this.result.getColumns(); j++) {
                this.result.setData(j, row, (runnables2.get(row*result.getColumns() + j).getResult()));
            }
            //System.err.println("Finished second multiplication<" + row + ">");
        } finally {
            lock.unlock();
        }
    }

    private void multiplyIntermediateSingle() {
        for(int i = 0; i < this.intermediate.getRows(); i++) {
            for(int j = 0; j < this.intermediate.getColumns(); j++) {
                int res = 0;
                for(int k = 0; k < this.m2.getRows(); k++)
                    res += this.m1.getData()[i][k] * this.m2.getData()[k][j];
                intermediate.setData(i, j, res);
            }
        }
    }

    private void multiplyResultSingle() {
        for(int i = 0; i < this.result.getRows(); i++) {
            for(int j = 0; j < this.result.getColumns(); j++) {
                int res = 0;
                for(int k = 0; k < this.m3.getRows(); k++)
                    res += this.intermediate.getData()[i][k] * this.m3.getData()[k][j];
                this.result.setData(i, j, res);
            }
        }
    }


}
