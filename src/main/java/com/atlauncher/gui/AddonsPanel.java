/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.App;
import com.atlauncher.data.Addon;
import com.atlauncher.utils.Localizer;

import javax.swing.*;
import java.awt.*;

public class AddonsPanel extends JPanel {

    private JPanel panel;
    private JScrollPane scrollPane;

    public AddonsPanel() {
        setLayout(new BorderLayout());
        panel = new JPanel();
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        if (App.settings.getAddons().size() == 0) {
            panel.add(new NothingToDisplay(Localizer.localize("addon.nodisplay", "\n\n")), gbc);
        } else {
            for (Addon addon : App.settings.getAddons()) {
                panel.add(new AddonDisplay(addon), gbc);
                gbc.gridy++;
            }
        }
    }

}
