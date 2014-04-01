/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.App;
import com.atlauncher.data.Account;
import com.atlauncher.data.Status;
import com.atlauncher.gui.comp.panel.SocialMediaPanel;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class BottomBar extends JPanel {
    private final SocialMediaPanel SMP = new SocialMediaPanel();
    private JPanel leftSide;
    private JPanel middle;

    private Account fillerAccount;
    private boolean dontSave = false;

    private JButton toggleConsole;
    private JButton openFolder;
    private JButton updateData;
    private JComboBox<Account> username;

    private JLabel statusIcon;

    public BottomBar() {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 50)); // Make the bottom bar at least
                                                // 50 pixels high

        leftSide = new JPanel();
        leftSide.setLayout(new GridBagLayout());
        middle = new JPanel();
        middle.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        createButtons();
        setupListeners();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(0, 0, 0, 5);
        leftSide.add(toggleConsole, gbc);
        gbc.gridx++;
        leftSide.add(openFolder, gbc);
        gbc.gridx++;
        leftSide.add(updateData, gbc);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(0, 0, 0, 5);
        middle.add(username, gbc);
        gbc.gridx++;
        middle.add(statusIcon, gbc);

        add(leftSide, BorderLayout.WEST);
        add(middle, BorderLayout.CENTER);
        this.add(this.SMP, BorderLayout.EAST);
    }

    /**
     * Sets up the listeners on the buttons
     */
    private void setupListeners() {
        toggleConsole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.settings.setConsoleVisible(!App.settings.isConsoleVisible());
            }
        });
        openFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.openExplorer(App.settings.getBaseDir());
            }
        });
        updateData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final ProgressDialog dialog = new ProgressDialog(Localizer.localize("common.checkingforupdates"), 0,
                       Localizer.localize("common.checkingforupdates"), "Aborting Update Check!");
                dialog.addThread(new Thread() {
                    public void run() {
                        if (App.settings.hasUpdatedFiles()) {
                            App.settings.reloadLauncherData();
                        }
                        dialog.close();
                    }

                    ;
                });
                dialog.start();
            }
        });
        username.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (!dontSave) {
                        App.settings.switchAccount((Account) username.getSelectedItem());
                    }
                }
            }
        });
    }

    /**
     * Creates the JButton's for use in the bar
     */
    private void createButtons() {
        if (App.settings.isConsoleVisible()) {
            toggleConsole = new JButton(Localizer.localize("console.hide"));
        } else {
            toggleConsole = new JButton(Localizer.localize("console.show"));
        }

        openFolder = new JButton(Localizer.localize("common.openfolder"));
        updateData = new JButton(Localizer.localize("common.updatedata"));

        username = new JComboBox<Account>();
        username.setRenderer(new AccountsDropDownRenderer());
        fillerAccount = new Account(Localizer.localize("account.select"));
        username.addItem(fillerAccount);
        for (Account account : App.settings.getAccounts()) {
            username.addItem(account);
        }
        Account active = App.settings.getAccount();
        if (active == null) {
            username.setSelectedIndex(0);
        } else {
            username.setSelectedItem(active);
        }

        statusIcon = new JLabel(Utils.getIconImage("StatusWhite.png")) {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        statusIcon.setBorder(BorderFactory.createEmptyBorder());
        statusIcon.setToolTipText(Localizer.localize("status.minecraft.checking"));
    }

    /**
     * Update the status icon to show the current Minecraft server status.
     * 
     * @param status
     *            The status of servers
     */
    public void updateStatus(Status status) {
        switch (status) {
            case UNKNOWN:
                statusIcon.setToolTipText(Localizer.localize("status.minecraft.checking"));
                statusIcon.setIcon(Utils.getIconImage("StatusWhite.png"));
                break;
            case ONLINE:
                statusIcon.setToolTipText(Localizer.localize("status.minecraft.online"));
                statusIcon.setIcon(Utils.getIconImage("StatusGreen.png"));
                break;
            case OFFLINE:
                statusIcon.setToolTipText(Localizer.localize("status.minecraft.offline"));
                statusIcon.setIcon(Utils.getIconImage("StatusRed.png"));
                break;
            case PARTIAL:
                statusIcon.setToolTipText(Localizer.localize("status.minecraft.partial"));
                statusIcon.setIcon(Utils.getIconImage("StatusYellow.png"));
                break;
            default:
                break;
        }
    }

    /**
     * Changes the text on the toggleConsole button when the console is hidden
     */
    public void hideConsole() {
        toggleConsole.setText(Localizer.localize("console.show"));
    }

    /**
     * Changes the text on the toggleConsole button when the console is shown
     */
    public void showConsole() {
        toggleConsole.setText(Localizer.localize("console.hide"));
    }

    public void reloadAccounts() {
        dontSave = true;
        username.removeAllItems();
        username.addItem(fillerAccount);
        for (Account account : App.settings.getAccounts()) {
            username.addItem(account);
        }
        if (App.settings.getAccount() == null) {
            username.setSelectedIndex(0);
        } else {
            username.setSelectedItem(App.settings.getAccount());
        }
        dontSave = false;
    }
}
