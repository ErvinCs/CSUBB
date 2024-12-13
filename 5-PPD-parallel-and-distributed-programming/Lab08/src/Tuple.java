public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }


    @Override
    public String toString() {
        return "(" + x.toString() + ", " + y.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return this.getX() == ((Tuple) obj).getX() && this.getY() == ((Tuple) obj).getY();
    }
}