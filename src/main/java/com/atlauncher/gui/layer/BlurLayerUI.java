package com.atlauncher.gui.layer;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public final class BlurLayerUI<x extends JComponent> extends LayerUI<x> implements ActionListener {
    private final int LIMIT = 15;

    private Timer timer;

    private int angle = 0;
    private int count = 0;

    private volatile boolean running = false;
    private volatile boolean fading = false;

    public void start(){
        if(this.running){
            return;
        }

        this.running = true;
        this.fading = false;
        this.count = 0;

        this.timer = new Timer(1000 / 30, this);
        this.timer.start();
    }

    public void stop(){
        this.fading = true;
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        super.paint(g, comp);

        if(!this.running){
            return;
        }

        int width = comp.getWidth();
        int height = comp.getHeight();
        Graphics2D graphics = (Graphics2D) g.create();
        float fade = (float) this.count / this.LIMIT;
        Composite c = graphics.getComposite();

        graphics.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 0.5F * fade
        ));
        graphics.fillRect(0, 0, width, height);
        graphics.setComposite(c);

        int second = Math.min(width, height) / 5;
        int dX = width / 2;
        int dY = height / 2;

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics.setStroke(
                new BasicStroke(
                        second / 4,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );
        graphics.setPaint(Color.WHITE);
        graphics.rotate(Math.PI * angle / 180, dX, dY);

        for(int i = 0; i < 12; i++){
            float scale = (11.0F - (float) i) / 11.0F;

            graphics.drawLine(dX + second, dY, dX + second * 2, dY);
            graphics.rotate(-(Math.PI / 6), dX, dY);
            graphics.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, scale * fade
            ));
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(this.running){
            firePropertyChange("tick", 0, 1);

            this.angle += 3;

            if(this.angle >= 360){
                this.angle = 0;
            }

            if(this.fading){
                this.count--;

                if(this.count == 0){
                    this.running = false;
                    this.timer.stop();
                }
            } else if(this.count < this.LIMIT){
                this.count++;
            }
        }
    }

    @Override
    public void applyPropertyChange(PropertyChangeEvent event, JLayer layer){
        if(event.getPropertyName().equalsIgnoreCase("tick")){
            layer.repaint();
        }
    }
}