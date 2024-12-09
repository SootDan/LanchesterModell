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

    ArrayList<Vector2D> coordXAxis = new ArrayList<>(), coordYAxis = new ArrayList<>();
    Vector2D coordOrigin, coordBounds;

    double maxX;
    int border = 25, coordArrow = 5;


    public CoordinateSystem(Population G, Population H, VictoryCalc victoryCalc) {
        this.G = G;
        this.H = H;
        startGPop = G.number;
        startHPop = H.number;
        maxX = Math.max(startGPop, startHPop);

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
        coordOrigin = new Vector2D(border, border);
        coordBounds = new Vector2D(width - border, height - border);

        drawAxes(g2d);
        drawCoordinateSystem(g);
        drawTimer(g);
        drawPopGraph(g2d, TimerManager.ticks);
    }


    /**
     * Draws the axes and their arrows.
     */
    private void drawAxes(Graphics2D g) {
        Shape[] xAxis = {
                new Line2D.Double(coordOrigin.x, coordBounds.y, coordBounds.x, coordBounds.y),
                new Line2D.Double(coordBounds.x - coordArrow, coordBounds.y + coordArrow, coordBounds.x, coordBounds.y),
                new Line2D.Double(coordBounds.x - coordArrow, coordBounds.y - coordArrow, coordBounds.x, coordBounds.y)
        };
        for (Shape shape: xAxis)
            g.draw(shape);

        Shape[] yAxis = {
                new Line2D.Double(coordOrigin.x, coordOrigin.y, coordOrigin.x, coordBounds.y),
                new Line2D.Double(coordOrigin.x - coordArrow, coordOrigin.y + coordArrow, coordOrigin.x, coordOrigin.y),
                new Line2D.Double(coordOrigin.x + coordArrow, coordOrigin.y + coordArrow, coordOrigin.x, coordOrigin.y)
        };
        for (Shape shape: yAxis)
            g.draw(shape);
    }


    /**
     * Draws the coordinate system including arrows.
     */
    public void drawCoordinateSystem(Graphics g) {
        // Set five dimensions on the labelled x-axis
        int incrementer = (int) (coordBounds.y - coordOrigin.y) / 6;
        double popSize = maxX / 5;
        g.drawString("p", (int) coordOrigin.x, (int) coordOrigin.y - coordArrow);

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
        }
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

        Vector2D gStart = new Vector2D(coordXAxis.getFirst().x, (coordYAxis.getFirst().y - coordYAxis.getLast().y) * startG + coordYAxis.getLast().y);
        Vector2D gEnd = new Vector2D(coordXAxis.getLast().x, (coordYAxis.getFirst().y - coordYAxis.getLast().y) * endG + coordYAxis.getFirst().y);
        Shape gVector = new Line2D.Double(gStart.x, gStart.y, gEnd.x, gEnd.y);
        g.setColor(Color.PINK);
        g.draw(gVector);

        Vector2D hStart = new Vector2D(coordXAxis.getFirst().x, (coordYAxis.getFirst().y - coordYAxis.getLast().y) * startH + coordYAxis.getLast().y);
        Vector2D hEnd = new Vector2D(coordXAxis.getLast().x, (coordYAxis.getFirst().y - coordYAxis.getLast().y) * endH + coordYAxis.getFirst().y);
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
        g.drawString("t elapsed: " + TimerManager.ticks, (int) coordXAxis.get(8).x, coordArrow * 2);
    }


    private void updateTimer(Graphics g) {

    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
