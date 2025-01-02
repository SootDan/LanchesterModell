package app;

import gfx.GraphicsMain;
import lanchester.MathManager;
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
    public MathManager mathManager;

    public MainScreen(MathManager mathManager) {
        this.mathManager = mathManager;
        this.G = mathManager.G;
        this.H = mathManager.H;
        this.setSize(Constants.WIDTH, Constants.MAIN_SCREEN_HEIGHT);

        gPanel = drawPanel(G, G.color);
        gPanel.add(new JLabel("Population G"));

        hPanel = drawPanel(H, H.color);
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


    @Override
    public void onTimerTick() {
        gPanel.repaint();
        hPanel.repaint();
    }

}


