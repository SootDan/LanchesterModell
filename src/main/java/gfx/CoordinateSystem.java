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
        g.setColor(Color.BLACK);
        g.drawLine(border, height - border, width - border, height - border);
        g.drawLine(border, border, border, height - border);

        int coordArrow = 5;
        // Arrow x-axis
        g.drawLine(width - border - coordArrow,
                height - border - coordArrow,
                width - border,
                height - border);
        g.drawLine(width - border,
                height - border,
                width - border - coordArrow,
                height - border + coordArrow);

        //Arrow y-axis
        g.drawLine(border - coordArrow, border + coordArrow, border, border);
        g.drawLine(border, border, border + coordArrow, border + coordArrow);
    }


    /**
     *
     * @param g
     */
    public void drawCoordinateDescription(Graphics g) {

    }
}
