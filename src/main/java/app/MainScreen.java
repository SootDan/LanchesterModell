package app;

import javax.swing.*;
import java.awt.*;

/**
 * This is the upper border of the simulation's main screen.
 */
public class MainScreen extends JPanel {
    //mainScreen.setSize(Constants.WIDTH, 864);
    //mainScreen.setBackground(Color.BLUE);
    public JPanel gPanel;
    public JPanel hPanel;

    public MainScreen() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.setPreferredSize(new Dimension(Constants.WIDTH, 614));
        this.setBackground(Color.BLACK);

        gPanel = new JPanel();
        gPanel.setBackground(Color.PINK);
        gPanel.setPreferredSize(new Dimension(640, 614));

        hPanel = new JPanel();
        hPanel.setBackground(Color.ORANGE);
        hPanel.setPreferredSize(new Dimension(640, 614));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(gPanel);
        this.add(hPanel);

    }

        public Dimension getPreferredSize() {
            return new Dimension(Constants.WIDTH, Constants.HEIGHT);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d;
            g2d = (Graphics2D) g;
            g.setColor(Color.BLACK);
            g.drawLine(Constants.WIDTH / 2, 0, Constants.WIDTH / 2, 614);

            g.fillOval(5,5,5,5);

        }
}


