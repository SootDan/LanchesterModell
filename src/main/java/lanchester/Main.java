package lanchester;

/**
 * This class exists solely to test VictoryCalc and Population.
 * Feel free to ignore it.
 */
public class Main {
    public static void main(String[] args) {
        Population G = new Population(200.0, 1);
        Population H = new Population(100.0, 8);

        VictoryCalc victoryCalc = new VictoryCalc(G, H);
        for (double t = 0; t < 1.0; t += 0.01) {
            double[] vc = victoryCalc.popAtTime(t);
            if (vc[0] <= 0.0 || vc[1] <= 0.0)
                break;

        }
    }
}