package com.atlauncher.gui.tab;

import com.atlauncher.App;
import com.atlauncher.data.News;
import com.atlauncher.utils.Utils;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class NewsTab extends JPanel implements ActionListener {
    private final Timer UPDATER = new Timer(300000, this);

    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit(){{
        this.setStyleSheet(Utils.createStyleSheet("news"));
    }};

    private final JEditorPane NEWS = new JEditorPane("text/html", ""){{
        this.setEditable(false);
        this.setSelectionColor(Color.GRAY);
        this.setEditorKit(NewsTab.this.HTML_KIT);
        this.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event){

            }
        });
    }};

    public NewsTab(){
        super(new BorderLayout());

        this.add(new JScrollPane(this.NEWS));

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
        this.NEWS.setText("");

        StringBuilder builder = new StringBuilder();
        builder.append("<html>");

        List<News> collected = App.settings.getNews();

        for(int i = 0; i < collected.size(); i++){
            builder.append(collected.get(i));

            if(i < collected.size() - 1){
                builder.append("<hr/>");
            }
        }

        builder.append("</html>");

        this.NEWS.setText(builder.toString());
    }

    @Override
    public void actionPerformed(ActionEvent event){
        try{
            this.loadContent();
        } catch(Exception ex){
            App.settings.logStackTrace(ex);
        }
    }
}