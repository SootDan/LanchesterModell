package gfx;

import lanchester.Population;

import javax.swing.*;
import java.awt.*;


public class GraphicsMain extends JPanel {

    public Population P;
    private final int diameter = 12;
    private final int halfDiameter = diameter/2; //to help stick figure calculations

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
        g2d.setStroke(new BasicStroke(2.5f));
        int xc = 0; //to change x position
        int yc = 30; //to change y position, offset at 30 to prevent covering text

        for (int i = 0; i <= P.number - 1; i++) { //-1 because otherwise there is one circle too many

            int x = xc + 10;
            int y = yc + 10;

            //head
            g.fillOval(x, y, diameter, diameter);
            //torso
            g.drawLine(x + halfDiameter, y + halfDiameter,
                    x + halfDiameter, y + diameter * 2);
            //arms
            g.drawLine(x, y + diameter + halfDiameter,
                    x + diameter, y + diameter + halfDiameter);
            //left leg
            g.drawLine(x + halfDiameter, y + diameter * 2,
                    x, y + diameter * 2 + halfDiameter);
            //right leg
            g.drawLine(x + halfDiameter, y + diameter * 2,
                    x + diameter, y + diameter * 2 + halfDiameter);

            xc += diameter + 5; //spacing between the circles
            if(xc >= getWidth() - diameter * 2){ //next line
                yc += diameter * 3;
                xc = 0;
            }
        }
    }

}
