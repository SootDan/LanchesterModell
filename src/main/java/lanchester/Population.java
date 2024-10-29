package lanchester;

public class Population {
    // G and H
    double number;
    // r and s
    double attackStrength;

    public Population(double number, double attackStrength) {
        this.number = number;
        this.attackStrength = attackStrength;
    }

    public double popAtTime(Population p, double t) {
        // Number of the population G(t) at time t
        // always number at t = 0
        double bothAttack = Math.sqrt(attackStrength * p.attackStrength) * t;
        return number * Math.cosh(bothAttack) -
                Math.sqrt(attackStrength / p.attackStrength) * p.number *
                Math.sinh(bothAttack);
    }

    public double popZero(Population p) {
        double bothAttack = Math.sqrt(attackStrength * p.attackStrength);
        double absoluteZero = bothAttack * p.number * Math.sinh(bothAttack);
        return absoluteZero;
    }
}
