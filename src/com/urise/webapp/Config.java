package com.urise.webapp;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    protected static final String PROPS = "C:\\Users\\voite\\IdeaProjects\\ResumeDB\\Config\\resumes.properties";
    private File Storage_dir;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    private final String password;
    private final String url;
    private final String user;
    private Properties properties = new Properties();
    public static Config get() {
        return INSTANCE;
    }
    public File getStorage_dir(){
        return Storage_dir;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            properties.load(is);
            Storage_dir= new File(properties.getProperty("storage.dir"));
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IllegalStateException("Invalied config gile" + PROPS);
        }
    }


}
