package com.atlauncher.gui.tab;

import com.atlauncher.App;
import com.atlauncher.type.Instance;
import com.atlauncher.gui.InstanceDisplay;
import com.atlauncher.gui.NothingToDisplay;

import javax.swing.*;
import java.awt.*;

public final class InstanceTab extends JPanel {
    private final TopPanel TOP_PANEL = new TopPanel();
    private final MiddlePanel MIDDLE_PANEL = new MiddlePanel();

    public InstanceTab(){
        super(new BorderLayout());

        this.add(this.TOP_PANEL, BorderLayout.NORTH);
        this.add(this.MIDDLE_PANEL, BorderLayout.CENTER);
    }

    private final class TopPanel extends JPanel{

    }

    private final class MiddlePanel extends JPanel{
        private final JPanel WRAPPER = new JPanel();
        private final JScrollPane SCROLLER = new JScrollPane(this.WRAPPER){{
            this.getVerticalScrollBar().setUnitIncrement(16);
        }};
        private final GridBagConstraints gbc = new GridBagConstraints();

        private MiddlePanel(){
            super(new BorderLayout());

            this.add(this.SCROLLER, BorderLayout.CENTER);
        }

        private void loadContent(){
            this.gbc.gridx = 0;
            this.gbc.gridy = 0;
            this.gbc.weightx = 1.0;
            this.gbc.fill = GridBagConstraints.BOTH;

            int count = 0;
            for(Instance instance : App.settings.getInstancesSorted()){
                if(instance.canPlay()){
                    boolean show = true;

                    if(show){
                        this.WRAPPER.add(new InstanceDisplay(instance), this.gbc);
                        this.gbc.gridy++;
                        count++;
                    }
                } else{
                    this.WRAPPER.add(new InstanceDisplay(instance), this.gbc);
                    this.gbc.gridy++;
                    count++;
                }
            }

            if(count == 0){
                this.WRAPPER.add(new NothingToDisplay("Nothing To Display"), this.gbc);
            }
        }
    }
}