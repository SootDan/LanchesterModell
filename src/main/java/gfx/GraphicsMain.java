package gfx;

import app.Constants;
import lanchester.Population;

import javax.swing.*;
import java.awt.*;


public class GraphicsMain extends JPanel {

    public Population P;
    public int width;
    public int height;
    private int diameter = 10;

    public GraphicsMain(Population P, int width, int height){
         this.P = P;
         this.width = width;
         this.height = height;
         setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int xc = 0; //to change x position
        int yc = 0; //to change y position
        for (int i = 0; i <= P.number; i++) {
                g.fillOval(10 + xc, 10 + yc, diameter, diameter);
                //((Graphics2D) g).setStroke(new BasicStroke(2));
                //g.drawLine(15 + xc, 15 + yc, 15 + xc, 30 + yc);
                xc += 15;
                if(xc >= width - 30){
                    yc += 15;
                    xc = 0;
                }
        }
    }
}
