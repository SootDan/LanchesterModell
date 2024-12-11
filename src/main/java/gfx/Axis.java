package gfx;

public enum Axis {
    X(10),
    Y(5);

    public final int incrementer;

    Axis(int incrementer) {
        this.incrementer = incrementer;
    }
}
