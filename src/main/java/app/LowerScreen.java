package app;
import gfx.CoordinateSystem;
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

        setLayout(new BorderLayout());
        add(gPanel, BorderLayout.WEST);
        add(statPanel, BorderLayout.CENTER);
        add(hPanel, BorderLayout.EAST);
    }

    /**
     * Creates the information panels about each population to the bottom left/right.
     */
    public JPanel createPopPanel(Population pop) {
        // TODO: Change pop size with timer
        JPanel popPanel = new JPanel();
        JLabel popLabel = new JLabel("<html>Population Size: " + pop.number
        + "<br>Attack Power: " + pop.attackStrength * 100 + "%</html>");
        popLabel.setForeground(Color.WHITE);

        popPanel.add(popLabel);
        popPanel.setBackground(Color.DARK_GRAY);
        popPanel.setPreferredSize(new Dimension(Constants.LOWER_SCREEN_POP_WIDTH - 1, Constants.LOWER_SCREEN_HEIGHT));
        return popPanel;
    }

    /***
     * Creates the stats at the bottom and updates them.
     */
    /*public CoordinateSystem createStatPanel() {
        CoordinateSystem statPanel = new CoordinateSystem(
                Constants.LOWER_SCREEN_STAT_WIDTH - 40, Constants.LOWER_SCREEN_HEIGHT - 40,
                G, H);
        statPanel.setBackground(Color.CYAN);
        statPanel.setPreferredSize(new Dimension(Constants.LOWER_SCREEN_STAT_WIDTH, Constants.LOWER_SCREEN_HEIGHT));
        //statPanel.add(new CoordinateSystem(statPanel.getWidth(), statPanel.getHeight()));
        return statPanel;
    }*/

    public JPanel createStatPanel() {
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.CYAN);
        statPanel.setLayout(new BorderLayout(0, 0));
        statPanel.setPreferredSize(new Dimension(
                Constants.LOWER_SCREEN_STAT_WIDTH, Constants.LOWER_SCREEN_HEIGHT
        ));

        CoordinateSystem coordinateSystem = new CoordinateSystem(G, H);
        statPanel.add(coordinateSystem);
        return statPanel;
    }
}
