package com.yomi.bot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.yomi.bot.DiscordBotHandler.loadCommands;

public class EventRegistrar extends ListenerAdapter {
    // yes, I know there's no constructor,
    // but PMD can deal with it; It's unneeded
    final List<Class<?>> commandList = loadCommands();
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        for (Class<?> clazz : commandList) {
                // Get the constructor for the class
            Constructor<?> constructor;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            // Create a new instance of the class
                SlashCommandRegistrar command;
                try {
                    command = (SlashCommandRegistrar) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                if (command.getName().equalsIgnoreCase(event.getInteraction().getName())) {
                    command.handle(event);
                    break;
            }
        }
    }
}
