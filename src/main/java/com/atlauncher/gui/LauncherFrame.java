/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.App;
import com.atlauncher.gui.comp.panel.MainBottomPanel;
import com.atlauncher.gui.tab.NewsTab;
import com.atlauncher.gui.tab.SocialMediaTab;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class LauncherFrame extends JFrame {
    // Size of initial window
    private final BorderLayout LAYOUT_MANAGER = new BorderLayout();
    private final Color BASE_COLOR = new Color(40, 45, 50);

    private JTabbedPane tabbedPane;
    private PacksPanel packsPanel;
    private InstancesPanel instancesPanel;
    private AccountPanel accountPanel;
    private SettingsPanel settingsPanel;
    public final SocialMediaTab SM_TAB = new SocialMediaTab();
    private final JPanel NEWS_TAB = new NewsTab();
    private final JPanel BOTTOM_PANEL = new MainBottomPanel();

    public LauncherFrame(boolean show) {
        App.settings.log("Launcher opening");
        App.settings.log("Made By Bob*");
        App.settings.log("*(Not Actually)");
        App.settings.setParentFrame(this);
        setSize(new Dimension(1000, 600));
        setTitle("ATLauncher %VERSION%");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(Utils.getImage("Icon.png"));
        setLayout(LAYOUT_MANAGER);

        App.settings.log("Setting up Look & Feel");
        App.settings.log("Finished Setting up Bottom Bar");

        App.settings.log("Setting up Tabs");
        setupTabs(); // Setup the JTabbedPane
        App.settings.log("Finished Setting up Tabs");

        add(tabbedPane, BorderLayout.CENTER);
        add(this.BOTTOM_PANEL, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                dispose();
            }
        });

        if (show) {
            App.settings.log("Showing Launcher");
            setVisible(true);
        }

        App.settings.addConsoleListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                App.settings.setConsoleVisible(false);
            }
        });

        App.TASKPOOL.execute(new Runnable() {
            public void run() {
                App.settings.checkMojangStatus(); // Check Minecraft status
            }
        });
    }


    /**
     * Setup the individual tabs used in the Launcher sidebar
     */
    private void setupTabs() {
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
        tabbedPane.setBackground(BASE_COLOR);

        packsPanel = new PacksPanel();
        App.settings.setPacksPanel(packsPanel);
        instancesPanel = new InstancesPanel();
        App.settings.setInstancesPanel(instancesPanel);
        accountPanel = new AccountPanel();
        settingsPanel = new SettingsPanel();

        tabbedPane.setFont(Utils.makeFont("Oswald-Regular").deriveFont((float) 34));
        tabbedPane.addTab(Localizer.localize("tabs.news"), this.NEWS_TAB);
        tabbedPane.addTab(Localizer.localize("tabs.packs"), packsPanel);
        // tabbedPane.addTab(Localizer.localize("tabs.addons"), addonsPanel);
        tabbedPane.addTab(Localizer.localize("tabs.instances"), instancesPanel);
        tabbedPane.addTab(Localizer.localize("tabs.account"), accountPanel);
        tabbedPane.addTab(Localizer.localize("tabs.settings"), settingsPanel);
        tabbedPane.addTab("Social", this.SM_TAB);
        tabbedPane.setBackground(BASE_COLOR.brighter());
        tabbedPane.setOpaque(true);
    }
}
