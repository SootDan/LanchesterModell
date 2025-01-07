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
     * Basic vector addition that returns a new vector.
     */
    public static Vector2D addition(Vector2D u, Vector2D v) {
        return new Vector2D(u.x + v.x, u.y + v.y);
    }

    public void addition(Vector2D v) {
        x += v.x;
        y += v.y;
    }


    public boolean isZero(Vector2D v) {
        return v.x == 0.0 && v.y == 0.0;
    }


    /**
     * Translates given x and y into x1 and y1.
     * tx and ty are the necessary shift parameters to accomplish this.
     */
    public static Vector2D homogenousCoordinates(Vector2D v, double tx, double ty) {
        return new Vector2D(v.x + tx, v.y + ty);
    }


    public Vector2D homogenousCoordinates(Vector2D v, double t, boolean xShift) {
        if (xShift) return new Vector2D(v.x + t, v.y);
        else return new Vector2D(v.x, v.y + t);
    }


    /**
     * Returns a string of both vector coordinates. Useful for debugging.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}