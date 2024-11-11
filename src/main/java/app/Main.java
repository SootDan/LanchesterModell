package app;
import lanchester.Population;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lanchester-Modell");
        JLayeredPane jLayeredPane = new JLayeredPane();

       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       MainScreen mainScreen = new MainScreen();
       LowerScreen lowerScreen = new LowerScreen(
               new Population(400, 0.3),
               new Population(300, 0.5));
       frame.add(mainScreen);
       frame.add(lowerScreen);

       frame.setVisible(true);
    }

    public void paintCoordinateSystem(Graphics graphics) {
        graphics.setColor(Color.BLUE);
    }
}
