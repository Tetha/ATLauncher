package com.atlauncher.gui.tab;

import com.atlauncher.gui.comp.panel.PTabBottomPanel;
import com.atlauncher.gui.comp.panel.PTabCenterPanel;
import com.atlauncher.gui.comp.panel.PTabTopPanel;

import javax.swing.*;
import java.awt.*;

public final class PacksTab extends JPanel {
    private final PTabTopPanel TOP_PANEL = new PTabTopPanel();
    private final PTabBottomPanel BOTTOM_PANEL = new PTabBottomPanel();
    private final PTabCenterPanel CENTER_PANEL = new PTabCenterPanel();

    public PacksTab(){
        super(new BorderLayout());

        this.add(this.TOP_PANEL, BorderLayout.NORTH);
        this.add(this.BOTTOM_PANEL, BorderLayout.SOUTH);
        this.add(this.CENTER_PANEL, BorderLayout.CENTER);
    }
}