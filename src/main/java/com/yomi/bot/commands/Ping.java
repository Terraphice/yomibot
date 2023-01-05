package com.yomi.bot.commands;

import com.yomi.bot.SlashCommandRegistrar;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@SuppressWarnings("unused")
public class Ping implements SlashCommandRegistrar {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Returns Pong";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("Pong!").queue();
    }

    @Override
    public void setOptions(SlashCommandData command) {}
}
