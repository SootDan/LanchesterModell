package gfx;

import lanchester.Population;

import javax.swing.*;
import java.awt.*;

public class CoordinateSystem extends JPanel {
    int width;
    int height;
    Population G;
    Population H;

    double minX = 1;
    double maxX;

    public CoordinateSystem(int width, int height,
                            Population G, Population H) {
        this.width = width;
        this.height = height;
        this.G = G;
        this.H = H;
        maxX = Math.max(G.number, H.number);
        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws coordinate system
        g.setColor(Color.BLACK);
        g.drawLine(10, height, width, height);
        g.drawLine(10, 10, 10, height);

        int coordArrow = 5;
        g.drawLine(width - coordArrow, height - coordArrow, width, height);
        g.drawLine(width - coordArrow, height + coordArrow, width, height);

        g.drawLine(10 - coordArrow, 10 + coordArrow, 10, 10);
        g.drawLine(10, 10, 10 + coordArrow, 10 + coordArrow);
    }
}
