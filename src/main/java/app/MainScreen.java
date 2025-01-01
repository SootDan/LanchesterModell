package app;

import gfx.GraphicsMain;
import lanchester.Population;
import lanchester.VictoryCalc;
import utils.Constants;
import utils.TimerListener;
import utils.TimerManager;

import javax.swing.*;
import java.awt.*;

/**
 * This is the upper border of the simulation's main screen.
 */
public class MainScreen extends JPanel implements TimerListener {

    public JPanel gPanel, hPanel;
    public Population G, H;
    public int ticks = 0;

    public MainScreen(Population G, Population H) {
        this.G = G;
        this.H = H;
        this.setSize(Constants.WIDTH, Constants.MAIN_SCREEN_HEIGHT);

        gPanel = drawPanel(G, Color.PINK);
        gPanel.add(new JLabel("Population G"));

        hPanel = drawPanel(H, Color.ORANGE);
        hPanel.add(new JLabel("Population H"));

        this.setLayout(new GridLayout(1, 2, 0, 0));

        this.add(gPanel);
        this.add(hPanel);

    }

    public GraphicsMain drawPanel(Population P, Color BGcolor){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Constants.MAIN_SCREEN_PANEL_WIDTH, Constants.MAIN_SCREEN_HEIGHT));

        return new GraphicsMain(P, BGcolor);
    }

    public void updatePanel(JPanel panel, Population P) {
        panel.repaint();
    }


    @Override
    public void onTimerTick() {
        ticks++;
        G.number = Math.round(G.popAtTime(H, ticks));
        H.number = Math.round(H.popAtTime(G, ticks));
        updatePanel(gPanel, G);
        updatePanel(hPanel, H);
    }

}


