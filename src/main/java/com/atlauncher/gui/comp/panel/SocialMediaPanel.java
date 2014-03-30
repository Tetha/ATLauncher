package com.atlauncher.gui.comp.panel;

import com.atlauncher.App;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SocialMediaPanel extends JPanel {
    private final JButton FACEBOOK_BUTTON = new JButton(Utils.getIconImage("FacebookIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.log("Opening Up ATLauncher Facebook Page");
                Utils.openBrowser("http://www.facebook.com/ATLauncher");
            }
        });
    }};
    private final JButton GITHUB_BUTTON = new JButton(Utils.getIconImage("GitHubIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.log("Opening Up ATLauncher GitHub Page");
                Utils.openBrowser("https://github.com/ATLauncher/ATLauncher");
            }
        });
    }};
    private final JButton REDDIT_BUTTON = new JButton(Utils.getIconImage("RedditIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.log("Opening Up ATLauncher Reddit Page");
                Utils.openBrowser("http://www.reddit.com/r/ATLauncher");
            }
        });
    }};
    private final JButton TWITTER_BUTTON = new JButton(Utils.getIconImage("TwitterIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                App.settings.log("Opening Up ATLauncher Twitter Page");
                Utils.openBrowser("http://www.twitter.com/ATLauncher");
            }
        });
    }};
    private final JButton[] ALL = new JButton[]{
            this.FACEBOOK_BUTTON, this.GITHUB_BUTTON,
            this.REDDIT_BUTTON, this.TWITTER_BUTTON
    };

    private final GridBagConstraints gbc = new GridBagConstraints();

    public SocialMediaPanel(){
        super(new GridBagLayout());

        this.gbc.gridx = 0;
        this.gbc.gridy = GridBagConstraints.RELATIVE;
        this.gbc.insets.set(0, 0, 0, 5);

        for(JButton button : this.ALL){
            this.add(button, this.gbc);
            this.gbc.gridx++;
        }
    }
}