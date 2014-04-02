package com.atlauncher.gui;

import com.atlauncher.gui.comp.panel.ConsoleBottomPanel;
import com.atlauncher.gui.comp.panel.LoggingPanel;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class ConsoleFrame extends JFrame {
    private final ConsoleBottomPanel BOTTOM_PANEL = new ConsoleBottomPanel();
    private final LoggingPanel MIDDLE_PANEL = new LoggingPanel();

    public ConsoleFrame(){
        super("ATLauncher Console %VERSION%");
        this.setMinimumSize(new Dimension(600, 400));
        this.setIconImage(Utils.getImage("Icon.png"));
        this.setResizable(false);

        this.add(this.BOTTOM_PANEL, BorderLayout.SOUTH);
        this.add(this.MIDDLE_PANEL, BorderLayout.CENTER);
    }

    public void log(String msg){
        this.MIDDLE_PANEL.log(msg);
    }

    public String getText(){
        return this.MIDDLE_PANEL.getText();
    }

    public void clear(){
        this.MIDDLE_PANEL.clear();
    }
}