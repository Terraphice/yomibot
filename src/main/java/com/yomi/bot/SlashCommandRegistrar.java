package com.yomi.bot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface SlashCommandRegistrar {
    void handle(SlashCommandInteractionEvent event);
    String getName();
    String getDescription();
    void setOptions(SlashCommandData command);
}
