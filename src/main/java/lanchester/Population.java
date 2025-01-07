package lanchester;
import utils.TimerListener;
import utils.TimerManager;

import java.awt.*;

public class Population {
    // G and H
    public double number;
    // r and s
    public double attackStrength;
    public Color color;

    // This never changes and is tied to number at initialization.
    // Useful for calculations and to not have to constantly invoke it.
    public final double numberAtStart;

    
    public Population(double number, double attackStrength, Color color) {
        this.number = number;
        this.attackStrength = attackStrength;
        this.color = color;

        numberAtStart = this.number;
    }


    public double popAtTime(Population p, double t) {
        double newPop = numberAtStart * Math.cosh(
                Math.sqrt(attackStrength * p.attackStrength) * t)
                - Math.sqrt(p.attackStrength / attackStrength) * p.numberAtStart * Math.sinh(
                Math.sqrt(attackStrength * p.attackStrength) * t);
        if (newPop <= 0.0)
            newPop = 0.0;
        return newPop;
    }
}
