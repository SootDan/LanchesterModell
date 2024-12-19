package gfx;

import lanchester.Population;
import lanchester.VictoryCalc;
import utils.TimerListener;
import utils.TimerManager;
import utils.Vector2D;
import utils.VectorMath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class CoordinateSystem extends JPanel implements TimerListener {
    int width, height;
    public VictoryCalc victoryCalc;
    Population G, H;

    Vector2D origin, bounds, delta, xDelta, yDelta;

    double maxX;
    int border = 25, arrow = 5;

    // Minimum and maximum of either axis (doesn't take into account the graph -- just the valid points on the axis.)
    double x0, x1, y0, y1;

    public CoordinateSystem(Population G, Population H, VictoryCalc victoryCalc) {
        this.G = G;
        this.H = H;
        maxX = Math.max(G.numberAtStart, H.numberAtStart);

        // Check which pop loses. Draw coordinate system accordingly.
        this.victoryCalc = victoryCalc;

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
        origin = new Vector2D(x0, y0);
        bounds = new Vector2D(x1, y1);

        // Deltas for space between coordinate ticks
        delta = new Vector2D((x1 - x0) / Axis.X.incrementer, (y1 - y0) / Axis.Y.incrementer);
        xDelta = new Vector2D((bounds.x - origin.x) / Axis.X.incrementer, 0.0);
        yDelta = new Vector2D(0.0, (bounds.y - origin.y) / Axis.Y.incrementer);

        for (Axis axis: Axis.values()) {
            drawAxes(g2d, axis);
            drawCoordinateSystem(g2d, axis);
        }
        drawTimer(g);
        drawPopGraph(g2d, TimerManager.ticks);
    }


    /**
     * Draws the axes and their arrows.
     */
    private void drawAxes(Graphics2D g, Axis axis) {
        // Axes
        Line2D axes = new Line2D.Double(
                origin.x, origin.y,
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
        // Writes the descriptors for the axes only for the first call
        if (axis == Axis.X) {
            g.drawString("p", (float) origin.x, (float) (bounds.y - arrow));
            g.drawString("p(t)", (float) bounds.x, (float) origin.y - arrow);
        }

        // Draws the actual ticks for each axis
        /*Vector2D delta = new Vector2D(
            axis == Axis.X ? (bounds.x - origin.x) / axis.incrementer : origin.x,
            axis == Axis.X ? bounds.y : (bounds.y - border * 2) / axis.incrementer
        );*/
        /*for (int i = 0; i <= axis.incrementer; i++) {
            Line2D line2D = new Line2D.Double(
                axis == Axis.X ? delta.x * i : delta.x - arrow,
                axis == Axis.X ? delta.y - arrow : bounds.y - i * delta.y,
                axis == Axis.X ? delta.x * i : delta.x + arrow,
                axis == Axis.X ? delta.y + arrow : bounds.y - i * delta.y
            );
            g.draw(line2D);*/
        for (int i = 0; i < axis.incrementer; i++) {
            Line2D line2D = new Line2D.Double(
                    origin.x - arrow,
                    origin.y + delta.y * i,
                    origin.x + arrow,
                    origin.y + delta.y * i
            );
            g.draw(line2D);
/*
            String descriptor = axis == Axis.X ? "" + i : "" + (int) (i * (maxX / axis.incrementer));
            g.drawString(
                descriptor,
                (int) (axis == Axis.X ? line2D.getX1() : line2D.getX1() - arrow * 4),
                (int) (axis == Axis.X ? line2D.getY2() + arrow * 3 : line2D.getY2())
            );*/
        }
    }


    /**
     * Draws the population over time.
     */
    private void drawPopGraph(Graphics2D g, int ticks) {
        // TODO: Actually calculate how the graphs would look
        double Pt = victoryCalc.constantLZeroPop();


        double startG = G.numberAtStart / maxX;
        double startH = H.numberAtStart / maxX;
        double endG = G.popAtTime(H, 5) / maxX;
        double endH = H.popAtTime(G, Pt) / maxX;
        if (endG < 0.0) endG = 0.0;
        if (endH < 0.0) endH = 0.0;

        Vector2D start = new Vector2D(origin.x, bounds.y);
        Vector2D max = new Vector2D(
                VectorMath.homogenousCoordinates(origin, (xDelta.x) * 10.0 - origin.x, bounds.y).x,
                VectorMath.homogenousCoordinates(origin, 0.0,
                        bounds.y - (yDelta.y) * 5.0).y);
        Vector2D delta = new Vector2D(max.x - start.x, max.y - start.y);
        g.setColor(Color.RED);
        g.draw(new Line2D.Double(start.x, start.y, max.x, max.y));

        Vector2D gStart = new Vector2D(start.x, start.y);
        g.setColor(Color.GREEN);
        g.draw(new Line2D.Double(gStart.x, startG * max.y, max.x, max.y + endG * max.y));

        //Vector2D gStart = new Vector2D(origin.x, bounds.y - (yDelta.y * Axis.Y.incrementer * startG));
        // TODO: popAtTime isn't working. Why?
        Vector2D gEnd = new Vector2D(xDelta.x * Axis.X.incrementer, bounds.y - (
                yDelta.y * Axis.Y.incrementer * endG));
        Line2D gLine = new Line2D.Double(gStart.x, gStart.y, gEnd.x, gEnd.y);
        g.setColor(Color.PINK);
        g.draw(gLine);

        Vector2D hStart = new Vector2D(origin.x, bounds.y - (yDelta.y * Axis.Y.incrementer * startH));
        Vector2D hEnd = new Vector2D(xDelta.x * Axis.X.incrementer, bounds.y - (
                yDelta.y * Axis.Y.incrementer * endH));
        Line2D hLine = new Line2D.Double(hStart.x, hStart.y, hEnd.x, hEnd.y);
        g.setColor(Color.ORANGE);
        g.draw(hLine);
    }


    private void drawTimer(Graphics g) {
        // TODO: Add time system
        g.drawString("t elapsed: " + TimerManager.ticks, (int) (bounds.x - origin.x * 2), (int) origin.y);
    }


    private void updateTimer(Graphics g) {

    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
