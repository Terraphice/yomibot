package com.yomi.bot.commands;

import com.yomi.bot.SlashCommandRegistrar;
import net.dv8tion.jda.api.entities.Message;

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
    public void handle(Message message) {
        message.getChannel().sendMessage("Hello, world!").queue();
    }

}
