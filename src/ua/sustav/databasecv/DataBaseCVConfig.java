package ua.sustav.databasecv;

import ua.sustav.databasecv.storage.IStorage;
import ua.sustav.databasecv.storage.XmlStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class DataBaseCVConfig {
    private static final DataBaseCVConfig INSTANCE = new DataBaseCVConfig();

    private IStorage storage;
    private Properties properties;

    public static DataBaseCVConfig get() {
        return INSTANCE;
    }

    public IStorage getStorage() {
        return storage;
    }

    private DataBaseCVConfig() {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties");
            InputStream dbcvIs = getClass().getClassLoader().getResourceAsStream("databasecv.properties")) {

            LogManager.getLogManager().readConfiguration(is);

            properties = new Properties();
            properties.load(dbcvIs);
            storage = new XmlStorage(properties.getProperty("storage.dir"));
            properties.getProperty("db.url");
            properties.getProperty("db.user");
            properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
