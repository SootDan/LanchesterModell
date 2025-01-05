package lanchester;

import utils.Constants;
import utils.TimerListener;
import utils.TimerManager;

/**
 * Stores the calculation variables at each point.
 */
public class MathManager implements TimerListener {
    public Population G, H;
    public int ticks = 0;
    public VictoryCalc victoryCalc;


    public MathManager(Population G, Population H) {
        this.G = G;
        this.H = H;
        this.victoryCalc = new VictoryCalc(G, H);

        TimerManager.getInstance().addSubscriber(this);
    }


    /**
     * Updates the population numbers at a pace defined by MAX_TICKS.
     * Also stops the clock when the ticks exceed MAX_TICKS.
     */
    @Override
    public void onTimerTick() {
        double increment = victoryCalc.tPlus() / (double) Constants.MAX_TICKS;
        ticks++;
        G.number = G.popAtTime(H, increment * ticks);
        H.number = H.popAtTime(G, increment * ticks);

        if (ticks >= Constants.MAX_TICKS)
            TimerManager.getInstance().stop();
    }
}
