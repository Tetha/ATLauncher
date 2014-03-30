package com.atlauncher.gui.tab;

import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class GitHubTab extends JPanel {
    private final JLabel OOS_LABEL = new JLabel("Out of Service :("){{
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));
        this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(32.0F));
    }};

    public GitHubTab(){
        super(new GridLayout(1, 1));

        this.add(this.OOS_LABEL);
    }
}