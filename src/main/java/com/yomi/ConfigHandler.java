package com.yomi;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ConfigHandler {
    public static Map<String, Object> loadConfig() {
        Map<String, Object> config;

        try {
            //Loading config file
            InputStream inputStream = new FileInputStream("config.yml");
            Yaml yaml = new Yaml();
            //set the config object
            config = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //check for any updates or problems to the bots config
        updateCheck(config);

        return config;
    }

    public static void updateCheck(Map<String, Object> config) {
        Map<String, Object> exampleConfig;

        try {
            //Loading config example file
            InputStream inputStream = new FileInputStream("config-example.yml");
            Yaml yaml = new Yaml();
            //set the config object
            exampleConfig = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //check for un-configured values in config, or useless values in config
        for (Map.Entry<String, Object> configIteration : config.entrySet()) {
            //Check for keys in config not present in example config, this isn't really a problem so just warn.
            if (exampleConfig.get(configIteration.getKey()) == null) {
                System.out.println("[WARNING] Key found in config not present in example config: " + configIteration.getKey());
            }
            if (configIteration.getValue().equals(exampleConfig.get(configIteration.getKey()))) {
                System.out.println("[ERROR] Config value unchanged, please configure key \"" + configIteration.getKey() + "\"");
                System.exit(1);
            }
        }

        //check for missing keys in config
        for (Map.Entry<String, Object> exampleConfigIteration : exampleConfig.entrySet()) {
            if (config.get(exampleConfigIteration.getKey()) == null) {
                System.out.println("[ERROR] Key found in example config not present in config: " + exampleConfigIteration.getKey() +
                        "\nPlease copy all missing values and set them.");
                System.exit(1);
            }
        }
    }
}
