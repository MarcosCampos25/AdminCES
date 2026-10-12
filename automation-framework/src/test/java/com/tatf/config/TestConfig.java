package com.tatf.config;

import com.tatf.core.util.ResourceLoader;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Datos del entorno y configuración de la ejecución, leídos de config.properties.
 * Una propiedad del sistema con la misma clave (-Dclave=valor) tiene prioridad sobre el archivo.
 */
public class TestConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static final String KEY_URL_ADMINCES = "adminces.url";
    private static final String KEY_PASSWORD_ADMINCES = "adminces.password";
    private static final String KEY_DEBUGGING = "browser.debugging";

    private static final Properties PROPERTIES = load();

    private TestConfig() {
    }

    public static String urlAdminces() {
        return get(KEY_URL_ADMINCES);
    }

    public static String passwordAdminces() {
        return get(KEY_PASSWORD_ADMINCES);
    }

    public static boolean debugging() {
        return Boolean.parseBoolean(get(KEY_DEBUGGING));
    }

    private static String get(String key) {
        String value = System.getProperty(key, PROPERTIES.getProperty(key));
        if (value == null) {
            throw new IllegalStateException("Falta la propiedad '" + key + "' en " + CONFIG_FILE);
        }
        return value;
    }

    private static Properties load() {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(ResourceLoader.loadAsString(CONFIG_FILE)));
        } catch (IOException e) {
            throw new UncheckedIOException("Error leyendo " + CONFIG_FILE, e);
        }
        return properties;
    }
}
