/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.utils.Localizer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModDescriptionJLabel extends JLabel {

    private static final long serialVersionUID = 1L;
    private String description;

    public ModDescriptionJLabel(String descriptionn) {
        super("<html><center><font color=\"#2277AA\"><sup>[?]</sup></font></center></html>");
        this.description = descriptionn;
        super.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                Object[] options = { Localizer.localize("common.ok") };
                JOptionPane.showOptionDialog(null, "<html><center>" + description
                        + "</center></html>",
                        Localizer.localize("instance.moddescription"),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                        options[0]);
            }

        });
    }
}
