package com.atlauncher.utils;

public final class HTMLifier{
    private String text;

    private HTMLifier(String text){
        this.text = text;
    }

    public static HTMLifier wrap(String text){
        return new HTMLifier(text);
    }

    public HTMLifier font(String color){
        this.text = "<font color=" + color + ">" + this.text + "</font>";
        return this;
    }

    public HTMLifier bold(){
        this.text = "<b>" + this.text + "</b>";
        return this;
    }

    @Override
    public String toString(){
        return  this.text;
    }
}