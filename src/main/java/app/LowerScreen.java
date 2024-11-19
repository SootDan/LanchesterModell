package app;
import gfx.CoordinateSystem;
import lanchester.Population;
import utils.Constants;

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
    public CoordinateSystem coordinateSystem;

    public LowerScreen(Population G, Population H) {
        this.G = G;
        this.H = H;
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.LOWER_SCREEN_HEIGHT));

        gPanel = createPopPanel(G);
        hPanel = createPopPanel(H);
        coordinateSystem = new CoordinateSystem(G, H);

        setLayout(new BorderLayout());
        add(gPanel, BorderLayout.WEST);
        add(coordinateSystem, BorderLayout.CENTER);
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
}
