package com.yomi.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class DiscordBotHandler {
    public static void init(final Map<String, Object> config) throws
            NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        final String token = (String) config.get("token");
        final JDABuilder jdabuilder = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT);
        final JDA jda = jdabuilder.build();
        jda.addEventListener(new EventRegistrar());
        registerCommands(jda);
    }

    static List<Class<?>> loadCommands() {
        List<Class<?>> commandClasses = new ArrayList<>();

        ClassLoader classLoader = DiscordBotHandler.class.getClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources("com/yomi/bot/commands");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File directory = new File(url.getFile());
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                // Check if the file is a class file
                if (file.getName().endsWith(".class")) {
                    // Get the class object for the file
                    Class<?> clazz;
                    try {
                        clazz = classLoader.loadClass("com.yomi.bot.commands" +
                                    "." + file.getName().replace(".class", ""));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    // Check if the class implements the SlashCommand interface
                    if (Arrays.asList(clazz.getInterfaces())
                            .contains(SlashCommandRegistrar.class)) {
                        commandClasses.add(clazz);
                    }
                }
            }
        }
        return commandClasses;
    }

    private static void registerCommands(JDA jda) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException,
            IllegalAccessException {
        List<Class<?>> commandClasses = loadCommands();

        CommandListUpdateAction updateCommands = null;
        for (Class<?> clazz : commandClasses) {
            // Get the constructor for the class
            Constructor<?> constructor = clazz.getConstructor();
            // Create a new instance of the class
            SlashCommandRegistrar command =
                    (SlashCommandRegistrar) constructor.newInstance();
            String commandName = command.getName();
            String commandDescription = command.getDescription();
            SlashCommandData slashCommand =
                    Commands.slash(
                            commandName.toLowerCase(),
                            commandDescription);
            command.setOptions(slashCommand);
            updateCommands = jda.updateCommands().addCommands(
                    slashCommand);
        }
        assert updateCommands != null;
        updateCommands.queue();
    }
}
