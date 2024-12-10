package app;
import gfx.CoordinateSystem;
import lanchester.Population;
import lanchester.VictoryCalc;
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
    public Population G, H;
    public double GStartPop, HStartPop;
    public JPanel gPanel, hPanel;
    public CoordinateSystem coordinateSystem;
    public VictoryCalc victoryCalc;

    public LowerScreen(Population G, Population H, VictoryCalc victoryCalc) {
        this.G = G;
        this.H = H;
        this.victoryCalc = victoryCalc;
        GStartPop = G.number;
        HStartPop = H.number;

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.LOWER_SCREEN_HEIGHT));

        gPanel = createPopPanel(G);
        hPanel = createPopPanel(H);
        coordinateSystem = new CoordinateSystem(G, H, this.victoryCalc);

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
        + "<br>Attack Power: " + pop.attackStrength * 100 + "%</html>");
        popLabel.setForeground(Color.WHITE);

        popPanel.add(popLabel);
        popPanel.setBackground(Color.DARK_GRAY);
        popPanel.setPreferredSize(new Dimension(Constants.LOWER_SCREEN_POP_WIDTH - 1, Constants.LOWER_SCREEN_HEIGHT));

        popPanel.putClientProperty("popLabel", popLabel);

        return popPanel;
    }

    /**
     * Dynamically redraws the panels with their given populations.
     */
    public void updatePopPanel(JPanel popPanel, Population pop, double startPop) {
    JLabel popLabel = (JLabel) popPanel.getClientProperty("popLabel");
    double popDiff = startPop - pop.number;

    popLabel.setText("<html>Population Size: " + pop.number + " (- " + popDiff
            + ")<br>Attack Power: " + pop.attackStrength * 100 + "%</html>");
    popPanel.repaint();
    }


    @Override
    public void onTimerTick() {
        // TODO: Add actual maths to this.
        G.number -= 10;
        H.number -= 5;
        SwingUtilities.invokeLater(() -> {
            updatePopPanel(gPanel, G, GStartPop);
            updatePopPanel(hPanel, H, HStartPop);
        });
    }
}
