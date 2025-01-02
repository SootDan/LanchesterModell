package utils;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The global manager for the application time.
 * Handles ticks and the population over time.
 */
public class TimerManager {
    // Creates a singleton instance that can be used by every class.
    private static TimerManager instance;
    private Timer timer;
    private List<TimerListener> listeners;

    
    private TimerManager() {
        listeners = new ArrayList<>();
        timer = new Timer(Constants.MS_PER_TICK / Constants.TIMESCALE, e -> notifyListeners());
    }


    public static TimerManager getInstance() {
        if (instance == null)
            instance = new TimerManager();
        return instance;
    }


    public void start() {
        timer.start();
    }


    public void stop() {
        timer.stop();
    }


    /**
     * Likes and Subscribes to TimerManager, and never forgets to hit the bell.
     */
    public void addSubscriber(TimerListener listener) {
        listeners.add(listener);
    }


    /**
     * Notifies all listeners that a tick happened.
     */
    private void notifyListeners() {
        for (TimerListener listener : listeners)
            listener.onTimerTick();
    }
}

