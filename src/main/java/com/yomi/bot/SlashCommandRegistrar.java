package com.yomi.bot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommandRegistrar {
    void handle(SlashCommandInteractionEvent event);
    String getName();
    String getDescription();
}
