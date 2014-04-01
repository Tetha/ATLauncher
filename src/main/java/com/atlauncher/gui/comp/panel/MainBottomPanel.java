package com.atlauncher.gui.comp.panel;

import com.atlauncher.App;
import com.atlauncher.data.Account;
import com.atlauncher.gui.AccountsDropDownRenderer;
import com.atlauncher.gui.CustomLineBorder;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MainBottomPanel extends JPanel {
    private final MiddlePanel MIDDLE_PANEL = new MiddlePanel();
    private final SocialMediaPanel RIGHT_PANEL = new SocialMediaPanel();
    private final LeftPanel LEFT_PANEL = new LeftPanel();

    public MainBottomPanel(){
        super(new BorderLayout());

        this.setBorder(BorderFactory.createEtchedBorder());
        this.setPreferredSize(new Dimension(0, 50));

        this.add(this.MIDDLE_PANEL, BorderLayout.CENTER);
        this.add(this.LEFT_PANEL, BorderLayout.WEST);
        this.add(this.RIGHT_PANEL, BorderLayout.EAST);
    }

    private final class MiddlePanel extends JPanel{
        private final JComboBox<Account> ACCOUNTS_BOX = new JComboBox<Account>(){{
            this.setRenderer(new AccountsDropDownRenderer());
            this.addItem(new Account(Localizer.localize("account.select")));

            for(Account acc : App.settings.getAccounts()){
                this.addItem(acc);
            }

            if(App.settings.getAccount() == null){
                this.setSelectedIndex(0);
            } else{
                this.setSelectedItem(App.settings.getAccount());
            }
        }};

        private final JLabel STATUS_ICON = new JLabel(Utils.getIconImage("StatusWhite.png")){{
            this.setBorder(BorderFactory.createEmptyBorder());
            this.setToolTipText(Localizer.localize("status.minecraft.checking"));
        }

            @Override
            public JToolTip createToolTip(){
                JToolTip tooltip = super.createToolTip();

                tooltip.setBorder(new CustomLineBorder(5, new Color(80, 170, 107), 2));

                return tooltip;
            }
        };

        private final GridBagConstraints gbc = new GridBagConstraints();

        private MiddlePanel(){
            super(new GridBagLayout());

            this.gbc.gridx = 0;
            this.gbc.gridy = GridBagConstraints.RELATIVE;
            this.gbc.insets.set(0, 0, 0, 5);

            this.add(this.ACCOUNTS_BOX, this.gbc);
            this.gbc.gridx++;
            this.add(this.STATUS_ICON, this.gbc);
        }
    }


    private final class LeftPanel extends JPanel{
        private final JButton TC_BUTTON = new JButton(){{
            if(App.settings.isConsoleVisible()){
                this.setText(Localizer.localize("console.hide"));
            } else{
                this.setText(Localizer.localize("console.show"));
            }

            this.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){

                }
            });
        }};
        private final JButton OPENFOLDER_BUTTON = new JButton(Localizer.localize("common.openfolder")){{
            this.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){

                }
            });
        }};
        private final JButton UPDATEDATA_BUTTON = new JButton(Localizer.localize("common.updatedata")){{
            this.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){

                }
            });
        }};

        private final GridBagConstraints gbc = new GridBagConstraints();

        private LeftPanel(){
            super(new GridBagLayout());

            this.gbc.gridx = 0;
            this.gbc.gridy = GridBagConstraints.RELATIVE;
            this.gbc.insets.set(0, 0, 0, 5);
            this.add(this.TC_BUTTON, this.gbc);
            this.gbc.gridx++;
            this.add(this.OPENFOLDER_BUTTON, this.gbc);
            this.gbc.gridx++;
            this.add(this.UPDATEDATA_BUTTON, this.gbc);
        }
    }
}