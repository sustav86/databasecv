package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public abstract class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()){
            throw new IllegalArgumentException("'" + path + "'" + " is't directory or is't writable");
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
    protected List<Resume> doGetAll() {
        File[] files = dir.listFiles();
        if (files == null) return Collections.emptyList();
        List<Resume> resumeList = new ArrayList<>(files.length);
        for (File file: files) {
            resumeList.add(read(file));
        }
        return resumeList;
    }

    @Override
    protected int doSize() {
        File[] files = dir.listFiles();
        if (files == null) throw new DataBaseCVException("Couldn't read directory " + dir.getAbsolutePath());
        return files.length;
    }
}
