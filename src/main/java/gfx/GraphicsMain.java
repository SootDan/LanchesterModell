package gfx;

import lanchester.Population;

import javax.swing.*;
import java.awt.*;


public class GraphicsMain extends JPanel {

    public Population P;
    private final int diameter = 15;

    public GraphicsMain(Population P){
         this.P = P;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(P.color);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        g.setColor(Color.black);
        int xc = 0; //to change x position
        int yc = 30; //to change y position, offset at 30 to prevent covering text

        for (int i = 0; i <= P.number - 1; i++) { //-1 because otherwise there is one circle too many
                g.fillOval(10 + xc, 10 + yc, diameter, diameter);
                xc += diameter + 5; //spacing between the circles
                if(xc >= getWidth() - diameter * 2){ //next line
                    yc += diameter + 5;
                    xc = 0;
                }
        }
    }

}
