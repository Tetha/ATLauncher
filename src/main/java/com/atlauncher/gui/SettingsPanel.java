/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher.gui;

import com.atlauncher.App;
import com.atlauncher.data.Server;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton saveButton;
    private ImageIcon helpIcon;

    private JLabel languageLabel;
    private JComboBox<String> language;

    private JLabel downloadServerLabel;
    private JComboBox<Server> server;

    private JLabel forgeLoggingLevelLabel;
    private JComboBox<String> forgeLoggingLevel;

    private JLabel memoryLabel;
    private JComboBox<String> memory;

    private JLabel permGenLabel;
    private JTextField permGen;

    private JPanel windowSizePanel;
    private JLabel windowSizeLabel;
    private JTextField widthField;
    private JTextField heightField;
    private JComboBox<String> commonScreenSizes;

    private JPanel javaPathPanel;
    private JLabel javaPathLabel;
    private JTextField javaPath;
    private JButton javaPathResetButton;

    private JLabel javaParametersLabel;
    private JTextField javaParameters;

    private JLabel startMinecraftMaximisedLabel;
    private JCheckBox startMinecraftMaximised;

    private JLabel advancedBackupLabel;
    private JCheckBox advancedBackup;

    private JLabel sortPacksAlphabeticallyLabel;
    private JCheckBox sortPacksAlphabetically;

    private JLabel keepLauncherOpenLabel;
    private JCheckBox keepLauncherOpen;

    private JLabel enableConsoleLabel;
    private JCheckBox enableConsole;

    private JLabel enableDebugConsoleLabel;
    private JCheckBox enableDebugConsole;

    private JLabel enableLeaderboardsLabel;
    private JCheckBox enableLeaderboards;

    private JLabel enableLoggingLabel;
    private JCheckBox enableLogs;

    private final Insets LABEL_INSETS = new Insets(3, 0, 3, 10);
    private final Insets FIELD_INSETS = new Insets(3, 0, 3, 0);
    private final Insets LABEL_INSETS_SMALL = new Insets(0, 0, 0, 10);
    private final Insets FIELD_INSETS_SMALL = new Insets(0, 0, 0, 0);

    public SettingsPanel() {
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        helpIcon = Utils.getIconImage("Help.png");

        // Language
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        languageLabel = new JLabel(Localizer.localize("settings.language") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        languageLabel.setIcon(helpIcon);
        languageLabel.setToolTipText(Localizer.localize("settings.languagehelp"));
        topPanel.add(languageLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        language = new JComboBox<String>();
        for (String languagee : App.settings.collectLanguages()) {
            language.addItem(languagee);
        }
        language.setSelectedItem(Localizer.INSTANCE.getLanguage());
        topPanel.add(language, gbc);

        // Forge Logging Level
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        forgeLoggingLevelLabel = new JLabel(
                Localizer.localize("settings.forgelogginglevel") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        forgeLoggingLevelLabel.setIcon(helpIcon);
        forgeLoggingLevelLabel.setToolTipText("<html><center>"
                + Localizer.localize("settings.forgelogginglevelhelp", "<br/><br/>")
                + "</center></html>");
        topPanel.add(forgeLoggingLevelLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        forgeLoggingLevel = new JComboBox<String>();
        forgeLoggingLevel.addItem("SEVERE");
        forgeLoggingLevel.addItem("WARNING");
        forgeLoggingLevel.addItem("INFO");
        forgeLoggingLevel.addItem("CONFIG");
        forgeLoggingLevel.addItem("FINE");
        forgeLoggingLevel.addItem("FINER");
        forgeLoggingLevel.addItem("FINEST");
        forgeLoggingLevel.setSelectedItem(App.settings.getForgeLoggingLevel());
        topPanel.add(forgeLoggingLevel, gbc);

        // Download Server
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        downloadServerLabel = new JLabel(Localizer.localize("settings.downloadserver")
                + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        downloadServerLabel.setIcon(helpIcon);
        downloadServerLabel.setToolTipText(Localizer.localize("settings.downloadserverhelp"));
        topPanel.add(downloadServerLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        server = new JComboBox<Server>();
        for (Server serverr : App.settings.getServers()) {
            if (serverr.isUserSelectable()) {
                server.addItem(serverr);
            }
        }
        server.setSelectedItem(App.settings.getOriginalServer());
        topPanel.add(server, gbc);

        // Memory Settings
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        memoryLabel = new JLabel(Localizer.localize("settings.memory") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        memoryLabel.setIcon(helpIcon);
        if (Utils.is64Bit())
            memoryLabel.setToolTipText(Localizer.localize("settings.memoryhelp"));
        else
            memoryLabel.setToolTipText("<html><center>"
                    + Localizer.localize("settings.memoryhelp32bit", "<br/>")
                    + "</center></html>");
        topPanel.add(memoryLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        memory = new JComboBox<String>();
        String[] memoryOptions = Utils.getMemoryOptions();
        for (int i = 0; i < memoryOptions.length; i++) {
            memory.addItem(memoryOptions[i]);
        }
        memory.setSelectedItem(App.settings.getMemory() + " MB");
        topPanel.add(memory, gbc);

        // Perm Gen Settings
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        permGenLabel = new JLabel(Localizer.localize("settings.permgen") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        permGenLabel.setIcon(helpIcon);
        permGenLabel.setToolTipText(Localizer.localize("settings.permgenhelp"));
        topPanel.add(permGenLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        permGen = new JTextField(4);
        permGen.setText(App.settings.getPermGen() + "");
        topPanel.add(permGen, gbc);

        // Window Size
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = LABEL_INSETS_SMALL;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        windowSizeLabel = new JLabel(Localizer.localize("settings.windowsize") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        windowSizeLabel.setIcon(helpIcon);
        windowSizeLabel.setToolTipText(Localizer.localize("settings.windowsizehelp"));
        topPanel.add(windowSizeLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS_SMALL;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        windowSizePanel = new JPanel();
        windowSizePanel.setLayout(new FlowLayout());
        widthField = new JTextField(4);
        widthField.setText(App.settings.getWindowWidth() + "");
        heightField = new JTextField(4);
        heightField.setText(App.settings.getWindowHeight() + "");
        commonScreenSizes = new JComboBox<String>();
        commonScreenSizes.addItem("Select An Option");
        commonScreenSizes.addItem("854x480");
        if (Utils.getMaximumWindowWidth() >= 1280 && Utils.getMaximumWindowHeight() >= 720) {
            commonScreenSizes.addItem("1280x720");
        }
        if (Utils.getMaximumWindowWidth() >= 1600 && Utils.getMaximumWindowHeight() >= 900) {
            commonScreenSizes.addItem("1600x900");
        }
        if (Utils.getMaximumWindowWidth() >= 1920 && Utils.getMaximumWindowHeight() >= 1080) {
            commonScreenSizes.addItem("1920x1080");
        }
        commonScreenSizes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) commonScreenSizes.getSelectedItem();
                if (selected.contains("x")) {
                    String[] parts = selected.split("x");
                    widthField.setText(parts[0]);
                    heightField.setText(parts[1]);
                }
            }
        });
        commonScreenSizes.setPreferredSize(new Dimension(
                commonScreenSizes.getPreferredSize().width + 10, commonScreenSizes
                        .getPreferredSize().height));
        windowSizePanel.add(widthField);
        windowSizePanel.add(new JLabel("x"));
        windowSizePanel.add(heightField);
        windowSizePanel.add(commonScreenSizes);
        topPanel.add(windowSizePanel, gbc);
        windowSizeLabel.setPreferredSize(new Dimension(windowSizeLabel.getPreferredSize().width,
                windowSizePanel.getPreferredSize().height));

        // Java Path

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = LABEL_INSETS_SMALL;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        javaPathLabel = new JLabel(Localizer.localize("settings.javapath") + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        javaPathLabel.setIcon(helpIcon);
        javaPathLabel.setToolTipText("<html><center>"
                + Localizer.localize("settings.javapathhelp", "<br/>")
                + "</center></html>");
        topPanel.add(javaPathLabel, gbc);

        gbc.gridx++;
        gbc.insets = LABEL_INSETS_SMALL;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        javaPathPanel = new JPanel();
        javaPathPanel.setLayout(new FlowLayout());
        javaPath = new JTextField(20);
        javaPath.setText(App.settings.getJavaPath());
        javaPathResetButton = new JButton(Localizer.localize("settings.javapathreset"));
        javaPathResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                javaPath.setText(Utils.getJavaHome());
            }
        });
        javaPathPanel.add(javaPath);
        javaPathPanel.add(javaPathResetButton);
        topPanel.add(javaPathPanel, gbc);
        javaPathLabel.setPreferredSize(new Dimension(javaPathLabel.getPreferredSize().width,
                javaPathPanel.getPreferredSize().height));

        // Java Paramaters

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        javaParametersLabel = new JLabel(Localizer.localize("settings.javaparameters")
                + ":") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        javaParametersLabel.setIcon(helpIcon);
        javaParametersLabel.setToolTipText(Localizer.localize("settings.javaparametershelp"));
        topPanel.add(javaParametersLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        javaParameters = new JTextField(20);
        javaParameters.setText(App.settings.getJavaParameters());
        topPanel.add(javaParameters, gbc);

        // Start Minecraft Maximised

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        startMinecraftMaximisedLabel = new JLabel(
                Localizer.localize("settings.startminecraftmaximised") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        startMinecraftMaximisedLabel.setIcon(helpIcon);
        startMinecraftMaximisedLabel.setToolTipText(Localizer.localize("settings.startminecraftmaximisedhelp"));
        topPanel.add(startMinecraftMaximisedLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        startMinecraftMaximised = new JCheckBox();
        if (App.settings.startMinecraftMaximised()) {
            startMinecraftMaximised.setSelected(true);
        }
        topPanel.add(startMinecraftMaximised, gbc);

        // Advanced Backup

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        advancedBackupLabel = new JLabel(Localizer.localize("settings.advancedbackup")
                + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        advancedBackupLabel.setIcon(helpIcon);
        advancedBackupLabel.setToolTipText("<html><center>"
                + Localizer.localize("settings.advancedbackuphelp", "<br/>")
                + "</center></html>");
        topPanel.add(advancedBackupLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        advancedBackup = new JCheckBox();
        if (App.settings.isAdvancedBackupsEnabled()) {
            advancedBackup.setSelected(true);
        }
        topPanel.add(advancedBackup, gbc);

        // Sort Packs Alphabetically

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        sortPacksAlphabeticallyLabel = new JLabel(
                Localizer.localize("settings.sortpacksalphabetically") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        sortPacksAlphabeticallyLabel.setIcon(helpIcon);
        sortPacksAlphabeticallyLabel.setToolTipText(Localizer.localize("settings.sortpacksalphabeticallyhelp"));
        topPanel.add(sortPacksAlphabeticallyLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        sortPacksAlphabetically = new JCheckBox();
        if (App.settings.sortPacksAlphabetically()) {
            sortPacksAlphabetically.setSelected(true);
        }
        topPanel.add(sortPacksAlphabetically, gbc);

        // Keep Launcher Open

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        keepLauncherOpenLabel = new JLabel(
                Localizer.localize("settings.keeplauncheropen") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        keepLauncherOpenLabel.setIcon(helpIcon);
        keepLauncherOpenLabel.setToolTipText(Localizer.localize("settings.keeplauncheropenhelp"));
        topPanel.add(keepLauncherOpenLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        keepLauncherOpen = new JCheckBox();
        if (App.settings.keepLauncherOpen()) {
            keepLauncherOpen.setSelected(true);
        }
        topPanel.add(keepLauncherOpen, gbc);

        // Enable Console

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        enableConsoleLabel = new JLabel(Localizer.localize("settings.console") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        enableConsoleLabel.setIcon(helpIcon);
        enableConsoleLabel.setToolTipText(Localizer.localize("settings.consolehelp"));
        topPanel.add(enableConsoleLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        enableConsole = new JCheckBox();
        if (App.settings.enableConsole()) {
            enableConsole.setSelected(true);
        }
        topPanel.add(enableConsole, gbc);

        // Enable Debug Console

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        enableDebugConsoleLabel = new JLabel(
                Localizer.localize("settings.debugconsole") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        enableDebugConsoleLabel.setIcon(helpIcon);
        enableDebugConsoleLabel.setToolTipText(Localizer.localize("settings.debugconsolehelp"));
        topPanel.add(enableDebugConsoleLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        enableDebugConsole = new JCheckBox();
        if (App.settings.enableDebugConsole()) {
            enableDebugConsole.setSelected(true);
        }
        topPanel.add(enableDebugConsole, gbc);

        // Enable Leaderboards

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        enableLeaderboardsLabel = new JLabel(
                Localizer.localize("settings.leaderboards") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        enableLeaderboardsLabel.setIcon(helpIcon);
        enableLeaderboardsLabel.setToolTipText(Localizer.localize("settings.leaderboardshelp"));
        topPanel.add(enableLeaderboardsLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        enableLeaderboards = new JCheckBox();
        if (App.settings.enableLeaderboards()) {
            enableLeaderboards.setSelected(true);
        }
        topPanel.add(enableLeaderboards, gbc);

        // Enable Logging

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = LABEL_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        enableLoggingLabel = new JLabel(Localizer.localize("settings.logging") + "?") {
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                Border border = new CustomLineBorder(5, new Color(80, 170, 107), 2);
                tip.setBorder(border);
                return tip;
            }
        };
        enableLoggingLabel.setIcon(helpIcon);
        enableLoggingLabel.setToolTipText("<html><center>"
                + Localizer.localize("settings.logginghelp", "<br/>")
                + "</center></html>");
        topPanel.add(enableLoggingLabel, gbc);

        gbc.gridx++;
        gbc.insets = FIELD_INSETS;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        enableLogs = new JCheckBox();
        if (App.settings.enableLogs()) {
            enableLogs.setSelected(true);
        }
        topPanel.add(enableLogs, gbc);

        // End Components

        bottomPanel = new JPanel();
        saveButton = new JButton(Localizer.localize("common.save"));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                File jPath = new File(javaPath.getText(), "bin");
                if (!jPath.exists()) {
                    JOptionPane.showMessageDialog(
                            App.settings.getParent(),
                            "<html><center>"
                                    + Localizer.localize("settings.javapathincorrect",
                                            "<br/><br/>") + "</center></html>",
                            Localizer.localize("settings.help"),
                            JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if (javaParameters.getText().contains("-Xms")
                        || javaParameters.getText().contains("-Xmx")
                        || javaParameters.getText().contains("-XX:PermSize")) {
                    JOptionPane.showMessageDialog(
                            App.settings.getParent(),
                            "<html><center>"
                                    + Localizer.localize(
                                            "settings.javaparametersincorrect", "<br/><br/>")
                                    + "</center></html>", Localizer.localize("settings.help"), JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                boolean reboot = false;
                boolean reloadPacksPanel = false;
                if (language.getSelectedItem() != Localizer.INSTANCE.getLanguage()) {
                    reboot = true;
                }
                if (sortPacksAlphabetically.isSelected() != App.settings.sortPacksAlphabetically()) {
                    reloadPacksPanel = true;
                }
                Localizer.INSTANCE.setCurrent((String) language.getSelectedItem());
                App.settings.setServer((Server) server.getSelectedItem());
                App.settings.setForgeLoggingLevel((String) forgeLoggingLevel.getSelectedItem());
                App.settings.setMemory(Integer.parseInt(((String) memory.getSelectedItem())
                        .replace(" MB", "")));
                App.settings.setPermGen(Integer
                        .parseInt(permGen.getText().replaceAll("[^0-9]", "")));
                App.settings.setWindowWidth(Integer.parseInt(widthField.getText().replaceAll(
                        "[^0-9]", "")));
                App.settings.setWindowHeight(Integer.parseInt(heightField.getText().replaceAll(
                        "[^0-9]", "")));
                App.settings.setJavaPath(javaPath.getText());
                App.settings.setJavaParameters(javaParameters.getText());
                App.settings.setStartMinecraftMaximised(startMinecraftMaximised.isSelected());
                App.settings.setAdvancedBackups(advancedBackup.isSelected());
                App.settings.setSortPacksAlphabetically(sortPacksAlphabetically.isSelected());
                App.settings.setKeepLauncherOpen(keepLauncherOpen.isSelected());
                App.settings.setEnableConsole(enableConsole.isSelected());
                App.settings.setEnableDebugConsole(enableDebugConsole.isSelected());
                App.settings.setEnableLeaderboards(enableLeaderboards.isSelected());
                App.settings.setEnableLogs(enableLogs.isSelected());
                App.settings.saveProperties();
                App.settings.log("Settings Saved!");
                if (reboot) {
                    App.settings.restartLauncher();
                }
                if (reloadPacksPanel) {
                    App.settings.reloadPacksPanel();
                }
                String[] options = { Localizer.localize("common.ok") };
                JOptionPane.showOptionDialog(App.settings.getParent(),
                        Localizer.localize("settings.saved"),
                        Localizer.localize("settings.saved"),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                        options[0]);
            }
        });
        bottomPanel.add(saveButton);

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}