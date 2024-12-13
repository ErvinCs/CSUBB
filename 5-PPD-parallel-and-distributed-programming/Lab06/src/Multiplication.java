public abstract class Multiplication {
    protected Polynomial p1;
    protected Polynomial p2;
    protected Polynomial result;

    public Multiplication(Polynomial p1, Polynomial p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.result = new Polynomial(p1.getSize() + p2.getSize(), new int[p1.getSize() + p2.getSize()]);
    }

    public Polynomial getP1() {
        return p1;
    }

    public Polynomial getP2() {
        return p2;
    }

    public Polynomial getResult() {
        return this.result;
    }

    public void clearResult() {
        this.result = new Polynomial(p1.getSize() + p2.getSize(), new int[p1.getSize() + p2.getSize()]);
    }

    public abstract Polynomial multiply(boolean showFlag);

    public abstract Polynomial multiplyMultiTh(boolean showFlag);

    @Override
    public String toString() {
        return p1.toString() + "*\n" + p2.toString();
    }
}
