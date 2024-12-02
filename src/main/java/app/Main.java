package app;
import lanchester.Population;
import utils.Constants;
import utils.TimerListener;
import utils.TimerManager;

import javax.swing.*;
import java.awt.*;

public class Main {
   static Population G = new Population(400, 0.3);
   static Population H = new Population(300, 0.5);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lanchester-Modell");
        JFrame input = new JFrame("Input Frame");

       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       input.setSize(450, 200);
       input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       input.setTitle("Input your Populations");

       InputFrame inputFrame = new InputFrame();
       input.add(inputFrame);
       input.setVisible(true);

       /*
        * Starts global timer.
        * To add your class as an event listener, implement TimerListener and add an onTimerTick() method.
        */
       TimerManager.getInstance().start();

       MainScreen mainScreen = new MainScreen(G, H);
       LowerScreen lowerScreen = new LowerScreen(G, H);

       frame.setLayout(new BorderLayout());
       frame.add(mainScreen, BorderLayout.CENTER);
       frame.add(lowerScreen, BorderLayout.SOUTH);

       frame.setVisible(true);
    }
}