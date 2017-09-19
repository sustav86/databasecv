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

    protected void write(File file, Resume resume) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
           oos.writeObject(resume);
        }catch (IOException e) {
            throw new DataBaseCVException("Can't open file to serialize " + file.getAbsolutePath(), resume, e);
        }
    }

    protected Resume read(File file) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Resume) ois.readObject();
        } catch (IOException e) {
            throw new DataBaseCVException("Can't open file to read " + file.getAbsolutePath(), e);
        } catch (ClassNotFoundException e) {
            throw new DataBaseCVException("Can't cast serialize file to Resume " + file.getAbsolutePath(), e);
        }
    }

}
