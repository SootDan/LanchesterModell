package app;
import lanchester.Population;

import javax.swing.*;
import java.awt.*;

public class LowerScreen extends JPanel {
    public Population G;
    public Population H;
    public JPanel gPanel;
    public JPanel hPanel;
    public JPanel statPanel;

    public LowerScreen(Population G, Population H) {
        this.G = G;
        this.H = H;
        this.setSize(new Dimension(Constants.WIDTH, 216));
        gPanel = createPopPanel(G);
        hPanel = createPopPanel(H);

        statPanel = new JPanel();
        statPanel.setBackground(Color.CYAN);
        statPanel.setPreferredSize(new Dimension(768, 216));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(gPanel);
        this.add(statPanel);
        this.add(hPanel);
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
        popPanel.setPreferredSize((new Dimension(256, this.getHeight())));
        return popPanel;
    }
}
