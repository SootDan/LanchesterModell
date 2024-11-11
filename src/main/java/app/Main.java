package app;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
       JFrame frame = new JFrame("Lanchester-Modell");
       frame.setMinimumSize(windowSize());
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JLabel label = new JLabel("...");
       frame.getContentPane().add(label);
       frame.pack();
       frame.setVisible(true);
    }

    public static Dimension windowSize() {
        return new Dimension(1000, 400);
    }

    public void paintCoordinateSystem(Graphics graphics) {
        graphics.setColor(Color.BLUE);
    }
}
