public class Main {
    public static int[] generateCoefs(int n) {
        int[] coefs = new int[n];
        for(int i = 0; i < n; i++) {
            coefs[i] = (int) (Math.random() * 5 + 1);
        }
        return coefs;
    }

    public static void main(String[] args) {
        int size = 250;
        int[] coef1 = generateCoefs(size);  //{1, 2, 3, 4, 5};
        Polynomial p1 = new Polynomial(size, coef1);
        int[] coef2 = generateCoefs(size);  //{5, -4, 3, -2, 1};
        Polynomial p2 = new Polynomial(size, coef2);

        System.out.println("P1=" + p1.toString());
        System.out.println("P2=" + p2.toString() + "\n");

        RegularMultiplication mRegular = new RegularMultiplication(p1, p2);
        System.out.println("Single-threaded O(n^2)=" + mRegular.multiply(true));
        System.out.println("Multi-threaded O(n^2)=" + mRegular.multiplyMultiTh(true) + "\n");

        KaratsubaMultiplication mKaratsuba = new KaratsubaMultiplication(p1, p2);
        System.out.println("Single-threaded O(n^2)=" + mKaratsuba.multiply(true));
        System.out.println("Multi-threaded O(n^2)=" + mKaratsuba.multiplyMultiTh(true));
    }
}
