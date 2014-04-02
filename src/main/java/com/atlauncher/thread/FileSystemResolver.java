package com.atlauncher.thread;

import com.atlauncher.App;
import com.atlauncher.management.Resources;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public final class FileSystemResolver implements Callable<Void>{
    @Override
    public Void call()
    throws IOException {
        for(File file : Resources.all()){
            App.LOGGER.info("Validating File: " + file);

            if(!file.exists()){
                App.LOGGER.info("Creating File: " + file);

                file.mkdir();
            }
        }
        return null;
    }
}