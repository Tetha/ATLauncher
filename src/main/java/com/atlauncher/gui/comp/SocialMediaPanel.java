package com.atlauncher.gui.comp;

import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class SocialMediaPanel extends JPanel {
    private final JButton FACEBOOK_BUTTON = new JButton(Utils.getIconImage("FacebookIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }};
    private final JButton GITHUB_BUTTON = new JButton(Utils.getIconImage("GitHubIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }};
    private final JButton REDDIT_BUTTON = new JButton(Utils.getIconImage("RedditIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }};
    private final JButton TWITTER_BUTTON = new JButton(Utils.getIconImage("TwitterIcon.png")){{
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }};
}