package com.yomi;

import com.yomi.bot.DiscordBotHandler;

import java.util.Map;

public class Main {
    public static void main(final String[] args) {
        final Map<String, Object> config = ConfigHandler.loadConfig();
        DiscordBotHandler.init(config);
    }
}
