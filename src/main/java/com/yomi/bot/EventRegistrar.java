package com.yomi.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventRegistrar extends ListenerAdapter {
    // yes, I know there's no constructor,
    // but PMD can deal with it; It's unneeded
    public static final Logger LOGGER = LoggerFactory.getLogger(EventRegistrar.class);
    public void onMessageReceived(final @NotNull MessageReceivedEvent event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Message received: " + event.getMessage().getContentDisplay());
        }
    }
}