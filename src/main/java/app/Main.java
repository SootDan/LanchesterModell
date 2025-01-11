package app;
import lanchester.MathManager;
import lanchester.Population;
import utils.Constants;
import utils.TimerManager;

import javax.swing.*;
import java.awt.*;

public class Main {
   static Population G = new Population(200, 1, Color.PINK);
   static Population H = new Population(100, 8, Color.LIGHT_GRAY);

   //TODO: maybe we can open input frame again after simulation is done, to be able to input other numbers without restarting application
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

       Population G2 = new Population(gNumber, gAttackStrength, Constants.G_COLOR);
       Population H2 = new Population(hNumber, hAttackStrength, Constants.H_COLOR);

       JFrame frame = new JFrame("Lanchester-Modell");

       frame.setSize(Constants.WIDTH, Constants.HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Lanchester-Modell");

       // Get the necessary calculations from VictoryCalc.
       MathManager mathManager = new MathManager(G2, H2);
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