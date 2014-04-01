package com.atlauncher.gui.diag;

import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;

public final class AddPackDialog extends JDialog {
    private final TopPanel TOP_PANEL = new TopPanel();
    private final MiddlePanel MIDDLE_PANEL = new MiddlePanel();
    private final BottomPanel BOTTOM_PANEL = new BottomPanel();

    public AddPackDialog(){
        super(null, Localizer.localize("pack.addpack"), ModalityType.APPLICATION_MODAL);
        this.setMinimumSize(new Dimension(300, 150));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setIconImage(Utils.getImage("Icon.png"));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        this.add(this.TOP_PANEL, BorderLayout.NORTH);
        this.add(this.MIDDLE_PANEL, BorderLayout.CENTER);
        this.add(this.BOTTOM_PANEL, BorderLayout.SOUTH);
    }

    private final class TopPanel extends JPanel{
        private TopPanel(){
            super();

            this.add(new JLabel(Localizer.localize("pack.addpack")));
        }
    }

    private final class MiddlePanel extends JPanel{
        private final JTextField PC_FIELD = new JTextField(16);
        private final GridBagConstraints gbc = new GridBagConstraints();

        private MiddlePanel(){
            super(new GridBagLayout());

            this.gbc.gridx = 0;
            this.gbc.gridy = 0;
            this.gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
            this.add(new JLabel(Localizer.localize("pack.packcode") + ": "));
            this.gbc.gridx++;
            this.gbc.anchor = GridBagConstraints.BASELINE_LEADING;
            this.add(this.PC_FIELD, this.gbc);
        }
    }

    private final class BottomPanel extends JPanel{

    }
}