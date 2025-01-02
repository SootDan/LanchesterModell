package lanchester;

import utils.TimerListener;
import utils.TimerManager;

/**
 * Stores the calculation variables at each point.
 */
public class MathManager implements TimerListener {
    public Population G, H;
    public int ticks = 0;


    public MathManager(Population G, Population H) {
        this.G = G;
        this.H = H;

        TimerManager.getInstance().addSubscriber(this);
    }


    @Override
    public void onTimerTick() {
        ticks++;
        G.number = Math.round(G.popAtTime(H, ticks));
        H.number = Math.round(H.popAtTime(G, ticks));
    }
}
