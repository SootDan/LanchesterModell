package app;
import lanchester.Population;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lanchester-Modell");

       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       Population G = new Population(400, 0.3);
       Population H = new Population(300, 0.5);
       MainScreen mainScreen = new MainScreen(G, H);
       LowerScreen lowerScreen = new LowerScreen(G, H);

       frame.setLayout(new BorderLayout());
       frame.add(mainScreen, BorderLayout.CENTER);
       frame.add(lowerScreen, BorderLayout.SOUTH);

       frame.setVisible(true);
    }
}
