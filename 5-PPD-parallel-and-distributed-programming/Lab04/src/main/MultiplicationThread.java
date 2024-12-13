package main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;

public class MultiplicationThread extends Thread
{
    private Thread thread;
    private int line, column;
    private Matrix matrix1, matrix2;
    private int result;
    private DisableLatch latch;
//    private Object monitor;
//    private final Condition lineReady;

    public MultiplicationThread(Matrix matrix1, Matrix matrix2, int line, int column, DisableLatch latch)//, Condition lineReady)//Object monitor)
    {
        this.thread = new Thread(this::run);
        this.line = line;
        this.column = column;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.latch = latch;
        //this.lineReady = lineReady;
        //this.monitor = monitor;
    }

    @Override
    public void run()
    {
        this.result = 0;
        for(int i = 0; i < matrix1.getColumns(); i++)
            this.result += this.matrix1.getData()[column][i] * this.matrix2.getData()[i][line];
        latch.countDown();
        //synchronized (lineReady) {
        //    if (latch.getCount() == 0)
        //        lineReady.signal();
        //}
            //monitor.notify();
    }

    public long getLatchCount()
    {
        return this.latch.getCount();
    }

    public int getLineIndex() {
        return line;
    }

    public int getColumnIndex() {
        return column;
    }

    public int getResult() {
        return result;
    }

//    public Object getMonitor() {
//        return this.monitor;
//    }
}