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

    public String victory() {
        // Formula taken from p. 14
        double L = constantL();
        if (L == 0.0) {
            if (G.numberAtStart == H.numberAtStart && r == s)
                return "Tragisches Unentschieden";
            return (G.numberAtStart > H.numberAtStart ? "Pyrrhussieg für G" : "Pyrrhussieg für H");
        }
        return (L > 0.0 ? "G gewinnt" : "H gewinnt");
    }


    public double[] popAtTime(double t) {
        // Formula taken from p. 9
        double a = (G.numberAtStart - (r / k) * H.numberAtStart) / 2.0;
        double b = (G.numberAtStart + (r / k) * H.numberAtStart) / 2.0;
        double c = (H.numberAtStart - (s / k) * G.numberAtStart) / 2.0;
        double d = (H.numberAtStart + (s / k) * G.numberAtStart) / 2.0;

        double Gt = a * Math.exp(k * t) + b * Math.exp(-k * t);
        double Ht = c * Math.exp(k * t) + d * Math.exp(-k * t);
        return new double[] {Gt, Ht};
    }


    /**
     * If L > 0, G wins; if L < 0, H wins; if L = 0, nobody wins.
     */
    public double constantL() {
        // Formula taken from p. 13
        return s * Math.pow(G.numberAtStart, 2) - r * Math.pow(H.numberAtStart, 2);
    }
}
