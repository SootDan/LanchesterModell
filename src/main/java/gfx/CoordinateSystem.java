package gfx;

import lanchester.MathManager;
import lanchester.Population;
import utils.Constants;
import utils.TimerListener;
import utils.TimerManager;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class CoordinateSystem extends JPanel implements TimerListener {
    private final MathManager mathManager;
    int width, height;

    Vector2D origin, bounds;

    double maxX;
    int border = 25, arrow = 5;

    // Minimum and maximum of either axis (doesn't take into account the graph -- just the valid points on the axis.)
    double x0, x1, y0, y1;
    double deltaX, deltaY;

    public CoordinateSystem(MathManager mathManager) {
        this.mathManager = mathManager;
        maxX = Math.max(mathManager.G.numberAtStart, mathManager.H.numberAtStart);

        TimerManager.getInstance().addSubscriber(this);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        width = getWidth();
        height = getHeight();

        x0 = border;
        x1 = width - border;
        y0 = height - border;
        y1 = border;
        // Deltas for space between coordinate ticks
        deltaX = x1 - x0;
        deltaY = y1 - y0;
        origin = new Vector2D(x0, y0);
        bounds = new Vector2D(x1, y1);

        for (Axis axis: Axis.values()) {
            drawAxes(g2d, axis);
            drawCoordinateSystem(g2d, axis);
        }
        drawTimer(g);
        drawPopGraph(g2d, mathManager.G, mathManager.H);
        drawPopGraph(g2d, mathManager.H, mathManager.G);
    }


    /**
     * Draws the axes and their arrows.
     */
    private void drawAxes(Graphics2D g, Axis axis) {
        // Axes
        Line2D axes = new Line2D.Double(
                origin.x,
                origin.y,
                axis == Axis.X ? bounds.x : origin.x,
                axis == Axis.X ? origin.y : bounds.y);
        g.draw(axes);

        // Arrows
        Vector2D arrowBase = new Vector2D(axes.getX2(), axes.getY2());
        g.draw(new Line2D.Double(arrowBase.x, arrowBase.y,
                arrowBase.x - arrow, arrowBase.y + arrow));
        g.draw(new Line2D.Double(arrowBase.x, arrowBase.y,
                axis == Axis.X ? arrowBase.x - arrow : arrowBase.x + arrow,
                axis == Axis.X ? arrowBase.y - arrow : arrowBase.y + arrow));
    }


    /**
     * Draws the coordinate system including arrows.
     * Axis.X is p(t).
     * Axis.Y is p (sets max value to the highest pop size at p(0)).
     */
    public void drawCoordinateSystem(Graphics2D g, Axis axis) {
        // Writes the descriptors for the axes only for the first function call.
        if (axis == Axis.X) {
            g.drawString("p", (float) origin.x, (float) (bounds.y - arrow));
            g.drawString("p(t)", (float) bounds.x, (float) origin.y);
        }

        AffineTransform base = g.getTransform();
        AffineTransform affineTransform = new AffineTransform();
        // Draws the actual ticks for each axis.
        for (int i = 0; i <= axis.incrementer; i++) {
            double dx = axis == Axis.X ? deltaX / axis.incrementer * i : 0.0;
            double dy = axis == Axis.X ? 0.0 : deltaY / axis.incrementer * i;
            Line2D line2D = axis == Axis.X
                    ? new Line2D.Double(0, -arrow, 0, arrow)
                    : new Line2D.Double(-arrow, 0, arrow, 0);
            affineTransform.setToTranslation(origin.x + dx, origin.y + dy);
            g.setTransform(affineTransform);
            g.draw(line2D);

            String descriptor = axis == Axis.X ? "" + i * 2 : "" + (int) (i * (maxX / axis.incrementer));
            g.drawString(
                descriptor,
                (int) (axis == Axis.X ? line2D.getX1() : line2D.getX1() - arrow * 4),
                (int) (axis == Axis.X ? line2D.getY2() + arrow * 3 : line2D.getY2())
            );
        }
        g.setTransform(base);
    }


    /**
     * Draws the population over time.
     */
    private void drawPopGraph(Graphics2D g, Population p1, Population p2) {
        Vector2D[] popAtT = new Vector2D[Constants.MAX_TICKS];

        for (int i = 0; i < popAtT.length; i++) {
            double popNum = mathManager.G.number / maxX;
            if (popNum < 0.0) popNum = 0.0;
            popAtT[i] = new Vector2D(x0, y1);
            g.drawLine((int) x0, (int) (y0 + deltaY * p1.numberAtStart / maxX), (int) (x0 + deltaX * mathManager.ticks / Constants.MAX_TICKS), (int) (y0 + deltaY * p1.number / maxX));
        }
        drawWaveForm(g, popAtT, p1.equals(mathManager.G));
    }


    private void drawTimer(Graphics g) {
        g.drawString("t elapsed: " + mathManager.ticks, (int) (bounds.x - origin.x * 2), (int) bounds.y);
    }


    /**
     * Draws a polyline approximating a wave form.
     */
    private void drawWaveForm(Graphics2D g, Vector2D[] popAtT, boolean isG) {
        int[] xs = new int[popAtT.length];
        int[] ys = new int[popAtT.length];
        for (int i = 0; i < popAtT.length; i++) {
            xs[i] = (int) 50;
            ys[i] = (int) 50;
        }
        g.setColor((isG ? mathManager.G.color : mathManager.H.color));
        g.drawPolyline(xs, ys, mathManager.ticks);
    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
