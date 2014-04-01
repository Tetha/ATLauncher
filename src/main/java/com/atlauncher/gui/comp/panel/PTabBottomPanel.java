package com.atlauncher.gui.comp.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PTabBottomPanel extends JPanel {
    private final JButton COLLAPSE_BUTTON = new JButton("Collapse All"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            }
        });
    }};
    private final JButton UNCOLLAPSE_BUTTON = new JButton("Uncollapse All"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            }
        });
    }};

    public PTabBottomPanel(){
        super(new FlowLayout(FlowLayout.LEFT));

        this.add(this.COLLAPSE_BUTTON);
        this.add(this.UNCOLLAPSE_BUTTON);
    }
}