/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * ATLauncher is licensed under CC BY-NC-ND 3.0 which allows others you to
 * share this software with others as long as you credit us by linking to our
 * website at http://www.atlauncher.com. You also cannot modify the application
 * in any way or make commercial use of this software.
 *
 * Link to license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
package com.atlauncher.gui;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.atlauncher.App;

public final class TrayMenu extends PopupMenu {

    private final MenuItem KILLMC_BUTTON = new MenuItem() {
        {
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (App.settings.isMinecraftLaunched()) {
                                int ret = JOptionPane.showConfirmDialog(
                                        App.settings.getParent(),
                                        "<html><center>"
                                                + App.settings.getLocalizedString(
                                                        "console.killsure", "<br/><br/>")
                                                + "</center></html>", App.settings
                                                .getLocalizedString("console.kill"),
                                        JOptionPane.YES_OPTION);

                                if (ret == JOptionPane.YES_OPTION) {
                                    App.settings.killMinecraft();
                                }
                            }
                        }
                    });
                }
            });
        }
    };

    private final MenuItem TC_BUTTON = new MenuItem() {
        {
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (App.settings.isConsoleVisible()) {
                        App.settings.setConsoleVisible(false);
                        setLabel(App.settings.getLocalizedString("console.show"));
                    } else {
                        App.settings.setConsoleVisible(true);
                        setLabel(App.settings.getLocalizedString("console.hide"));
                    }
                }
            });
        }
    };

    private final MenuItem QUIT_BUTTON = new MenuItem() {
        {
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });
        }
    };

    public void setConsoleVisible(boolean visible) {
        if (visible) {
            if (App.settings.isLanguageLoaded()) {
                this.TC_BUTTON.setLabel(App.settings.getLocalizedString("console.hide"));
            } else {
                this.TC_BUTTON.setLabel("Hide Console");
            }
        } else {
            if (App.settings.isLanguageLoaded()) {
                this.TC_BUTTON.setLabel(App.settings.getLocalizedString("console.show"));
            } else {
                this.TC_BUTTON.setLabel("Show Console");
            }
        }
    }

    public void setMinecraftLaunched(boolean launched) {
        this.KILLMC_BUTTON.setEnabled(launched); // TODO: Fix this not redisabling/enabling the item
    }

    public TrayMenu() {
        super();

        this.setMinecraftLaunched(false); // Default Kill MC item to be disabled

        // Setup default labels until proper localization is able to be done
        this.KILLMC_BUTTON.setLabel("Kill Minecraft");
        this.TC_BUTTON.setLabel("Toggle Console");
        this.TC_BUTTON.setEnabled(false);
        this.QUIT_BUTTON.setLabel("Quit");

        this.add(this.KILLMC_BUTTON);
        this.add(this.TC_BUTTON);
        this.addSeparator();
        this.add(this.QUIT_BUTTON);
    }

    public void localize() {

        // Resetup TC Button
        this.TC_BUTTON.setEnabled(true);
        if (App.settings.isConsoleVisible()) {
            this.TC_BUTTON.setLabel(App.settings.getLocalizedString("console.hide"));
        } else {
            this.TC_BUTTON.setLabel(App.settings.getLocalizedString("console.show"));
        }

        // Do localization
        this.KILLMC_BUTTON.setLabel(App.settings.getLocalizedString("console.kill"));
        this.QUIT_BUTTON.setLabel(App.settings.getLocalizedString("common.quit"));
    }

}
