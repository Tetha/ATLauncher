package com.atlauncher.gui.comp.panel;

import javax.swing.*;
import java.awt.*;

public final class PTabBottomPanel extends JPanel {
    private final JButton COLLAPSE_BUTTON = new JButton("Collapse All"){{

    }};
    private final JButton UNCOLLAPSE_BUTTON = new JButton("Uncollapse All"){{

    }};

    public PTabBottomPanel(){
        super(new FlowLayout(FlowLayout.LEFT));

        this.add(this.COLLAPSE_BUTTON);
        this.add(this.UNCOLLAPSE_BUTTON);
    }
}