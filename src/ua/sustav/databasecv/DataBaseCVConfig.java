package ua.sustav.databasecv;

import ua.sustav.databasecv.storage.IStorage;
import ua.sustav.databasecv.storage.XmlStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class DataBaseCVConfig {
    private static final DataBaseCVConfig INSTANCE = new DataBaseCVConfig();

    private IStorage storage;

    public static DataBaseCVConfig get() {
        return INSTANCE;
    }

    public IStorage getStorage() {
        return storage;
    }

    private DataBaseCVConfig() {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
//            LogManager.getLogManager().readConfiguration(is);
            storage = new XmlStorage("C:\\Users\\SUSTAVOV\\Documents\\myProject\\databasecv\\file_storage");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
