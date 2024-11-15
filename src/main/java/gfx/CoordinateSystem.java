package gfx;

import lanchester.Population;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinateSystem extends JPanel {
    int width;
    int height;

    ArrayList<Vector2D> coordXAxis = new ArrayList<>();
    ArrayList<Vector2D> coordYAxis = new ArrayList<>();

    Population G;
    Population H;

    double minX = 0;
    double maxX;

    int border = 25;
    int coordArrow = 5;

    Vector2D coordOrigin;
    Vector2D coordBounds;


    public CoordinateSystem(Population G, Population H) {
        this.G = G;
        this.H = H;
        maxX = Math.max(G.number, H.number);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        width = getWidth();
        height = getHeight();
        coordOrigin = new Vector2D(border, border);
        coordBounds = new Vector2D(width - border, height - border);

        drawCoordinateSystem(g);
        drawPopGraph(g);
    }


    /**
     * Draws the coordinate system including arrows.
     */
    public void drawCoordinateSystem(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(coordOrigin.x, coordBounds.y, coordBounds.x, coordBounds.y);
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x, coordBounds.y);

        // Arrow x-axis
        g.drawLine(coordBounds.x - coordArrow, coordBounds.y - coordArrow, coordBounds.x, coordBounds.y);
        g.drawLine(coordBounds.x, coordBounds.y, coordBounds.x - coordArrow, coordBounds.y + coordArrow);

        // Arrow y-axis
        g.drawLine(coordOrigin.x - coordArrow, coordOrigin.y + coordArrow, coordOrigin.x, coordOrigin.y);
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x + coordArrow, coordOrigin.y + coordArrow);

        // Set five dimensions on the labelled x-axis
        int incrementer = (coordBounds.y - coordOrigin.y) / 6;
        double popSize = maxX / 5;
        g.drawString("p", coordOrigin.x, coordOrigin.y - coordArrow);

        for (int i = 1; i <= 5; i++) {
            coordXAxis.add(new Vector2D(coordOrigin.x, coordOrigin.y + incrementer * i));
            g.drawLine(coordXAxis.get(i - 1).x - coordArrow, coordXAxis.get(i - 1).y,
                    coordXAxis.get(i - 1).x + coordArrow, coordXAxis.get(i - 1).y);
            g.drawString(String.format("%.0f", maxX - (i - 1) * popSize),
                    coordXAxis.get(i - 1).x - coordArrow * 5, coordXAxis.get(i - 1).y + coordArrow);
        }

        // Set ten dimensions on the labelled y-axis
        incrementer = (coordBounds.x - coordOrigin.x) / 11;
        g.drawString("p(t)", coordBounds.x + coordArrow, coordBounds.y);
        for (int i = 1; i <= 10; i++) {
            coordYAxis.add(new Vector2D(coordOrigin.x + incrementer * i, coordBounds.y));
            g.drawLine(coordYAxis.get(i - 1).x, coordYAxis.get(i - 1).y - coordArrow,
                    coordYAxis.get(i - 1).x, coordYAxis.get(i - 1).y + coordArrow);
            g.drawString(30 * i +" Min", coordYAxis.get(i - 1).x, coordYAxis.get(i - 1).y  + coordArrow * 3);
        }
    }


    /**
     * Draws the population over time.
     */
    public void drawPopGraph(Graphics g) {
        // TODO: Actually calculate how the graphs would look
        g.setColor(Color.PINK);
        g.drawLine(coordYAxis.getLast().x, coordYAxis.getLast().y,
                coordXAxis.getFirst().x, coordXAxis.getFirst().y);

        g.setColor(Color.ORANGE);
        g.drawLine(coordYAxis.get(8).x, coordYAxis.get(8).y,
                coordXAxis.get(1).x, coordXAxis.get(1).y);
    }
}
