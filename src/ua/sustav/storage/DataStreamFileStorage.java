package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.ContactType;
import ua.sustav.model.Resume;

import java.io.*;
import java.util.Map;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class DataStreamFileStorage extends FileStorage {
    private final String NULL = "NULL";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume resume) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacs = resume.getContacts();
            dos.writeInt(contacs.size());
            for (Map.Entry<ContactType, String> entry: resume.getContacts().entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            }
        } catch (IOException e) {
            throw new DataBaseCVException("Can't open file to write " + file.getAbsolutePath(), resume, e);
        }
    }

    protected Resume read(File file) {
        Resume result = new Resume();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            result.setFullName(readString(dis));
            result.setLocation(readString(dis));
            result.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                result.addContact(ContactType.values()[dis.readInt()], readString(dis));
            }
        } catch (IOException e) {
            throw new DataBaseCVException("Can't open file to read " + file.getAbsolutePath(), e);
        }
        result.setUuid(file.getName());
        return result;
    }


    private void writeString(DataOutputStream dos, String value) throws IOException {
        dos.writeUTF(value == null ? NULL: value);
    }

    private String readString(DataInputStream dis) throws IOException {
        String value = dis.readUTF();
        return value.equals(NULL) ? null : value;
    }
}
