package utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationTime extends Thread {
    public double timeSinceStart = 0;
    public long currentTime = 0;
    public long formerTime = 0;
    private double timeScale = Constants.TIMESCALE;
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(true);

    public ApplicationTime() {

    }




    @Override
    public void run() {
        formerTime = System.currentTimeMillis();
        while (running.get()) {
            currentTime = System.currentTimeMillis();
            if (!isPaused.get()) {
                timeSinceStart += (currentTime - formerTime) * timeScale;
            }
            formerTime = currentTime;
        }
    }


    // Returns current time in ms
    public double getTime() {
        return timeSinceStart;
    }


    // Returns the current time in seconds
    public double getTimeInSeconds() {
        return timeSinceStart / 1000;
    }


    // Change the timer speed (1.0 = realtime)
    public void changeTimeScaling(double newValue) {
        timeScale = newValue;
    }


    public void pauseTime() {
        while (true) {
            if (isPaused.compareAndSet(isPaused.get(), true)) {
                System.out.println("Application Time is paused.");
                return;
            }
        }
    }


    public void continueTime() {
        while (true) {
            if (isPaused.compareAndSet(isPaused.get(), false)) {
                System.out.println("Application Time continues.");
                return;
            }
        }
    }


    public void endThread() {
        while (true) {
            if (running.compareAndSet(running.get(), false)) {
                this.interrupt();
                System.out.println("Application Time interrupted.");
                return;
            }
        }
    }
}
