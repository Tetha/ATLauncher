package com.atlauncher.management;

import com.atlauncher.App;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Resources{
    INSTANCE;

    private final List<File> FILES = new LinkedList<File>();

    private Resources(){
        this.FILES.add(insideCurrent("libs"));
        this.FILES.add(insideCurrent("instances"));
        this.FILES.add(insideCurrent("tmp", true));
        this.FILES.add(insideCurrent("data"));
        this.FILES.add(new File(getFile("Data"), "Skins"));
        this.FILES.add(new File(getFile("Data"), "Accounts"));
        this.FILES.add(insideCurrent("downloads"));
    }

    public File insideCurrent(String name){
        return this.insideCurrent(name, false);
    }

    public File insideCurrent(String name, boolean temp){
        File file = new File(App.getCurrent(), name);

        if(temp){
            file.deleteOnExit();
        }

        return file;
    }

    public static void ensure(File file){
        INSTANCE.FILES.add(file);
    }

    public static List<File> all(){
        return Collections.unmodifiableList(INSTANCE.FILES);
    }

    public File getFile(String name){
        for(File file : this.FILES){
            if(file.getName().equalsIgnoreCase(name)){
                return file;
            }
        }

        return null;
    }
}