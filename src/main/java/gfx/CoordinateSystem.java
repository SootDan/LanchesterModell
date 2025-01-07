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
import java.awt.geom.Path2D;

public class CoordinateSystem extends JPanel implements TimerListener {
    private final MathManager mathManager;
    int width, height;

    Vector2D origin, bounds;

    double maxX;
    int border = 25, arrow = 5;

    // Minimum and maximum of either axis (doesn't take into account the graph - just the valid points on the axis.)
    double x0, x1, y0, y1;
    double deltaX, deltaY;
    Vector2D[] gPopAtT = new Vector2D[Constants.MAX_TICKS + 1];
    Vector2D[] hPopAtT = new Vector2D[Constants.MAX_TICKS + 1];


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
        drawPopGraph(g2d, mathManager.G, true);
        drawPopGraph(g2d, mathManager.H, false);
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

        // Draws the actual ticks for each axis.
        for (int i = 0; i <= axis.incrementer; i++) {
            double dx = deltaX / axis.incrementer * i;
            double dy = deltaY / axis.incrementer * i;
            Line2D line2D = axis == Axis.X
                    ? new Line2D.Double(x0 + dx, y0 + -arrow, x0 + dx, y0 + arrow)
                    : new Line2D.Double(x0 - arrow, y0 + dy, x0 + arrow, y0 + dy);
            g.draw(line2D);

            String descriptor = axis == Axis.X ? "" + i: "" + (int) (i * (maxX / axis.incrementer));
            g.drawString(
                descriptor,
                (int) (axis == Axis.X ? line2D.getX1() : line2D.getX1() - arrow * 4),
                (int) (axis == Axis.X ? line2D.getY2() + arrow * 3 : line2D.getY2())
            );
        }
    }


    /**
     * Draws the population over time.
     */
    private void drawPopGraph(Graphics2D g, Population p, boolean isG) {
        // TODO: Rewrite this. God is angry at this shitty code.
        gPopAtT[0] = new Vector2D(x0, (y0 + deltaY * mathManager.G.numberAtStart / maxX));
        hPopAtT[0] = new Vector2D(x0, (y0 + deltaY * mathManager.H.numberAtStart / maxX));

        Path2D path2D = new Path2D.Double();
        if (isG) {
            gPopAtT[mathManager.ticks] = new Vector2D(
                    (x0 + deltaX * (mathManager.ticks) / Constants.MAX_TICKS), (y0 + deltaY * p.number / maxX));
            path2D.moveTo(gPopAtT[0].x, gPopAtT[0].y);
        } else {
            hPopAtT[mathManager.ticks] = new Vector2D(
                    (x0 + deltaX * (mathManager.ticks) / Constants.MAX_TICKS), (y0 + deltaY * p.number / maxX));
            path2D.moveTo(hPopAtT[0].x, hPopAtT[0].y);
        }
        for (int i = 0; i <= mathManager.ticks; i++) {
            path2D.lineTo(
                    isG ? gPopAtT[i].x : hPopAtT[i].x,
                    isG ? gPopAtT[i].y : hPopAtT[i].y);
        }
        g.setColor(p.color);
        g.draw(path2D);
    }


    private void drawTimer(Graphics g) {
        g.drawString("t elapsed: " + mathManager.ticks / Constants.MS_PER_TICK,
                (int) (bounds.x - origin.x * 2), (int) bounds.y);
    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
