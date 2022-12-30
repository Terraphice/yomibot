package com.yomi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ConfigHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHandler.class);
    public static Map<String, Object> loadConfig() {
        Map<String, Object> config = null;

        try {
            //Loading config file
            final InputStream inputStream = new FileInputStream("config.yml");
            final Yaml yaml = new Yaml();
            //set the config object
            config = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("config.yml is not found! " +
                        "Please copy config-example.yml to config.yml");
            }
            System.exit(1);
        }

        //check for any updates or problems to the bots config
        updateCheck(config);

        return config;
    }

    public static void updateCheck(final Map<String, Object> config) {
        final Map<String, Object> exampleConfig = loadExampleConfig();
        checkForUnconfiguredValues(config, exampleConfig);
        checkForMissingKeys(config, exampleConfig);
    }

    private static Map<String, Object> loadExampleConfig() {
        Yaml yaml = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("config-example.yml");
            yaml = new Yaml();
        } catch (FileNotFoundException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("config-example.yml is not found! " +
                        "This should not happen unless you deleted it. " +
                        "It is needed for update checks, please put it back");
            }
            System.exit(1);
        }
        return yaml.load(inputStream);
    }
    private static void checkForUnconfiguredValues(final Map<String, Object> config, final Map<String, Object> exampleConfig) {
        for (final Map.Entry<String, Object> configIteration : config.entrySet()) {
            if (exampleConfig.get(configIteration.getKey()) == null && LOGGER.isWarnEnabled()) {
                LOGGER.warn("Key found in config not present in example config: " + configIteration.getKey());
            }
            final Object configValue = configIteration.getValue();
            final Object exampleValue = exampleConfig.get(configIteration.getKey());
            if (configValue == exampleValue && LOGGER.isErrorEnabled()) {
                LOGGER.error("Config value unchanged, please configure key \"" + configIteration.getKey() + "\"");
                System.exit(1);
            }
        }
    }
    private static void checkForMissingKeys(final Map<String, Object> config, final Map<String, Object> exampleConfig) {
        for (final Map.Entry<String, Object> exampleConfigI : exampleConfig.entrySet()) {
            if (config.get(exampleConfigI.getKey()) == null && LOGGER.isErrorEnabled()) {
                LOGGER.error("Key found in example config not present in config: " + exampleConfigI.getKey() +
                        "\nPlease copy all missing values and set them.");
                System.exit(1);
            }
        }
    }
}
