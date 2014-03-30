package com.atlauncher.gui.tab;

import com.atlauncher.App;
import com.atlauncher.data.Constants;
import com.atlauncher.utils.Twitter2HTML;
import com.atlauncher.utils.Utils;
import twitter4j.Status;
import twitter4j.Twitter;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class TwitterTab extends JPanel implements ActionListener {
    private final Timer UPDATER = new Timer(300000, this);

    private final Twitter TWITTER = Constants.TWITTER_FACTORY.getInstance();

    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit(){{
        this.getStyleSheet().addRule("A {color:#00BBCC");
        this.getStyleSheet().addRule("#newsHeader {font-weight:bold;font-size:14px;color:#339933;}");
        this.getStyleSheet().addRule("#newsBody {font-size:10px;padding-left:20px;}");
    }};

    private final JEditorPane FEED = new JEditorPane("text/html", ""){{
        this.setEditable(false);
        this.setSelectionColor(Color.GRAY);
        this.setEditorKit(TwitterTab.this.HTML_KIT);
        this.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event){
                if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
                    Utils.openBrowser(event.getURL());
                }
            }
        });
    }};

    public TwitterTab(){
        super(new BorderLayout());
        this.add(new JScrollPane(this.FEED));

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

        this.FEED.setText("");

        List<Status> statuses = this.TWITTER.getHomeTimeline();

        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append("<body>");

        for(Status status : statuses){
            text.append(Twitter2HTML.toHTML(status));
        }

        text.append("</body>");
        text.append("</html>");

        this.FEED.setText(text.toString());
        this.FEED.setCaretPosition(0);
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