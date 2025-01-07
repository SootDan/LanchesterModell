package gfx;

/**
 * Vectors and matrix transformation.
 * Useful for handling statistics and graphics.
 */
public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns a string of both vector coordinates. Useful for debugging.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}