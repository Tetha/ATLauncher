package com.atlauncher.gui.comp.panel;

import com.atlauncher.type.Pack;
import com.atlauncher.gui.PackDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public final class PTabCenterPanel extends JPanel {
    private final JPanel CONTENT = new JPanel(new GridBagLayout());
    private final JScrollPane SCROLLER = new JScrollPane(this.CONTENT){{
        this.getVerticalScrollBar().setUnitIncrement(16);
    }};
    private final GridBagConstraints gbc = new GridBagConstraints();

    public PTabCenterPanel(){
        super(new BorderLayout());

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weightx = 1.0;
        this.gbc.fill = GridBagConstraints.BOTH;

        this.loadContent();

        this.add(this.SCROLLER, BorderLayout.CENTER);
    }

    public void loadContent(){
        List<Pack> packs = this.get();
        int i;
        for(i = 0; i < packs.size(); i++){
            Pack p = packs.get(i);

            if(p.canInstall()){
                PackDisplay disp = new PackDisplay(p);

                this.CONTENT.add(disp, this.gbc);
                this.gbc.gridy++;
            }
        }
    }

    private List<Pack> get(){
        return new LinkedList<Pack>();
    }
}