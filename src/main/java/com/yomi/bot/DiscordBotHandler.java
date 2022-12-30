package com.yomi.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.util.Map;

public class DiscordBotHandler {
    public static void init(final Map<String, Object> config) {
        final JDABuilder jdabuilder = JDABuilder.createDefault(
                config.get("token").toString()
                )
                .addEventListeners(new Events());
        final JDA jda = jdabuilder.build();
        jda.addEventListener(new Events());
    }
}
