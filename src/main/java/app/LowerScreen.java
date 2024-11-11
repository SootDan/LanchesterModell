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
        gPanel = new JPanel();
        gPanel.setBackground(Color.BLACK);
        gPanel.setSize(300, 216);

        hPanel = new JPanel();
        hPanel.setBackground(Color.RED);
        hPanel.setSize(300, 216);

        statPanel = new JPanel();
        statPanel.setBackground(Color.CYAN);
        statPanel.setSize(500, 216);

        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(400, 216));
        this.add(gPanel, BorderLayout.WEST);
        this.add(statPanel, BorderLayout.CENTER);
        this.add(hPanel, BorderLayout.EAST);
    }

    public void createPanels() {
        JLayeredPane layeredPane = new JLayeredPane();
        this.add(layeredPane);

        JPanel panelG = new JPanel();
        panelG.setBackground(Color.BLACK);
        panelG.setBounds(0, 0, 600, 216);
        this.setOpaque(true);
        panelG.setSize(300,216);
        //JLabel label = new JLabel("SUKA BLJAD IDI NAHUI");
        //panelG.add(label);
        layeredPane.add(panelG, JLayeredPane.PALETTE_LAYER);
        layeredPane.setVisible(true);

        //this.add(panelG);
        //this.add(panelH);
    }
}
