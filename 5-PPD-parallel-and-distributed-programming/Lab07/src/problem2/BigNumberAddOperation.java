package problem2;

import problem1.SequenceSumThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BigNumberAddOperation {
    private BigNumber number1;
    private BigNumber number2;
    private BigNumber output;
    private ThreadPoolExecutor executor;
    private List<DigitAdditionThread> runnables;


    public BigNumberAddOperation(BigNumber number1, BigNumber number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.fill();
        this.output = new BigNumber();
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(number1.getNumberOfDigits());
        this.runnables = new ArrayList<>();
    }

    private void fill() {
        if(number1.getNumberOfDigits() > number2.getNumberOfDigits()) {
            number2.fillZeros(number1);
        } else if (number2.getNumberOfDigits() > number1.getNumberOfDigits()) {
            number1.fillZeros(number2);
        }
    }

    public void run() {
        int extra = 0;
        for(int i = this.number1.getNumberOfDigits() - 1; i >= 0 ; i--) {
            CountDownLatch latch = new CountDownLatch(1);

            DigitAdditionThread worker = new DigitAdditionThread(number1.getDigitAt(i),
                    number2.getDigitAt(i),
                    extra,
                    latch);
            runnables.add(worker);
            executor.execute(worker);

            try {
                latch.await();
                output.addDigit(worker.getSum());
                //Send the result to the next thread
                extra = worker.getExtra();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(extra != 0)
            output.addDigit(extra);
        output.reverseDigits();
        executor.shutdown();

    }

    @Override
    public String toString() {
        return  "Number1: " + number1.toString() + "\n" +
                "Number2: " + number2.toString() + "\n" +
                "Output: " + output.toString();
    }
}
