package utils;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TimerManager {
    // Creates a singleton instance that can be used by every class.
    private static TimerManager instance;
    private Timer timer;
    public static int ticks = 0;
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
     * Likes and Subscribes to TimerManager.
     */
    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }


    /**
     * Notifies all listeners that a tick happened.
     */
    private void notifyListeners() {
        ticks++;

        for (TimerListener listener : listeners)
            listener.onTimerTick();
    }
}

