package main;

import java.util.concurrent.CountDownLatch;

public class AdditionThread extends Thread
{
    private Thread thread;
    private int number1, number2;
    int result;
    CountDownLatch latch; //Should this be here tho?

    public AdditionThread(int number1, int number2, CountDownLatch latch)
    {
        this.thread = new Thread(this::run);
        this.number1 = number1;
        this.number2 = number2;
        this.latch = latch;
    }

    @Override
    public void run() {
        this.result = number1 + number2;
        latch.countDown();
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getResult() {
        return result;
    }
}