package app;

import lanchester.Population;
import utils.Constants;
import utils.TimerManager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import static java.lang.Integer.parseInt;

public class InputFrame extends JPanel {

    public JButton resetButton = new JButton("Reset");
    public JButton startButton = new JButton("Start");
    public JButton pauseButton = new JButton("Pause");

    public Population G;
    public Population H;

    private JPanel gPanel = new JPanel();
    private JPanel hPanel = new JPanel();
    private JPanel buttons = new JPanel();

    public InputFrame() {
        //To only allow input of numbers in Textfield
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);

        gPanel.setBackground(Constants.G_COLOR);
        hPanel.setBackground(Constants.H_COLOR);
        buttons.setBackground(Constants.SIDES_COLOR);

        this.setLayout(new GridLayout(1, 3));

        //create formatted Textfield with formatter from lines 24-28
        JFormattedTextField gPopNumber = new JFormattedTextField(formatter);
        JFormattedTextField hPopNumber = new JFormattedTextField(formatter);
        JFormattedTextField gAttack = new JFormattedTextField(formatter);
        JFormattedTextField hAttack = new JFormattedTextField(formatter);

        //otherwise the Fields are too tiny
        gPopNumber.setColumns(10);
        hPopNumber.setColumns(10);
        gAttack.setColumns(10);
        hAttack.setColumns(10);

        //add everything
        gPanel.add(new JLabel("<html><u>Population G</u>"));
        gPanel.add(new JLabel("Population Number"));
        gPanel.add(gPopNumber);
        gPanel.add(new JLabel("Attack Strength"));
        gPanel.add(gAttack);

        hPanel.add(new JLabel("<html><u>Population H</u>"));
        hPanel.add(new JLabel("Population Number"));
        hPanel.add(hPopNumber);
        hPanel.add(new JLabel("Attack Strength"));
        hPanel.add(hAttack);

        //buttons.setLayout(new GridLayout(3,1));
        buttons.add(startButton);
        buttons.add(resetButton);
        buttons.add(pauseButton);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.start(parseInt(gPopNumber.getText()), parseInt(gAttack.getText())/10.0,
                       parseInt(hPopNumber.getText()), parseInt(hAttack.getText())/10.0);
            }
        });

        //TODO: Find out why one doesn't get cleared and somehow the cleared comes back when pressing button again
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gPopNumber.setText("");
                gAttack.setText("");
                hPopNumber.setText("");
                hAttack.setText("");
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TimerManager.getInstance().isOn == true) {
                    TimerManager.getInstance().stop();
                    pauseButton.setText("Continue");
                }
                else {
                    TimerManager.getInstance().start();
                    pauseButton.setText("Pause");
                }
            }
        });

        this.add(gPanel);
        this.add(hPanel);
        this.add(buttons);
    }




}
