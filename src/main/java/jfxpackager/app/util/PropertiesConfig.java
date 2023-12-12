package jfxpackager.app.util;

import java.io.*;
import java.util.Properties;

public class PropertiesConfig {
    private final String nomeDoPropertie = "src/main/resources/theme.properties";
    private final Properties props = new Properties();

    public void setPropertyTheme(String theme) throws IOException {
        switch (theme) {
            case "light" -> props.setProperty("theme", "light");
            case "dark" -> props.setProperty("theme", "dark");
        }
        FileOutputStream os = new FileOutputStream(nomeDoPropertie);
        props.store(os,null);
    }

    public String getProperties() throws IOException {
        FileInputStream in = new FileInputStream(nomeDoPropertie);
        props.load(in);
        String theme = props.getProperty("theme");

        switch (theme){
            case "light" -> {
                return "light";
            }
            case "dark" -> {
                return "dark";
            }
        }

        return theme;
    }
}
