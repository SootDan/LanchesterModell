package app;

import lanchester.Population;

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
    public Population G;
    public Population H;

    public MainScreen(Population G, Population H) {
        this.G = G;
        this.H = H;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setSize(Constants.WIDTH, Constants.MAIN_SCREEN_HEIGHT);
        this.setBackground(Color.BLACK);

        gPanel = new JPanel();
        gPanel.setBackground(Color.PINK);
        gPanel.setPreferredSize(new Dimension(Constants.MAIN_SCREEN_PANEL_WIDTH, Constants.MAIN_SCREEN_HEIGHT));

        hPanel = new JPanel();
        hPanel.setBackground(Color.ORANGE);
        hPanel.setPreferredSize(new Dimension(Constants.MAIN_SCREEN_PANEL_WIDTH, Constants.MAIN_SCREEN_HEIGHT));

        this.setLayout(new GridLayout(1, 2, 2, 0));

        GraphicsMain g = new GraphicsMain(G, H);

        this.add(gPanel);
        this.add(hPanel);

    }

}


