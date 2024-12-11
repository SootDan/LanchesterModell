package gfx;

import lanchester.Population;
import lanchester.VictoryCalc;
import utils.TimerListener;
import utils.TimerManager;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class CoordinateSystem extends JPanel implements TimerListener {
    int width, height;
    public VictoryCalc victoryCalc;
    Population G, H;

    Vector2D origin, bounds, xDelta, yDelta;

    double maxX;
    int border = 25, arrow = 5;


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

        // x = 0; y = 0
        origin = new Vector2D(0.0, 0.0);
        origin = Vector2D.homogenousCoordinates(origin, border, border);

        // x = max; y = max
        bounds = new Vector2D(width, height);
        bounds = Vector2D.homogenousCoordinates(bounds, -border, -border);

        // Deltas for space between coordinate ticks
        xDelta = new Vector2D((bounds.x - origin.x) / Axis.X.incrementer, 0.0);
        yDelta = new Vector2D(0.0, (bounds.y - border * 2) / Axis.Y.incrementer);

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
        double tipX = axis == Axis.X ? bounds.x : origin.x;
        double tipY = axis == Axis.X ? bounds.y : origin.y;
        Line2D axes = new Line2D.Double(origin.x, bounds.y, tipX, tipY);
        g.draw(axes);

        // Arrows
        double baseX1 = tipX - arrow * (axis == Axis.X ? 1 : -1);
        double baseY1 = tipY + arrow;
        double baseX2 = tipX - arrow;
        double baseY2 = tipY - arrow * (axis == Axis.X ? 1 : -1);
        g.draw(new Line2D.Double(tipX, tipY, baseX1, baseY1));
        g.draw(new Line2D.Double(tipX, tipY, baseX2, baseY2));
    }


    /**
     * Draws the coordinate system including arrows.
     * Axis.X is p(t).
     * Axis.Y is p (sets max value to the highest pop size at p(0)).
     */
    public void drawCoordinateSystem(Graphics2D g, Axis axis) {
        // Writes the descriptors for the axes only for the first call
        if (axis == Axis.X) {
            g.drawString("p", (float) origin.x, (float) (origin.y - arrow));
            g.drawString("p(t)", (float) bounds.x, (float) bounds.y);
        }

        // Draws the actual ticks for each axis
        Vector2D delta = new Vector2D(
            axis == Axis.X ? (bounds.x - origin.x) / axis.incrementer : origin.x,
            axis == Axis.X ? bounds.y : (bounds.y - border * 2) / axis.incrementer
        );
        for (int i = 1; i <= axis.incrementer; i++) {
            Line2D line2D = new Line2D.Double(
                axis == Axis.X ? delta.x * i : delta.x - arrow,
                axis == Axis.X ? delta.y - arrow : bounds.y - i * delta.y,
                axis == Axis.X ? delta.x * i : delta.x + arrow,
                axis == Axis.X ? delta.y + arrow : bounds.y - i * delta.y
            );
            g.draw(line2D);

            String descriptor = axis == Axis.X ? "" + i : "" + (int) (i * (maxX / axis.incrementer));
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
    private void drawPopGraph(Graphics2D g, int ticks) {
        // TODO: Actually calculate how the graphs would look
        double Pt = victoryCalc.constantLZeroPop();

        double startG = G.numberAtStart / maxX;
        double startH = H.numberAtStart / maxX;
        double endG = G.popAtTime(H, 5) / maxX;
        double endH = H.popAtTime(G, Pt) / maxX;
        if (endG < 0.0) endG = 0.0;
        if (endH < 0.0) endH = 0.0;

        Vector2D gStart = new Vector2D(origin.x, bounds.y - (yDelta.y * Axis.Y.incrementer * startG));
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
