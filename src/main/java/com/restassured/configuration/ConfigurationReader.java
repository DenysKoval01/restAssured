package com.restassured.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationReader {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationReader.class);

    private ConfigurationReader() {
    }

    public static Properties getPropertiesFromFile(String pathToFile) {
        Properties props = new Properties();
        try (final InputStream stream = ConfigurationReader.class.getClassLoader().getResourceAsStream(pathToFile)) {
            props.load(stream);
        } catch (IOException e) {
            LOG.error("Problem occurred during reading properties file", e);
        }
        return props;
    }
}
