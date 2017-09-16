package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.Collection;

/**
 * Created by SUSTAVOV on 15.09.2017.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private int size;

    private Resume[] array = new Resume[LIMIT];

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
