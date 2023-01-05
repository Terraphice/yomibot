package com.yomi.bot.commands;

import com.yomi.bot.SlashCommandRegistrar;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@SuppressWarnings("unused")
public class Say implements SlashCommandRegistrar {

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "Responds with the inputted text";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        try {
            //noinspection DataFlowIssue
            event.reply(event.getOption("message").getAsString()).queue();
        } catch (NullPointerException e) {
            event.reply("Invalid syntax, please provide a message.")
                    .setEphemeral(true).queue();
        }
    }

    @Override
    public void setOptions(SlashCommandData command) {
        command.addOption(OptionType.STRING,
                "message",
                "What you wish for the bot to respond with");
    }
}
