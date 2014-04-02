package com.atlauncher.gui.comp.panel;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public final class LoggingPanel extends JPanel {
    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit();
    private final HTMLDocument HTML_DOC = new HTMLDocument();
    private final JEditorPane OUTPUT_PANE = new JEditorPane("text/html", ""){{
        this.setEditorKit(LoggingPanel.this.HTML_KIT);
        this.setDocument(LoggingPanel.this.HTML_DOC);
        this.setEditable(false);
        this.setSelectionColor(Color.GRAY);
    }
        @Override
        public boolean getScrollableTracksViewportWidth(){
            return true;
        }
    };

    public LoggingPanel(){
        super(new GridLayout(1, 1));

        this.add(new JScrollPane(this.OUTPUT_PANE, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
    }

    public int getDocLength(){
        return this.HTML_DOC.getLength();
    }

    public String getText(){
        synchronized(this.HTML_KIT)
        {
            try{
                return this.OUTPUT_PANE.getText();
            } catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public void clear(){
        synchronized(this.HTML_KIT)
        {
            try{
                this.OUTPUT_PANE.setText("");
            } catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public void log(String message){
        synchronized(this.HTML_KIT)
        {
            try{
                this.HTML_KIT.insertHTML(this.HTML_DOC, this.getDocLength(), message, 0, 0, null);
                this.OUTPUT_PANE.setCaretPosition(this.getDocLength());
            } catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }
}