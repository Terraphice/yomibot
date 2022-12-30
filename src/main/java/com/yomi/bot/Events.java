package com.yomi.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Events extends ListenerAdapter {
    public static final Logger LOGGER = LoggerFactory.getLogger(Events.class);
    public Events() {super();}
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(event.getMessage().getContentDisplay());
        }
    }
}
