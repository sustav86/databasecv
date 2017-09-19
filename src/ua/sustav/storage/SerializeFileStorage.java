package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.ContactType;
import ua.sustav.model.Resume;

import java.io.*;
import java.util.Map;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class SerializeFileStorage extends FileStorage {
    private File dir;

    public SerializeFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume resume) {

    }

    protected Resume read(File file) {
        Resume result = new Resume();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {

        } catch (IOException e) {
            throw new DataBaseCVException("Can't open file to read " + file.getAbsolutePath(), e);
        }

        return result;
    }

}
