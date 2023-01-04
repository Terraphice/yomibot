package com.yomi.bot;

import net.dv8tion.jda.api.entities.Message;

public interface SlashCommandRegistrar {
    void handle(Message message);
    String getName();
    String getDescription();
}
