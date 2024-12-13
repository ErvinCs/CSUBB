package problem2;

import java.util.concurrent.CountDownLatch;

public class DigitAdditionThread extends Thread  {
    private Thread thread;
    private int digit1;
    private int digit2;
    private int sum;
    private int extra;
    private CountDownLatch latch;

    public DigitAdditionThread(int digit1, int digit2, int extra, CountDownLatch latch) {
        this.thread = new Thread(this::run);
        this.digit1 = digit1;
        this.digit2 = digit2;
        this.sum = 0;
        this.extra = extra;
        this.latch = latch;
    }

    @Override
    public void run() {
        //System.out.println("Th: " + digit1 + digit2 + extra);
        sum = digit1 + digit2 + extra;
        if (sum >= 10) {
            extra = 1;
            sum -= 10;
        } else
            extra = 0;
        latch.countDown();
    }

    public int getSum() {
        return sum;
    }

    public int getExtra() {
        return extra;
    }
}
