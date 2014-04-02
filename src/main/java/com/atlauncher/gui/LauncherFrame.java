package com.atlauncher.gui;

import com.atlauncher.App;
import com.atlauncher.gui.comp.panel.MainBottomPanel;
import com.atlauncher.gui.tab.*;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class LauncherFrame extends JFrame {
    private final NewsTab NEWS_TAB = new NewsTab();
    private final SocialMediaTab SM_TAB = new SocialMediaTab();
    private final InstanceTab INSTANCE_TAB = new InstanceTab();
    private final PacksTab PACKS_TAB = new PacksTab();
    private final AccountTab ACCOUNT_TAB = new AccountTab();
    private final SettingsTab SETTINGS_TAB = new SettingsTab();

    private final MainBottomPanel BOTTOM_PANEL = new MainBottomPanel();
    private final JTabbedPane TABS = new JTabbedPane(JTabbedPane.RIGHT){{
        this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(34.0F));
        this.setBackground(App.BASE_COLOR.brighter());
        this.setOpaque(true);

        this.addTab("News", LauncherFrame.this.NEWS_TAB);
        this.add("Packs", LauncherFrame.this.PACKS_TAB);
        this.add("Instances", LauncherFrame.this.INSTANCE_TAB);
        this.add("Account", LauncherFrame.this.ACCOUNT_TAB);
        this.add("Settings", LauncherFrame.this.SETTINGS_TAB);
        this.addTab("Social", LauncherFrame.this.SM_TAB);
    }};

    public LauncherFrame(){
        super("ATLauncher - %VERSION%");
        this.setMinimumSize(new Dimension(1000, 640));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(Utils.getImage("Icon.png"));

        this.add(this.TABS, BorderLayout.CENTER);
        this.add(this.BOTTOM_PANEL, BorderLayout.SOUTH);
    }
}