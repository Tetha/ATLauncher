package com.atlauncher.gui.anim;

import com.atlauncher.gui.layer.BlurLayerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class BlurAnimation<x extends JComponent> implements ActionListener {
    private final BlurLayerUI<x> layer;
    private final Timer timer;

    public BlurAnimation(BlurLayerUI<x> layer, int time){
        this.timer = new Timer(time, this);
        this.layer = layer;
        this.timer.setRepeats(false);
    }

    public BlurAnimation<x> start(){
        this.layer.start();
        if(!this.timer.isRunning()){
            this.timer.start();
        }
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        this.layer.stop();
    }
}