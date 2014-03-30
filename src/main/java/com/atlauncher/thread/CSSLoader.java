package com.atlauncher.thread;

import javax.swing.text.html.StyleSheet;
import java.util.concurrent.Callable;

public final class CSSLoader implements Callable<StyleSheet>{
    private final String name;

    public CSSLoader(String name){
        this.name = name;
    }

    @Override
    public StyleSheet call()
    throws Exception{
        StyleSheet sheet = new StyleSheet();

        sheet.importStyleSheet(CSSLoader.class.getResource("/css/" + this.name + ".css"));

        return sheet;
    }
}