package com.atlauncher.thread;

import com.atlauncher.App;
import com.atlauncher.stream.DownloadStream;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public final class Download implements Callable<Void>{
    private final URL url;
    private final File file;

    public Download(URL url, File file){
        this.url = url;
        this.file = file;
    }

    public Download(String url, File file)
    throws MalformedURLException{
        this(new URL(url), file);
    }

    @Override
    public Void call()
    throws Exception {
        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();

        if(conn.getResponseCode() == 200){
            DownloadStream stream = new DownloadStream(this.file, this.url);
            stream.read();
            stream.close();
        } else{
            App.LOGGER.info("Server Responded With " + conn.getResponseCode());
        }

        return null;
    }
}
