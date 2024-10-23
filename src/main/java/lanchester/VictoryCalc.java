package lanchester;

public class VictoryCalc {
    // constant L
    public Population G;
    public Population H;

    public VictoryCalc(Population G, Population H) {
        this.G = G;
        this.H = H;
    }

    public String victory() {
        if (G.number == H.number && G.attackStrength == H.attackStrength)
            return "Tragisches Unentschieden";

        boolean calc = G.attackStrength * Math.pow(G.popAtTime(H, 0), 2) - H.attackStrength * Math.pow(H.popAtTime(G, 0), 2) > 0;
        return (calc) ? "G gewinnt" : "H gewinnt";
    }
}
