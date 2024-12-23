package app;

import utils.ApplicationTime;
import utils.Constants;
import utils.FrameUpdater;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;

public abstract class Animation {

    public void start(){
        ApplicationTime applicationTimeThread = new ApplicationTime();
        applicationTimeThread.start();
        FrameUpdater frameUpdater = new FrameUpdater(createFrames(applicationTimeThread));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(frameUpdater, 100, Constants.MS_PER_TICK);
    }

    protected abstract ArrayList<JFrame> createFrames(ApplicationTime applicationTimeThread);
}
