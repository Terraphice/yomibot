package com.yomi;

import com.yomi.bot.DiscordBotHandler;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> config = ConfigHandler.loadConfig();
        DiscordBotHandler.init(config);
    }
}
