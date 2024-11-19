package gfx;

import lanchester.Population;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GraphicsMain extends JPanel {

    public Population P;
    private int diameter = 10;

    public GraphicsMain(Population P){
         this.P = P;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int xc = 0; //to change x position
        int yc = 0; //to change y position

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("fighter.png"));
        } catch (IOException e) {
        }

        g2d.drawImage(img, 0, 0, null);

        for (int i = 0; i <= P.number; i++) {
                g.fillOval(10 + xc, 10 + yc, diameter, diameter);
                //((Graphics2D) g).setStroke(new BasicStroke(2));
                //g.drawLine(15 + xc, 15 + yc, 15 + xc, 30 + yc);
                xc += 15; //spacing between the circles
                if(xc >= getWidth() - 30){ //next line
                    yc += 15;
                    xc = 0;
                }
        }
    }
}
