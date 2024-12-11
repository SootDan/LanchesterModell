package lanchester;

public class Population {
    // G and H
    public double number;
    // r and s
    public double attackStrength;

    // This never changes and is tied to number at initialization.
    // Useful for calculations and to not have to constantly invoke it.
    public final double numberAtStart;

    
    public Population(double number, double attackStrength) {
        this.number = number;
        this.attackStrength = attackStrength;

        numberAtStart = this.number;
    }

    public double popAtTime(Population p, double t) {
        return numberAtStart * Math.cosh(
                Math.sqrt(attackStrength * p.attackStrength) * t)
                - Math.sqrt(p.attackStrength / attackStrength) * p.numberAtStart * Math.sinh(
                Math.sqrt(attackStrength * p.attackStrength) * t);
    }
}
