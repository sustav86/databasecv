package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.ContactType;
import ua.sustav.model.Resume;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class DataStreamFileStorage extends FileStorage {
    private File dir;

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume resume) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeUTF(resume.getFullName());
            dos.writeUTF(resume.getLocation());
            dos.writeUTF(resume.getHomePage());
            for (Map.Entry<ContactType, String> entry: resume.getContacts().entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                dos.writeUTF(entry.getValue());
            }
        } catch (IOException e) {
            throw new DataBaseCVException("Can't open file to write " + file.getAbsolutePath(), resume, e);
        }
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
