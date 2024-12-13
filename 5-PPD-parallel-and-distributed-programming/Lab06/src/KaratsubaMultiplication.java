import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KaratsubaMultiplication extends Multiplication {
    private ThreadPoolExecutor executor;
    private List<MultiplicationThread> runnables;

    public KaratsubaMultiplication(Polynomial p1, Polynomial p2) {
        super(p1, p2);
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(p1.getSize() * p2.getSize());
        this.runnables = new ArrayList<>();
    }

    @Override
    public Polynomial multiply(boolean showFlag) {
        this.clearResult();
        long startTime, stopTime, elapsedTime;

        startTime = System.currentTimeMillis();

        Polynomial a0 = p1.getFirstHalf();
        Polynomial a1 = p1.getSecondHalf();
        Polynomial b0 = p2.getFirstHalf();
        Polynomial b1 = p2.getSecondHalf();

//        System.out.println("A0=" + a0.toString());
//        System.out.println("A1=" + a1.toString());
//        System.out.println("B0=" + b0.toString());
//        System.out.println("B1=" + b1.toString());

        Polynomial A0B0 = new RegularMultiplication(a0, b0).multiply(false);
        Polynomial A0B1 = new RegularMultiplication(a0, b1).multiply(false);
        Polynomial A1B0 = new RegularMultiplication(a1, b0).multiply(false);
        Polynomial A1B1 = new RegularMultiplication(a1, b1).multiply(false);

        int n = (p1.getSize() + p2.getSize()) / 2;
        if (n % 2 == 0)
            n += 1;
        int mid = n / 2 + 1;
//        System.out.println("n=" + n);

//        int[] middleCoef = {0, 0, 1};
        int[] middleCoef = new int[mid];
        for(int i = 0; i < mid-1; i++)
            middleCoef[i] = 0;
        middleCoef[mid-1] = 1;
        Polynomial middle = new Polynomial(mid, middleCoef);
//        System.out.println("Middle=" + middle.toString());


//        int[] lastCoef = {0, 0, 0, 0, 1};
        int[] lastCoef = new int[n];
        for(int i = 0; i < n-1; i++)
            lastCoef[i] = 0;
        lastCoef[n-1] = 1;
        Polynomial last = new Polynomial(n, lastCoef);
//        System.out.println("Last=" + last.toString());

        this.result = A0B0
                .add(new RegularMultiplication(A0B1, middle).multiply(false))
                .add(new RegularMultiplication(A1B0, middle).multiply(false))
                .add(new RegularMultiplication(A1B1, last).multiply(false));

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;

        System.out.println("Single-threaded Karatsuba multiplication Time: " + elapsedTime + "ms");

        return this.result;
    }

    @Override
    public Polynomial multiplyMultiTh(boolean showFlag) {
        this.clearResult();
        long startTime, stopTime, elapsedTime;

        startTime = System.currentTimeMillis();

        Polynomial a0 = p1.getFirstHalf();
        Polynomial a1 = p1.getSecondHalf();
        Polynomial b0 = p2.getFirstHalf();
        Polynomial b1 = p2.getSecondHalf();

//        System.out.println("A0=" + a0.toString());
//        System.out.println("A1=" + a1.toString());
//        System.out.println("B0=" + b0.toString());
//        System.out.println("B1=" + b1.toString());

        Polynomial A0B0 = new RegularMultiplication(a0, b0).multiplyMultiTh(false);
        Polynomial A0B1 = new RegularMultiplication(a0, b1).multiplyMultiTh(false);
        Polynomial A1B0 = new RegularMultiplication(a1, b0).multiplyMultiTh(false);
        Polynomial A1B1 = new RegularMultiplication(a1, b1).multiplyMultiTh(false);

        int n = (p1.getSize() + p2.getSize()) / 2;
        if (n % 2 == 0)
            n += 1;
        int mid = n / 2 + 1;
//        System.out.println("n=" + n);

//      int[] middleCoef = {0, 0, 1};
        int[] middleCoef = new int[mid];
        for(int i = 0; i < mid-1; i++)
            middleCoef[i] = 0;
        middleCoef[mid-1] = 1;
        Polynomial middle = new Polynomial(mid, middleCoef);
//        System.out.println("Middle=" + middle.toString());


//      int[] lastCoef = {0, 0, 0, 0, 1};
        int[] lastCoef = new int[n];
        for(int i = 0; i < n-1; i++)
            lastCoef[i] = 0;
        lastCoef[n-1] = 1;
        Polynomial last = new Polynomial(n, lastCoef);
//        System.out.println("Last=" + last.toString());

        this.result = A0B0
                .add(new RegularMultiplication(A0B1, middle).multiplyMultiTh(false))
                .add(new RegularMultiplication(A1B0, middle).multiplyMultiTh(false))
                .add(new RegularMultiplication(A1B1, last).multiplyMultiTh(false));

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;

        System.out.println("Multi-threaded Karatsuba multiplication Time: " + elapsedTime + "ms");

        return this.result;
    }
}
