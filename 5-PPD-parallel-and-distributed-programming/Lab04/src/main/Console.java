package main;

public class Console {
    public static void main(String[] args) {
        int lines1 = 10;
        int columns1 = 10;

        Matrix A = new Matrix(lines1, columns1);
        A.populate();
        System.out.println("A:" + A.toString());

        Matrix B = new Matrix(lines1, columns1);
        B.populate();
        System.out.println("B:" + B.toString());

        Matrix C = new Matrix(lines1, columns1);
        C.populate();
        System.out.println("C:" + C.toString());

        long startTime, stopTime, elapsedTime;

        MatrixMultiplication ABC = new MatrixMultiplication(A,B,C);
        try {
            startTime = System.currentTimeMillis();
            ABC.runSingle();
            //ABC.runMulti();
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Running time: " + elapsedTime + "ms.");
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }
        System.out.println(ABC.getResult().toString());


//        try {
//            Matrix ABC = A.multiply(B, C);
//            System.out.println("Multi-threaded:\nA * B * C: " + ABC);
//        }catch (InterruptedException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
//        Matrix ABCnoTh = A.multiplyNoThreads(B, C);
//        System.out.println("Single-threaded:\nA * B * C: " + ABCnoTh);

    }
}
