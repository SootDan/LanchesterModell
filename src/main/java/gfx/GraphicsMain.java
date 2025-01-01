package gfx;

import lanchester.Population;
import utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GraphicsMain extends JPanel {

    public Population P;
    public Color BGcolor;
    private int diameter = 10;

    public GraphicsMain(Population P, Color BGcolor){
         this.P = P;
         this.BGcolor = BGcolor;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(BGcolor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        g.setColor(Color.black);
        int xc = 0; //to change x position
        int yc = 30; //to change y position, offset at 30 to prevent covering text

        for (int i = 0; i <= P.number; i++) {
                g.fillOval(10 + xc, 10 + yc, diameter, diameter);
                xc += 15; //spacing between the circles
                if(xc >= getWidth() - 30){ //next line
                    yc += 15;
                    xc = 0;
                }
        }
    }

}
