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
public abstract class FileStorage extends AbstractStorage<File> {
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

    protected abstract void write(File file, Resume resume);

    protected abstract Resume read(File file);

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
