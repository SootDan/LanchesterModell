package gfx;

import lanchester.Population;
import utils.TimerListener;
import utils.TimerManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinateSystem extends JPanel implements TimerListener {
    int width, height;
    Population G, H;
    final double startGPop, startHPop;

    ArrayList<Vector2D> coordXAxis = new ArrayList<>(), coordYAxis = new ArrayList<>();
    Vector2D coordOrigin, coordBounds;

    double maxX;
    int border = 25, coordArrow = 5;



    public CoordinateSystem(Population G, Population H) {
        this.G = G;
        this.H = H;
        startGPop = G.number;
        startHPop = H.number;
        maxX = Math.max(startGPop, startHPop);
        TimerManager.getInstance().addListener(this);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        width = getWidth();
        height = getHeight();
        coordOrigin = new Vector2D(border, border);
        coordBounds = new Vector2D(width - border, height - border);

        drawAxes(g);
        drawCoordinateSystem(g);
        drawTimer(g);
        drawPopGraph(g, TimerManager.ticks);
    }


    /**
     * Draws the axes and their arrows.
     */
    private void drawAxes(Graphics g) {
        // X-Axis
        g.drawLine(coordOrigin.x, coordBounds.y, coordBounds.x, coordBounds.y);
        g.drawLine(coordBounds.x - coordArrow, coordBounds.y - coordArrow, coordBounds.x, coordBounds.y);
        g.drawLine(coordBounds.x, coordBounds.y, coordBounds.x - coordArrow, coordBounds.y + coordArrow);

        // Y-Axis
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x, coordBounds.y);
        g.drawLine(coordOrigin.x - coordArrow, coordOrigin.y + coordArrow, coordOrigin.x, coordOrigin.y);
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x + coordArrow, coordOrigin.y + coordArrow);
    }


    /**
     * Draws the coordinate system including arrows.
     */
    public void drawCoordinateSystem(Graphics g) {
        // Set five dimensions on the labelled x-axis
        int incrementer = (coordBounds.y - coordOrigin.y) / 6;
        double popSize = maxX / 5;
        g.drawString("p", coordOrigin.x, coordOrigin.y - coordArrow);

        for (int i = 0; i <= 5; i++) {
            coordYAxis.add(new Vector2D(coordOrigin.x, coordBounds.y + -incrementer * i));
            g.drawLine(coordYAxis.get(i).x - coordArrow, coordYAxis.get(i).y,
                    coordYAxis.get(i).x + coordArrow, coordYAxis.get(i).y);
            g.drawString(String.format("%.0f", i * popSize),
                    coordYAxis.get(i).x - coordArrow * 5, coordYAxis.get(i).y + coordArrow);
        }

        // Set ten dimensions on the labelled y-axis
        incrementer = (coordBounds.x - coordOrigin.x) / 11;
        g.drawString("p(t)", coordBounds.x + coordArrow, coordBounds.y);
        for (int i = 0; i <= 10; i++) {
            coordXAxis.add(new Vector2D(coordOrigin.x + incrementer * i, coordBounds.y));
            g.drawLine(coordXAxis.get(i).x, coordXAxis.get(i).y - coordArrow,
                    coordXAxis.get(i).x, coordXAxis.get(i).y + coordArrow);
            g.drawString("" + i, coordXAxis.get(i).x, coordXAxis.get(i).y  + coordArrow * 3);
        }
    }


    /**
     * Draws the population over time.
     */
    private void drawPopGraph(Graphics g, int ticks) {
        // TODO: Actually calculate how the graphs would look
        double startG = 1.0 - startGPop / maxX;
        double startH = 1.0 - startHPop / maxX;

        // G
        g.setColor(Color.PINK);
        g.drawLine(coordXAxis.getFirst().x, (int) (
                        (double) (coordYAxis.getFirst().y - coordYAxis.getLast().y) * startG) + coordYAxis.getLast().y,
                coordXAxis.get(ticks).x, coordYAxis.getFirst().y);

        // H
        g.setColor(Color.ORANGE);
        g.drawLine(coordXAxis.getFirst().x, (int) (
                (double) (coordYAxis.getFirst().y - coordYAxis.getLast().y) * startH) + coordYAxis.getLast().y,
                coordXAxis.getLast().x, coordYAxis.getFirst().y);
    }

    private void drawTimer(Graphics g) {
        // TODO: Add time system
        g.drawString("t elapsed: " + TimerManager.ticks, coordXAxis.get(8).x, coordArrow * 2);
    }

    private void updateTimer(Graphics g) {

    }

    @Override
    public void onTimerTick() {
        repaint();
    }
}
