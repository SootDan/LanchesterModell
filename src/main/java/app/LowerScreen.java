package app;
import gfx.CoordinateSystem;
import lanchester.MathManager;
import lanchester.Population;
import utils.Constants;
import utils.TimerListener;
import utils.TimerManager;

import javax.swing.*;
import java.awt.*;

/**
 * Lower screen info bar.
 * Comes with stats for each population as well as a time graph.
 */
public class LowerScreen extends JPanel implements TimerListener {
    public JPanel gPanel, hPanel;
    public CoordinateSystem coordinateSystem;
    public MathManager mathManager;


    public LowerScreen(MathManager mathManager) {
        this.mathManager = mathManager;

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.LOWER_SCREEN_HEIGHT));

        gPanel = createPopPanel(mathManager.G);
        hPanel = createPopPanel(mathManager.H);
        coordinateSystem = new CoordinateSystem(mathManager);

        setLayout(new BorderLayout());
        add(gPanel, BorderLayout.WEST);
        add(coordinateSystem, BorderLayout.CENTER);
        add(hPanel, BorderLayout.EAST);

        TimerManager.getInstance().addSubscriber(this);

    }


    /**
     * Creates the information panels about each population to the bottom left/right.
     */
    public JPanel createPopPanel(Population pop) {
        JPanel popPanel = new JPanel();
        JLabel popLabel = new JLabel("<html>Population Size: " + pop.number
        + "<br>Attack Power: " + pop.attackStrength + "</html>");
        popLabel.setForeground(Color.WHITE);

        popPanel.add(popLabel);
        popPanel.setBackground(Color.DARK_GRAY);
        popPanel.setPreferredSize(new Dimension(Constants.LOWER_SCREEN_POP_WIDTH - 1, Constants.LOWER_SCREEN_HEIGHT));

        popPanel.putClientProperty("popLabel", popLabel);

        return popPanel;
    }


    @Override
    public void onTimerTick() {
        repaint();
    }
}
