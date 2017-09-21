package ua.sustav.databasecv.storage;

import ua.sustav.databasecv.model.Resume;

import java.util.Collection;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class SqlStorage implements IStorage {
    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
