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
public class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()){
            throw new IllegalArgumentException("'" + path + "'" + " is't directoru or is't writable");
        }
        
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected void doClear() {
        File[] listOfFiles = dir.listFiles();
        if (listOfFiles == null) return;
        for (File file : listOfFiles) {
            doDelete(file);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume resume) {
        write(file, resume);
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

    @Override
    protected void doUpdate(File file, Resume resume) {
        write(file, resume);
    }

    @Override
    protected Resume doLoad(File file) {
        return read(file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new DataBaseCVException("File " + file + " can't be delete");
        }
    }

    @Override
    protected List<Resume> doSorted() {
        return null;
    }

    @Override
    protected int doSize() {
        return dir.listFiles().length;
    }
}
