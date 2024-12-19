package utils;

public class VectorMath {
    /**
     * Basic vector addition that returns a new vector.
     */
    public static Vector2D addition(Vector2D u, Vector2D v) {
        return new Vector2D(u.x + v.x, u.y + v.y);
    }


    /**
     * Translates given x and y into x1 and y1.
     * tx and ty are the necessary shift parameters to accomplish this.
     */
    public static Vector2D homogenousCoordinates(Vector2D v, double tx, double ty) {
        return new Vector2D(v.x + tx, v.y + ty);
    }


    public static Vector2D homogenousCoordinatesX(Vector2D v, double t) {
        return new Vector2D(v.x + t, v.y);
    }


    public static Vector2D homogenousCoordinatesY(Vector2D v, double t) {
        return new Vector2D(v.x, v.y + t);
    }


    public static Vector2D homogenousCoordinates(Vector2D v, double t) {
        return new Vector2D(v.x + t, v.y + t);
    }


    public static Vector2D constant(Vector2D v, double k) {
        return new Vector2D(k * v.x, k * v.y);
    }
}
