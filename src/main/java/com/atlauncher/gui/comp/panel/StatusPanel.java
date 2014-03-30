package com.atlauncher.gui.comp.panel;

import com.atlauncher.utils.Utils;
import twitter4j.Status;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public final class StatusPanel extends JPanel {
    private final JLabel USERNAME_LABEL = new JLabel(){{
        this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(18.0F));
        this.setHorizontalAlignment(JLabel.LEFT);
    }};
    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit(){{
        this.setStyleSheet(Utils.createStyleSheet("twitter"));
    }};
    private final JEditorPane CONTENTS_AREA = new JEditorPane("text/html", ""){{
        this.setFont(Utils.makeFont("Oswald-Regular").deriveFont(12.0F));
        this.setEditorKit(StatusPanel.this.HTML_KIT);
    }};

    public StatusPanel(Status status){
        super(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));

        this.USERNAME_LABEL.setText(status.getUser().getName());
        this.CONTENTS_AREA.setText(status.getText());

        this.add(this.USERNAME_LABEL, BorderLayout.PAGE_START);
        this.add(new JScrollPane(this.CONTENTS_AREA), BorderLayout.CENTER);
    }
}
