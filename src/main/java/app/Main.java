package app;
import lanchester.MathManager;
import lanchester.Population;
import utils.Constants;
import utils.TimerManager;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
       JFrame input = new JFrame("Input Frame");

       input.setSize(450, 200);
       input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       input.setTitle("Input your Populations");

       InputFrame inputFrame = new InputFrame();
       input.add(inputFrame);
       input.setVisible(true);
    }

    public static void start(int gNumber, double gAttackStrength, int hNumber, double hAttackStrength){

       Population G = new Population(gNumber, gAttackStrength, Constants.G_COLOR);
       Population H = new Population(hNumber, hAttackStrength, Constants.H_COLOR);

       JFrame frame = new JFrame("Lanchester-Modell");

       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       // Get the necessary calculations from VictoryCalc.
       MathManager mathManager = new MathManager(G, H);
       /*
        * Starts global timer.
        * To add your class as an event listener, implement TimerListener and add an onTimerTick() method.
        */
       TimerManager.getInstance().start();

       MainScreen mainScreen = new MainScreen(mathManager);
       LowerScreen lowerScreen = new LowerScreen(mathManager);

       frame.setLayout(new BorderLayout());
       frame.add(mainScreen, BorderLayout.CENTER);
       frame.add(lowerScreen, BorderLayout.SOUTH);

       frame.setVisible(true);

    }
}