package com.atlauncher.gui.tab;

import com.atlauncher.gui.layer.BlurLayerUI;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class SocialMediaTab extends JPanel {
    private final JTabbedPane TABS = new JTabbedPane(JTabbedPane.LEFT);

    private final JPanel TWITTER_TAB = new TwitterTab();
    private final JPanel GITHUB_TAB = new GitHubTab();
    private final PacksTab PACKS_TAB = new PacksTab();

    public final BlurLayerUI BLUR_UI = new BlurLayerUI();
    private final JLayer<JPanel> BLUR_LAYER = new JLayer<JPanel>(this.PACKS_TAB, this.BLUR_UI);

    public SocialMediaTab(){
        super(new GridLayout(1, 1));

        this.TABS.setBackground(new Color(40, 45, 50).brighter());
        this.TABS.setFont(Utils.makeFont("Oswald-Regular").deriveFont((float) 34));
        this.TABS.setOpaque(true);
        this.TABS.addTab(Localizer.localize("common.search"), this.TWITTER_TAB);
        this.TABS.addTab("GitHub", this.GITHUB_TAB);
        this.TABS.addTab("Testing", this.PACKS_TAB);

        this.PACKS_TAB.add(this.BLUR_LAYER);

        this.add(this.TABS);
    }
}