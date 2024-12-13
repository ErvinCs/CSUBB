import problem1.Sequence;
import problem1.SequenceSumOperation;
import problem2.BigNumber;
import problem2.BigNumberAddOperation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime, stopTime, elapsedTime;

        Sequence sequence = new Sequence(5);
        System.out.println("Input " + sequence.toString());
        SequenceSumOperation summed = new SequenceSumOperation(sequence);

        startTime = System.currentTimeMillis();
        summed.run();
        stopTime = System.currentTimeMillis();

        System.out.println("Output " + summed.toString());
        elapsedTime = stopTime - startTime;
        System.out.println("Time: " + elapsedTime + "ms\n");


        BigNumber number1 = new BigNumber(new ArrayList<Integer>() {{
            add(7);
            add(5);
            add(7);
            add(8);
        }});
        BigNumber number2 = new BigNumber(new ArrayList<Integer>() {{
            add(7);
            add(7);
            add(3);
            add(9);
        }});
//        BigNumber number1 = new BigNumber();
//        BigNumber number2 = new BigNumber();
//        int n = 5;
//        number1.generateDigits(n);
//        number2.generateDigits(n);
        BigNumberAddOperation bigSummed = new BigNumberAddOperation(number1, number2);

        startTime = System.currentTimeMillis();
        bigSummed.run();
        stopTime = System.currentTimeMillis();

        System.out.println(bigSummed.toString());
        elapsedTime = stopTime - startTime;
        System.out.println("Time: " + elapsedTime + "ms\n");
    }
}
