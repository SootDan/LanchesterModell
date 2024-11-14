package gfx;

import lanchester.Population;

import javax.swing.*;
import java.awt.*;

public class CoordinateSystem extends JPanel {
    int width;
    int height;
    Population G;
    Population H;

    double minX = 0;
    double maxX;


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

        drawCoordinateSystem(g);
    }


    /**
     * Draws the coordinate system including arrows.
     */
    public void drawCoordinateSystem(Graphics g) {
        // Draws coordinate system
        int border = 15;
        int coordArrow = 5;

        Vector2D coordOrigin = new Vector2D(border, border);
        Vector2D coordBounds = new Vector2D(width - border, height - border);

        g.setColor(Color.BLACK);
        g.drawLine(coordOrigin.x, coordBounds.y, coordBounds.x, coordBounds.y);
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x, coordBounds.y);

        // Arrow x-axis
        g.drawLine(coordBounds.x - coordArrow, coordBounds.y - coordArrow, coordBounds.x, coordBounds.y);
        g.drawLine(coordBounds.x, coordBounds.y, coordBounds.x - coordArrow, coordBounds.y + coordArrow);

        //Arrow y-axis
        g.drawLine(coordOrigin.x - coordArrow, coordOrigin.y + coordArrow, coordOrigin.x, coordOrigin.y);
        g.drawLine(coordOrigin.x, coordOrigin.y, coordOrigin.x + coordArrow, coordOrigin.y + coordArrow);

        // Set four dimensions on the x-axis
        int incrementer = (coordBounds.y - coordOrigin.y) / 5;
        for (int i = 1; i < 5; i++)
            g.drawLine(coordOrigin.x - coordArrow, coordOrigin.y + incrementer * i,
                    coordOrigin.x + coordArrow, coordOrigin.y + incrementer * i);
    }
}
