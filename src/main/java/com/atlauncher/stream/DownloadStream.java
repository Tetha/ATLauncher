package com.atlauncher.stream;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public final class DownloadStream implements Closeable {
    private final ReadableByteChannel rbc;
    private final FileOutputStream fos;

    public DownloadStream(File file, URL url){
        try{
            this.rbc = Channels.newChannel(url.openStream());
            this.fos = new FileOutputStream(file);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void read()
    throws IOException{
        this.fos.getChannel().transferFrom(this.rbc, 0, Long.MAX_VALUE);
    }

    @Override
    public void close()
    throws IOException {
        this.fos.close();
        this.rbc.close();
    }
}