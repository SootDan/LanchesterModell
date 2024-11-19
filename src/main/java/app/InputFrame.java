package app;

import lanchester.Population;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

public class InputFrame extends JPanel {

    public JButton clearButton = new JButton("Clear");
    public JButton startButton = new JButton("Start");

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

        gPanel.setBackground(Color.PINK);
        hPanel.setBackground(Color.ORANGE);
        buttons.setBackground(Color.DARK_GRAY);

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

        gPanel.add(gPopNumber);
        gPanel.add(gAttack);
        hPanel.add(hPopNumber);
        hPanel.add(hAttack);
        buttons.add(startButton);
        buttons.add(clearButton);

        //TODO: button creates the Populations that will be used in rest of code
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(gPanel);
        this.add(hPanel);
        this.add(buttons);
    }




}
