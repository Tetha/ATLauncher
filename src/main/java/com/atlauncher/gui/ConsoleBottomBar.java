/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.gui.comp.ConsoleActionsPanel;
import com.atlauncher.gui.comp.SocialMediaPanel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ConsoleBottomBar extends JPanel {
    private final SocialMediaPanel SMP = new SocialMediaPanel();
    private final ConsoleActionsPanel CAP = new ConsoleActionsPanel();

    public ConsoleBottomBar() {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 50)); // Make the bottom bar at least 50 pixels high

        this.add(this.CAP, BorderLayout.WEST);
        this.add(this.SMP, BorderLayout.EAST);
    }

    public void showKillMinecraft() {
        this.CAP.showKillMC();
    }

    public void hideKillMinecraft() {
        this.CAP.hideKillMC();
    }

    public void setupLanguage() {
        this.CAP.localize();
    }
}
