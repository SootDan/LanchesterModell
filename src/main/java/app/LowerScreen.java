package app;
import lanchester.Population;

import javax.swing.*;
import java.awt.*;

/**
 * Lower screen info bar.
 * Comes with stats for each population as well as a time graph.
 */
public class LowerScreen extends JPanel {
    public Population G;
    public Population H;
    public JPanel gPanel;
    public JPanel hPanel;
    public JPanel statPanel;

    public LowerScreen(Population G, Population H) {
        this.G = G;
        this.H = H;
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.LOWER_SCREEN_HEIGHT));

        gPanel = createPopPanel(G);
        hPanel = createPopPanel(H);
        statPanel = createStatPanel();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(gPanel);
        add(statPanel);
        add(hPanel);
    }

    /**
     * Creates the information panels about each population to the bottom left/right.
     */
    public JPanel createPopPanel(Population pop) {
        // TODO: Change pop size with timer
        JPanel popPanel = new JPanel();
        JLabel popLabel = new JLabel("<html>Population Size: " + pop.number
        + "<br>Attack Power: " + pop.attackStrength + "</html>");
        popLabel.setForeground(Color.WHITE);

        popPanel.add(popLabel);
        popPanel.setBackground(Color.DARK_GRAY);
        popPanel.setPreferredSize((new Dimension(Constants.LOWER_SCREEN_POP_WIDTH, Constants.LOWER_SCREEN_HEIGHT)));
        return popPanel;
    }

    /***
     * Creates the stats at the bottom and updates them.
     */
    public JPanel createStatPanel() {
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.CYAN);
        statPanel.setPreferredSize(new Dimension(Constants.LOWER_SCREEN_STAT_WIDTH, Constants.LOWER_SCREEN_HEIGHT));
        return statPanel;
    }
}
