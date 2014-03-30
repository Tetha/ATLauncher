package com.atlauncher.gui.comp;

import com.atlauncher.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TrayMenu extends PopupMenu {
    private final MenuItem KILLMC_BUTTON = new MenuItem(){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(App.settings.isConsoleVisible()){
                    App.settings.setConsoleVisible(false);
                    setLabel("Show Console");
                } else{
                    App.settings.setConsoleVisible(true);
                    setLabel("Hide Console");
                }
            }
        });
    }};

    private final MenuItem TC_BUTTON = new MenuItem(){{
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
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
                                // TODO: This won't remove the button from the console
                                App.settings.killMinecraft();
                                TrayMenu.this.remove(3);
                            }
                        }
                    }
                });
            }
        });
    }};

    private final MenuItem QUIT_BUTTON = new MenuItem(){{
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
    }};

    public TrayMenu(){
        super();

        this.add(this.KILLMC_BUTTON);
        this.add(this.TC_BUTTON);
        this.addSeparator();
        this.add(this.QUIT_BUTTON);
    }
}