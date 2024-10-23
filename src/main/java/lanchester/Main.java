package lanchester;

public class Main {
    public static void main(String[] args) {
        Population G = new Population(400.0, 0.2);
        Population H = new Population(300.0, 0.1);
        double time = 0;
        double time1 = 1;

        Population G1 = new Population(300, 0.1);
        Population H1 = new Population(300, 0.1);

        double i = 0.0;
        while (G1.popAtTime(H1, i) > 0) {
            i++;
        }
        System.out.println(i);

        i = 0;
        while (H1.popAtTime(G1, i) > 0) {
            i++;
        }
        System.out.println(i);

        double L = G.attackStrength * Math.pow(G.popAtTime(H, time), 2) - H.attackStrength * Math.pow(H.popAtTime(G, time), 2);
        System.out.println(L);

        VictoryCalc victory = new VictoryCalc(G, H);
        String b = victory.victory();
        System.out.println(b);

        VictoryCalc victory2 = new VictoryCalc(G1, H1);
        System.out.println(victory2.victory());
    }
}