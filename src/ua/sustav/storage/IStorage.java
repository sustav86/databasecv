package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.Collection;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public interface IStorage {
    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();
}
