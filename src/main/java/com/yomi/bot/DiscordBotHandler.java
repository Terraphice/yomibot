package com.yomi.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.util.Map;

public class DiscordBotHandler {
    public static void init(Map<String, Object> config) {
        JDABuilder jdabuilder = JDABuilder.createDefault(
                config.get("token").toString()
                )
                .addEventListeners(new Events());
        JDA jda = jdabuilder.build();
        jda.addEventListener(new Events());
    }
}
