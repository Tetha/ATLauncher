package com.atlauncher.utils.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atlauncher.App;
import com.atlauncher.utils.Utils;

public final class JavaLauncherFactory {
    public static JavaLauncher fromJavaPath( String javaPath ) {
        if ( javaPath.equalsIgnoreCase(Utils.getJavaHome()) ) {
            return launcherFromProperties();
        } else {
            return launcherFromBinary( javaPath );
        }
    }
    
    private static JavaLauncher launcherFromProperties() {
        // TODO: Figure out what to do with javas of different versions -- I don't think
        // we want to handle something grusesome like java 5.
        if ( System.getProperty("java.version").substring(0, 3).equalsIgnoreCase("1.8") ) {
            return new Java8OrMoreLauncher();
        } else {
            return new Java7OrLessLauncher();
        }
    }
    
    private static JavaLauncher launcherFromBinary( String javaPath ) {
        File folder = new File(javaPath, "bin/");
        
        
        List<String> arguments = new ArrayList<String>();
        arguments.add(folder + File.separator + "java" + (Utils.isWindows() ? ".exe" : ""));
        arguments.add("-version");
        ProcessBuilder processBuilder = new ProcessBuilder(arguments);
        processBuilder.directory(folder);
        processBuilder.redirectErrorStream(true);
        
        
        BufferedReader br = null;
        try {
            Process process = processBuilder.start();
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = br.readLine(); // Read first line only
            String buildLine = br.readLine();
            if ( line.contains("\"1.8") ) {
                return new Java8OrMoreLauncher();
            } else {
                return new Java7OrLessLauncher();
            }
        } catch (IOException e) {
            App.settings.logStackTrace(e);
        } finally {
            if ( br != null ) {
                try {
                    br.close();
                } catch ( IOException e ) {
                    App.settings.log( "Cannot close input stream reader ");
                    App.settings.logStackTrace( e );
                }
            } 
        }
        return new Java7OrLessLauncher(); // Can't determine version, so fall back to not being Java 8
    }
}
