package utils;

import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JFrame;

public class FrameUpdater extends TimerTask {

    private ArrayList<JFrame> frames;

    public FrameUpdater(ArrayList<JFrame> frames){
        this.frames = frames;
    }

    @Override
    public  void run(){
        frames.forEach(f->f.repaint());
    }



}
