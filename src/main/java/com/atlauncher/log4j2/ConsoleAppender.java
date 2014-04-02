package com.atlauncher.log4j2;

import com.atlauncher.App;
import com.atlauncher.utils.HTMLifier;
import com.atlauncher.utils.Timestampper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.OutputStreamManager;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Plugin(name = "ATLauncher-Console", category = "Core", elementType = "appender", printObject = true)
public final class ConsoleAppender extends AbstractOutputStreamAppender{
    private final Map<Level, String> LEVEL_COLOURS = new HashMap<Level, String>(){{
        this.put(Level.INFO, "white");
    }};

    protected ConsoleAppender(String name, Layout<? extends Serializable> layout, Filter filter, boolean ignoreExceptions, boolean immediateFlush, OutputStreamManager manager) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
    }

    @Override
    public void append(LogEvent event){
        App.CONSOLE.log(String.format("<html>[%s] [%s] %s</html>",
                Timestampper.now(),
                HTMLifier.wrap(event.getLevel().name()).bold().font(this.LEVEL_COLOURS.get(event.getLevel())),
                event.getMessage().getFormattedMessage()
        ));
    }

    @PluginFactory()
    public static ConsoleAppender createAppender(){
        return new ConsoleAppender("ATLauncher-Console", null, null, false, false, null);
    }
}