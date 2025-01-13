package lanchester;

public class VictoryCalc {
    public Population G, H;
    public double r, s, k;

    
    public VictoryCalc(Population G, Population H) {
        this.G = G;
        this.H = H;

        s = G.attackStrength;
        r = H.attackStrength;
        k = Math.sqrt(r * s);
    }


    /**
     * Returns the population number at time t for either pop.
     */
    public double[] popAtTime(double t) {
        // Formula taken from p. 9
        double Gt = G.numberAtStart * Math.cosh(k * t) - Math.sqrt(r / s) * H.numberAtStart *
                Math.sinh(k * t);
        double Ht = H.numberAtStart * Math.cosh(k * t) - Math.sqrt(s / r) * G.numberAtStart *
                Math.sinh(k * t);
        return new double[] {Gt, Ht};
    }


    /**
     * If L > 0, G wins; if L < 0, H wins; if L = 0, nobody wins.
     */
    public double constantL() {
        // Formula taken from p. 13
        return s * Math.pow(G.numberAtStart, 2) - r * Math.pow(H.numberAtStart, 2);
    }


    /**
     * Time at which one of the populations hits zero.
     */
    public double tPlus() {
        // Formula taken from p. 15
        double L = constantL();
        double denominator;

        if (L < 0.0)
            denominator = Math.pow(Math.tanh(G.numberAtStart / Math.sqrt(s / r) * H.numberAtStart), -1);
        else if (L > 0.0)
            denominator = Math.pow(Math.tanh(H.numberAtStart / Math.sqrt(r / s) * G.numberAtStart), -1);
        else
            denominator = 1.0;
        return denominator / k;
    }
}
