package com.yomi;

import com.yomi.bot.DiscordBotHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MainClass {
    public static void main(final String[] args) throws
            InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        final Map<String, Object> config = ConfigHandler.loadConfig();
        DiscordBotHandler.init(config);
    }
}
