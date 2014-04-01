package com.atlauncher.gui.comp.panel;

import com.atlauncher.utils.Localizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PTabTopPanel extends JPanel {
    private final JButton ADDPACK_BUTTON = new JButton(Localizer.localize("pack.addpack")){{
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){

            }
        });
    }};
    private final JButton SEARCH_BUTTON = new JButton(Localizer.localize("common.search")){{

    }};
    private final JButton CLEAR_BUTTON = new JButton(Localizer.localize("common.clear")){{

    }};
    private final JTextField SEARCH_FIELD = new JTextField(16){{

    }};
    private final JCheckBox SERVERS_BOX = new JCheckBox(){{

    }};
    private final JCheckBox PRIVATE_BOX = new JCheckBox(){{

    }};

    private boolean servers = false;
    private boolean privatePacks = false;

    public PTabTopPanel(){
        super(new FlowLayout(FlowLayout.CENTER));

        this.add(this.ADDPACK_BUTTON);
        this.add(this.CLEAR_BUTTON);
        this.add(this.SEARCH_FIELD);
        this.add(this.SEARCH_BUTTON);
        this.add(this.SERVERS_BOX);
        this.add(new JLabel(Localizer.localize("pack.cancreateserver")));
        this.add(this.PRIVATE_BOX);
        this.add(new JLabel(Localizer.localize("pack.privatepacksonly")));
    }
}