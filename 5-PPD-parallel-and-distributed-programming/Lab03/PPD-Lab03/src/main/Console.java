package main;

public class Console {
    public static void main(String[] args) {
        int lines1 = 5;
        int columns1 = 5;

        Matrix A = new Matrix(lines1, columns1);
        A.populate();
        System.out.println("A:" + A.toString());

        Matrix B = new Matrix(lines1, columns1);
        B.populate();
        System.out.println("B:" + B.toString());

        Matrix AplusB = A.add(B);
        System.out.println("Multi-threaded:\nA + B: " + AplusB);

        Matrix AplusBnoTh = A.addNoThreads(B);
        System.out.println("Single-threaded:\nA + B: " + AplusBnoTh);

        Matrix AstarB = A.multiply(B);
        System.out.println("Multi-threaded:\nA * B: " + AstarB);

        Matrix AstarBnoTh = A.multiplyNoThreads(B);
        System.out.println("Single-threaded:\nA * B: " + AstarBnoTh);

    }
}
