package lanchester;

public class Main {
    public static void main(String[] args) {
        Population G = new Population(200.0, 1);
        Population H = new Population(100.0, 8);

        double i = 0.0;
        while (G.popAtTime(H, i) > 0 && H.popAtTime(G, i) > 0) {
            i += 0.001;
        }
        double d = G.popZero(H);

        System.out.println(Math.sqrt(G.attackStrength * H.attackStrength) * d);
        System.out.println(Math.cosh(Math.sqrt(G.attackStrength * H.attackStrength) * d));
        System.out.println(G.popAtTime(H, d));
    }
}