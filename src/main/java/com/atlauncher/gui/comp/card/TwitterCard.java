package com.atlauncher.gui.comp.card;

import com.atlauncher.utils.Utils;

import twitter4j.Status;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.*;

public final class TwitterCard extends JPanel {

    private final JLabel USERNAME_LABEL = new JLabel() {
        {
            this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(18.0F));
            this.setHorizontalAlignment(JLabel.LEFT);
        }
    };

    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit() {
        {
            this.setStyleSheet(Utils.createStyleSheet("twitter"));
        }
    };

    private final JEditorPane CONTENTS_AREA = new JEditorPane("text/html", "") {
        {
            this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(12.0F));
            this.setEditorKit(TwitterCard.this.HTML_KIT);
            this.setEditable(false);
            this.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent event) {
                    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        Utils.openBrowser(event.getURL());
                    }
                }
            });
        }
    };

    public TwitterCard(Status status) {
        super(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));

        this.USERNAME_LABEL.setText(status.getUser().getName());
        this.CONTENTS_AREA.setText(status.getText());

        this.add(this.USERNAME_LABEL, BorderLayout.PAGE_START);
        this.add(new JScrollPane(this.CONTENTS_AREA), BorderLayout.CENTER);
    }
}
