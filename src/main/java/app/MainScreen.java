package app;

import gfx.GraphicsMain;
import lanchester.Population;
import utils.Constants;

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
        this.setSize(Constants.WIDTH, Constants.MAIN_SCREEN_HEIGHT);
        this.setBackground(Color.BLACK);

        gPanel = drawPanel(G);
        gPanel.setBackground(Color.PINK);

        hPanel = drawPanel(H);
        hPanel.setBackground(Color.ORANGE);

        this.setLayout(new GridLayout(1, 2, 0, 0));

        this.add(gPanel);
        this.add(hPanel);

    }

    public GraphicsMain drawPanel(Population p){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Constants.MAIN_SCREEN_PANEL_WIDTH, Constants.MAIN_SCREEN_HEIGHT));
        panel.setPreferredSize(new Dimension(Constants.MAIN_SCREEN_PANEL_WIDTH, Constants.MAIN_SCREEN_HEIGHT));
        GraphicsMain draw = new GraphicsMain(p);

        return draw;
    }

}


