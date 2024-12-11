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
    final double startGPop, startHPop;

    ArrayList<Vector2D> xAxis = new ArrayList<>(), yAxis = new ArrayList<>();
    Vector2D origin, bounds, arrow;

    double maxX;
    int border = 25;


    public CoordinateSystem(Population G, Population H, VictoryCalc victoryCalc) {
        this.G = G;
        this.H = H;
        startGPop = G.number;
        startHPop = H.number;
        maxX = Math.max(startGPop, startHPop);
        arrow = new Vector2D(5.0, 5.0);

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

        for (Axis axis: Axis.values())
            drawAxes(g2d, axis);
        drawCoordinateSystem(g2d, 10, 5);
        //drawTimer(g);
        //drawPopGraph(g2d, TimerManager.ticks);
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
        double baseX1 = tipX - arrow.x * (axis == Axis.X ? 1 : -1);
        double baseY1 = tipY + arrow.y;
        double baseX2 = tipX - arrow.x;
        double baseY2 = tipY - arrow.y * (axis == Axis.X ? 1 : -1);
        g.draw(new Line2D.Double(tipX, tipY, baseX1, baseY1));
        g.draw(new Line2D.Double(tipX, tipY, baseX2, baseY2));
    }


    /**
     * Draws the coordinate system including arrows.
     */
    public void drawCoordinateSystem(Graphics2D g, double xIncrement, double yIncrement) {
        // x-axis
        Vector2D xDelta = new Vector2D((bounds.x - origin.x) / xIncrement, bounds.y);
        for (int i = 1; i <= xIncrement; i++) {
            Line2D line2D = new Line2D.Double(xDelta.x * i, xDelta.y - arrow.y,
                    xDelta.x * i, xDelta.y + arrow.y);
            g.draw(line2D);
            g.drawString("" + i, (int)line2D.getX1(), (int)(line2D.getY2() + arrow.y * 2));
        }

        // y-axis
        Vector2D yDelta = new Vector2D(origin.x, bounds.y - (bounds.y - origin.y) / yIncrement);
        g.draw(new Line2D.Double(yDelta.x - arrow.x, yDelta.y,
                yDelta.x + arrow.x, yDelta.y));
        for (int i = 1; i <= yIncrement; i++) {
            Line2D line2D = new Line2D.Double(yDelta.x - arrow.x, yDelta.y * i,
                    yDelta.x + arrow.x, yDelta.y * i);
            g.draw(line2D);
        }
       // g.draw(line2D);
        /*
        for (int i = 0; i <= 5; i++) {
            coordYAxis.add(new Vector2D(coordOrigin.x, coordBounds.y + -incrementer * i));
            g.drawLine((int) coordYAxis.get(i).x - coordArrow, (int) coordYAxis.get(i).y,
                    (int) coordYAxis.get(i).x + coordArrow, (int) coordYAxis.get(i).y);
            g.drawString(String.format("" + i * popSize),
                    (int) coordYAxis.get(i).x - coordArrow * 5, (int) coordYAxis.get(i).y + coordArrow);
        }

        // Set ten dimensions on the labelled y-axis
        incrementer = (int) (coordBounds.x - coordOrigin.x) / 11;
        g.drawString("p(t)", (int) coordBounds.x + coordArrow, (int) coordBounds.y);
        for (int i = 0; i <= 10; i++) {
            coordXAxis.add(new Vector2D(coordOrigin.x + incrementer * i, coordBounds.y));
            g.drawLine((int) coordXAxis.get(i).x, (int) coordXAxis.get(i).y - coordArrow,
                    (int) coordXAxis.get(i).x, (int) coordXAxis.get(i).y + coordArrow);
            g.drawString("" + i, (int) coordXAxis.get(i).x, (int) coordXAxis.get(i).y  + coordArrow * 3);
        }*/

        // Writes the descriptors for the axes
        g.drawString("p", (float) origin.x, (float) (origin.y - arrow.y));
    }


    /**
     * Draws the population over time.
     */
    private void drawPopGraph(Graphics2D g, int ticks) {
        // TODO: Actually calculate how the graphs would look
        double Pt = victoryCalc.constantLZeroPop();

        double startG = 1.0 - startGPop / maxX;
        System.out.println(startG);
        double endG = victoryCalc.popAtTime(Pt)[0] / maxX;
        System.out.println(endG);
        if (endG < 0.0) endG = 0.0;

        double startH = 1.0 - startHPop / maxX;
        double endH = 1.0 - victoryCalc.popAtTime(Pt)[1] / maxX;
        if (endH < 0.0) endH = 0.0;
        //System.out.println(endH);

        Vector2D gStart = new Vector2D(xAxis.getFirst().x, (yAxis.getFirst().y - yAxis.getLast().y) * startG + yAxis.getLast().y);
        Vector2D gEnd = new Vector2D(xAxis.getLast().x, (yAxis.getFirst().y - yAxis.getLast().y) * endG + yAxis.getFirst().y);
        Shape gVector = new Line2D.Double(gStart.x, gStart.y, gEnd.x, gEnd.y);
        g.setColor(Color.PINK);
        g.draw(gVector);

        Vector2D hStart = new Vector2D(xAxis.getFirst().x, (yAxis.getFirst().y - yAxis.getLast().y) * startH + yAxis.getLast().y);
        Vector2D hEnd = new Vector2D(xAxis.getLast().x, (yAxis.getFirst().y - yAxis.getLast().y) * endH + yAxis.getFirst().y);
        Shape hVector = new Line2D.Double(hStart.x, hStart.y, hEnd.x, hEnd.y);
        g.setColor(Color.ORANGE);
        g.draw(hVector);



        // G
        /*g.setColor(Color.PINK);
        g.drawLine((int) coordXAxis.getFirst().x, (int) ((coordYAxis.getFirst().y - coordYAxis.getLast().y) * startG
                + coordYAxis.getLast().y),
                (int) coordXAxis.get(ticks).x, (int) coordYAxis.getFirst().y);*/

        // H
        /*g.setColor(Color.ORANGE);
        g.drawLine((int) coordXAxis.getFirst().x, (int) (
                (coordYAxis.getFirst().y - coordYAxis.getLast().y) * startH) + (int) coordYAxis.getLast().y,
                (int) coordXAxis.getLast().x, (int) coordYAxis.getFirst().y);*/
    }


    private void drawTimer(Graphics g) {
        // TODO: Add time system
        //g.drawString("t elapsed: " + TimerManager.ticks, (int) coordXAxis.get(8).x, coordArrow * 2);
    }


    private void updateTimer(Graphics g) {

    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
