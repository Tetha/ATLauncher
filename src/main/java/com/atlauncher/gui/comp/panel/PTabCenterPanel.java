package com.atlauncher.gui.comp.panel;

import com.atlauncher.App;
import com.atlauncher.data.Pack;
import com.atlauncher.gui.NothingToDisplay;
import com.atlauncher.gui.PackDisplay;
import com.atlauncher.utils.Localizer;

import javax.swing.*;
import java.awt.*;
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

        if(i == 0){
            this.CONTENT.add(new NothingToDisplay(Localizer.localize("pack.nodisplay", "\n\n")), this.gbc);
        }
    }

    private List<Pack> get(){
        if(App.settings.sortPacksAlphabetically()){
            return App.settings.getPacksSortedAlphabetically();
        } else{
            return App.settings.getPacksSortedPositionally();
        }
    }
}