package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.io.*;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class SerializeFileStorage extends FileStorage {
    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new DataBaseCVException("Can't cast serialize file to Resume ", e);
        }
    }

}
