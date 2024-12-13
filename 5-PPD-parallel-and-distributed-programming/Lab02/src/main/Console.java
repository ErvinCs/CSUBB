package main;

public class Console {
    public static void main(String[] args) {

        long startTime, stopTime, elapsedTime;

        int lines1 = 5;
        int columns1 = 5;

        Matrix A = new Matrix(lines1, columns1);
        A.populate();
        System.out.println("A:" + A.toString());

        Matrix B = new Matrix(lines1, columns1);
        B.populate();
        System.out.println("B:" + B.toString());

        Matrix C = new Matrix(lines1, columns1);
        C.populate();
        System.out.println("C:" + C.toString());

        startTime = System.currentTimeMillis();
        Matrix AplusB = A.add(B);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Multi-threaded:\nA + B: " + AplusB + "Time: " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        Matrix AplusBnoTh = A.addNoThreads(B);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Single-threaded:\nA + B: " + AplusBnoTh + "Time: " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        Matrix BstarC = B.multiply(C);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Multi-threaded:\nB * C: " + BstarC + "Time: " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        Matrix BstarCnoTh = B.multiplyNoThreads(C);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Single-threaded:\nB * C: " + BstarCnoTh + "Time: " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        Matrix CstarB = C.multiply(B);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Multi-threaded:\nC * B: " + CstarB + "Time: " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        Matrix CstarBnoTh = C.multiplyNoThreads(B);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Single-threaded:\nC * B: " + CstarBnoTh + "Time: " + elapsedTime + "ms\n");

    }
}
