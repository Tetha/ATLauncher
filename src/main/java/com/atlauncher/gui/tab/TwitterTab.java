package com.atlauncher.gui.tab;

import com.atlauncher.App;
import com.atlauncher.data.Constants;
import com.atlauncher.gui.comp.card.TwitterCard;
import twitter4j.Status;
import twitter4j.Twitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class TwitterTab extends JPanel implements ActionListener {
    private final Timer UPDATER = new Timer(300000, this);

    private final Twitter TWITTER = Constants.TWITTER_FACTORY.getInstance();
    private final JPanel CONTENT = new JPanel(new GridBagLayout());

    public TwitterTab(){
        super(new BorderLayout());

        this.add(new JScrollPane(this.CONTENT), BorderLayout.CENTER);

        App.TASKPOOL.execute(new Runnable(){
            @Override
            public void run(){
                try{
                    loadContent();
                } catch(Exception ex){
                    App.settings.logStackTrace(ex);
                }
            }
        });

        this.UPDATER.start();
    }

    private void loadContent()
    throws Exception{
        App.settings.log("Updating Twitter");

        this.CONTENT.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.set(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.BOTH;

        List<Status> statuses = this.TWITTER.getHomeTimeline();

        for(Status status : statuses){
            this.CONTENT.add(new TwitterCard(status), gbc);
            gbc.gridy++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            this.loadContent();
        } catch(Exception ex){
            App.settings.logStackTrace(ex);
        }
    }
}