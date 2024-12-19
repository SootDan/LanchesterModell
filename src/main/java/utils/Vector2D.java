package utils;

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


    public Vector2D reflection(Vector2D v) {
        // TODO: UNFINISHED
        return new Vector2D(0.0, 0.0);
    }


    public void addition(Vector2D v) {
        x += v.x;
        y += v.y;
    }


    /**
     * Returns the scalar product as a double.
     */
    public double scalarProduct(Vector2D u, Vector2D v) {
        return u.x * v.x + u.y * v.y;
    }


    public boolean isZero(Vector2D v) {
        return v.x == 0.0 && v.y == 0.0;
    }


    /**
     * Returns the length of a vector as a double.
     */
    public double length(Vector2D v) throws Exception {
        if (isZero(v))
            throw new Exception("Zero vector.");
        return Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
    }


    /**
     * Returns a vector of length 1 (normalized vector).
     */
    public Vector2D normal(Vector2D v) throws Exception {
        if (isZero(v))
            throw new Exception("Zero vector.");
        return new Vector2D((1 / length(v)) * v.x, (1 / length(v)) * v.y);
    }


    /**
     * Returns the angle in radians (not degrees!).
     */
    public double angleRadians(Vector2D u, Vector2D v) throws Exception {
        if (isZero(v) || isZero(u))
            throw new Exception("Zero vector.");
        return Math.acos(scalarProduct(u, v) / (length(u) * length(v)));
    }


    /**
     * Returns a string of both vector coordinates. Useful for debugging.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}