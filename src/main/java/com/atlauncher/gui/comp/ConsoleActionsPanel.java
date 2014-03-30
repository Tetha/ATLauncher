package com.atlauncher.gui.comp;

import com.atlauncher.App;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ConsoleActionsPanel extends JPanel {
    private final JButton CLEAR_BUTTON = new JButton("Clear"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.clearConsole();
                App.settings.log("Console Cleared");
            }
        });
    }};
    private final JButton COPYLOG_BUTTON = new JButton("Copy Log"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.log("Copied Log To Clipboar");
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(App.settings.getLog()), null);
            }
        });
    }};
    private final JButton UPLOADLOG_BUTTON = new JButton("Upload Log"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                Utils.uploadLog();
            }
        });
    }};
    private final JButton KILLMC_BUTTON = new JButton("Kill Minecraft"){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        int ret = JOptionPane.showConfirmDialog(
                                App.settings.getParent(),
                                "<html><center>" +
                                        App.settings.getLocalizedString("console.killsure", "<br/><br/>") +
                                "</center></html>",
                                App.settings.getLocalizedString("console.kill"),
                                JOptionPane.YES_OPTION
                        );

                        if(ret == JOptionPane.YES_OPTION){
                            App.settings.killMinecraft();
                            ConsoleActionsPanel.this.KILLMC_BUTTON.setVisible(false);
                        }
                    }
                });
            }
        });
    }};
    private final JButton[] ALL = new JButton[]{
            this.CLEAR_BUTTON, this.COPYLOG_BUTTON,
            this.UPLOADLOG_BUTTON, this.KILLMC_BUTTON
    };

    private final GridBagConstraints gbc = new GridBagConstraints();

    public ConsoleActionsPanel(){
        super(new GridBagLayout());

        this.gbc.gridx = 0;
        this.gbc.gridy = GridBagConstraints.RELATIVE;
        this.gbc.insets.set(0, 5, 0, 0);

        for(JButton button : this.ALL){
            this.add(button, this.gbc);
            this.gbc.gridx++;
        }
    }

    public void showKillMC(){
        this.KILLMC_BUTTON.setVisible(true);
    }

    public void hideKillMC(){
        this.KILLMC_BUTTON.setVisible(false);
    }

    public void localize(){
        this.CLEAR_BUTTON.setText(App.settings.getLocalizedString("console.clear"));
        this.COPYLOG_BUTTON.setText(App.settings.getLocalizedString("console.copy"));
        this.UPLOADLOG_BUTTON.setText(App.settings.getLocalizedString("console.upload"));
        this.KILLMC_BUTTON.setText(App.settings.getLocalizedString("console.kill"));
    }
}