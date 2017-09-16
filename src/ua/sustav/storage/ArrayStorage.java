package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private int size;

    private Resume[] array = new Resume[LIMIT];

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        checkCapacity(size);
        checkResume(resume);
        array[size++] = resume;
    }

    private void checkResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(resume)) {
                throw new IllegalStateException("Resume has already present");
            }
        }
    }

    private void checkCapacity(int size) {
        if (size > array.length) {
            throw new IndexOutOfBoundsException("Exceeding the capacity of the array!");
        }
    }

    @Override
    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(resume)) {
                array[i] = resume;
                break;
            }

        }

    }

    @Override
    public Resume load(String uuid) {
        for (int i = 0; i < size; i++) {
            if (array[i].getUuid().equals(uuid)) {
                return array[i];
            }
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (array[i].getUuid().equals(uuid)) {

            }
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Arrays.sort(array);
        return Arrays.asList(array);
    }

    @Override
    public int size() {
        return array.length;
    }
}
