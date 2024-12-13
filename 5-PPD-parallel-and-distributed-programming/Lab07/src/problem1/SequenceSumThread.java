package problem1;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SequenceSumThread extends Thread {
    private Thread thread;
    private int previous;
    private int next;
    private int sum;
    private CountDownLatch latch;

    public SequenceSumThread(int previous, int next, CountDownLatch latch) {
        this.thread = new Thread(this::run);
        this.previous = previous;
        this.next = next;
        this.sum = 0;
        this.latch = latch;
    }

    @Override
    public void run() {
        sum = previous + next;
        latch.countDown();
    }

    public int getSum() {
        return sum;
    }
}
