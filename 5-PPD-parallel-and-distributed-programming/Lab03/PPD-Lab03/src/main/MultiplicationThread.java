package main;

import java.util.concurrent.CountDownLatch;

public class MultiplicationThread extends Thread
{
    private Thread thread;
    private int line, column;
    Matrix matrix1, matrix2;
    int result;
    CountDownLatch latch; //Should this be here tho?

    public MultiplicationThread(Matrix matrix1, Matrix matrix2, int line, int column, CountDownLatch latch)
    {
        this.thread = new Thread(this::run);
        this.line = line;
        this.column = column;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        this.result = 0;
        for(int i = 0; i < matrix1.getColumns(); i++)
            this.result += this.matrix1.getData()[column][i] * this.matrix2.getData()[i][line];
        latch.countDown();
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
}