package com.atlauncher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum Localizer{
    INSTANCE;

    private final File LANG_DIR = new File(new File(new File("."), "Configs"), "Languages");
    private final Map<String, Properties> LANGS = new HashMap<String, Properties>();

    private String current = "English";
    private Properties lang = null;

    private Localizer(){
        try {
            this.loadLanguage(this.current);

            this.lang = this.LANGS.get(this.current);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load the english language");
        }
    }

    public String getLanguage(){
        return this.current;
    }

    public void loadLanguage(String name)
    throws IOException {
        if(this.LANGS.containsKey(name)){
            return;
        } else{
            Properties props = new Properties();

            File langFile = new File(this.LANG_DIR, name + ".lang");

            if(langFile.exists()){
                props.load(new FileInputStream(langFile));
            }

            this.LANGS.put(name, props);
        }
    }

    public boolean isLoaded(String name){
        return this.LANGS.containsKey(name);
    }

    public Properties getCurrent(){
        return this.lang;
    }

    public void setCurrent(String current){
        if(this.current == current){
            return;
        } else{
            this.current = current;
        }

        if(this.isLoaded(current)){
            this.lang = this.LANGS.get(this.current);
        } else{
            try{
                this.loadLanguage(current);
                this.lang = this.LANGS.get(current);
            } catch(Exception ex){
                throw new RuntimeException("Error Switching Language to " + current);
            }
        }
    }

    public static String localize(String tag){
        return INSTANCE.getCurrent().getProperty(tag, tag);
    }

    public static String localize(String tag, String pattern){
        return INSTANCE.getCurrent().getProperty(tag, tag).replace("%s", pattern);
    }
}