package main;

import java.util.concurrent.CountDownLatch;

public class DisableLatch {
    private CountDownLatch latch;
    private boolean disabled;
    private int id = -1;

    DisableLatch(int countdown) {
        this.latch = new CountDownLatch(countdown);
        disabled = false;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void countDown() {
        this.latch.countDown();
    }

    public long getCount() {
        return this.latch.getCount();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void await() throws InterruptedException {
        this.latch.await();
    }
}
