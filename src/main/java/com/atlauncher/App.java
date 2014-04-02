/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package com.atlauncher;

import com.atlauncher.data.Settings;
import com.atlauncher.exceptions.InvalidCallbackException;
import com.atlauncher.gui.ConsoleFrame;
import com.atlauncher.gui.LauncherFrame;
import com.atlauncher.gui.SplashScreen;
import com.atlauncher.gui.comp.TrayMenu;
import com.atlauncher.management.Accounts;
import com.atlauncher.management.Resources;
import com.atlauncher.thread.DataDesolver;
import com.atlauncher.thread.Download;
import com.atlauncher.thread.FileSystemResolver;
import com.atlauncher.utils.Localizer;
import com.atlauncher.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class App {
    public static final ExecutorService TASKPOOL = Executors.newFixedThreadPool(2);
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Properties PROPERTIES = new Properties();
    public static final PopupMenu TRAY_MENU;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Color BASE_COLOR = new Color(40, 45, 50);

    private static SystemTray TRAY = null;
    private static File CURRENT;

    public static Settings settings;

    static {
        try {
            setLAF();
            modifyLAF();
            TRAY_MENU = new TrayMenu();
            Localizer.INSTANCE.isLoaded("English");
            resolveCurrent();
            trySystemTrayIntegration();

            Runtime.getRuntime().addShutdownHook(new Thread(new DataDesolver()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static final ConsoleFrame CONSOLE = new ConsoleFrame();
    public static final SplashScreen SPLASH = new SplashScreen();
    public static final LauncherFrame LAUNCHER = new LauncherFrame();

    private App(){}

    public static void main(String... args)
    throws InterruptedException {
        LOGGER.info("Showing Console");
        pre();
        LOGGER.info("Checking Directory");
        checkDirectory();
        LOGGER.info("Resolving File System");
        ensureFileSystem();
        LOGGER.info("Allowing IPv4");
        System.setProperty("java.net.preferIPv4Stack", "true");
        LOGGER.info("Loading Rest");
        settings = new Settings();
        downloadData();
        LOGGER.info("ATLauncher Version: " + settings.getVersion());
        LOGGER.info("Operating System: " + System.getProperty("os.name"));
        LOGGER.info("RAM Available: " + Utils.getMaximumRam() + "MB");
        LOGGER.info("Java Version: " + Utils.getJavaVersion());

        if(settings.isUsingCustomJavaPath()){
            LOGGER.info("Custom Java Path Caught!");
        }

        LOGGER.info("Java Path: " + settings.getJavaPath());

        if(Utils.is64Bit()){
            LOGGER.info("64bit Java Caught");
        }

        LOGGER.info("Working Directory: " + CURRENT.getAbsolutePath());

        if(Utils.isMac()){
            helpMacOut();
        }

        if(args.length > 0){
            if(args[0].equalsIgnoreCase("--launch") && args[1] != null){
                LOGGER.info("Caught Launch Argument, Launching Instance: " + args[1]);
            }
        }

        LOGGER.info("Done Loading");
        post();
    }

    private static void downloadData(){
        try{
            TASKPOOL.submit(new Download(new URL(Accounts.getSkinURL("default")), new File(Resources.INSTANCE.getFile("skins"), "default.png"))).get();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void ensureFileSystem(){
        try{
            Future<Void> f_callback = TASKPOOL.submit(new FileSystemResolver());
            Void callback = f_callback.get();

            if(callback != null){
                throw new InvalidCallbackException();
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void checkDirectory(){
        try{
            File data = Resources.INSTANCE.getFile("Data");

            if(!data.exists()){
                if(CURRENT.listFiles().length > 1){
                    String[] options = { "Yes It's Fine", "Whoops. I'll Change That Now" };
                    int ret = JOptionPane.showOptionDialog(null,
                            "<html><center>I've detected that you may not have installed this "
                                    + "in the right location.<br/><br/>The exe or jar file"
                                    + "should be placed in it's own folder with nothing else "
                                    + "in it<br/><br/>Are you 100% sure that's what you've"
                                    + "done?</center></html>", "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                    if (ret != 0) {
                        System.exit(0);
                    }
                }
            } else{
                File props = new File(data, "ATLauncher.cfg");

                if(!props.exists()){
                    props.createNewFile();
                }

                App.PROPERTIES.load(new InputStreamReader(new FileInputStream(props)));
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void helpMacOut(){
        try{
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty(
                    "com.apple.mrj.application.apple.menu.about.name",
                    "ATLauncher" + settings.getVersion()
            );

            Class clazz = Class.forName("com.apple.eawt.Application");
            Method getApplication = clazz.getDeclaredMethod("getApplication");
            Method setDockIconImage = clazz.getDeclaredMethod("setDockIconImage", new Class[]{Image.class});
            Object app = getApplication.invoke(null);
            setDockIconImage.invoke(app, Utils.getImage("Icon.png"));
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void pre(){
        try{
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    App.CONSOLE.setVisible(true);
                }
            });
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    App.SPLASH.setVisible(true);
                }
            });
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void post(){
        try{
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    App.SPLASH.setVisible(false);
                }
            });
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    App.LAUNCHER.setVisible(true);
                }
            });
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static File getCurrent(){
        return CURRENT;
    }

    private static void resolveCurrent(){
        if(Utils.isLinux()){
            try{
                CURRENT = new File(App.class.getClassLoader().getResource("").toURI());
            } catch(Exception ex){
                CURRENT = new File(System.getProperty("user.dir", "ATLaucher"));
            }
        } else{
            CURRENT =new File(System.getProperty("user.dir"));
        }
    }

    private static void setLAF() throws Exception {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equalsIgnoreCase("nimbus")) {
                UIManager.setLookAndFeel(info.getClassName());
            }
        }
    }

    private static void modifyLAF() throws Exception {
        UIManager.put("control", BASE_COLOR);
        UIManager.put("text", Color.WHITE);
        UIManager.put("nimbusBase", Color.BLACK);
        UIManager.put("nimbusFocus", BASE_COLOR);
        UIManager.put("nimbusBorder", BASE_COLOR);
        UIManager.put("nimbusLightBackground", BASE_COLOR);
        UIManager.put("info", BASE_COLOR);
        UIManager.put("nimbusSelectionBackground", new Color(100, 100, 200));
        UIManager
                .put("Table.focusCellHighlightBorder", BorderFactory.createEmptyBorder(2, 5, 2, 5));
    }

    private static void trySystemTrayIntegration() throws Exception {
        if (SystemTray.isSupported()) {
            TRAY = SystemTray.getSystemTray();

            Image trayIconImage = Utils.getImage("Icon.png");
            int trayIconWidth = new TrayIcon(Utils.getImage("Icon.png")).getSize().width;
            TRAY.add(new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1,
                    Image.SCALE_SMOOTH), "tray_icon") {
                {
                    this.setPopupMenu(App.TRAY_MENU);
                    this.setToolTip("ATLauncher");
                }
            });
        }
    }

}