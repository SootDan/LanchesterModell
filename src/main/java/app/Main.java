package app;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
       JFrame frame = new JFrame("Lanchester-Modell");
       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       JLabel label = new JLabel("...");
       MainScreen mainScreen = new MainScreen();
       //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
       frame.add(mainScreen);
       LowerScreen lowerScreen = new LowerScreen();
       frame.add(lowerScreen);
       frame.getContentPane().add(label);
       //frame.pack();
       frame.setVisible(true);
    }

    public void paintCoordinateSystem(Graphics graphics) {
        graphics.setColor(Color.BLUE);
    }
}
