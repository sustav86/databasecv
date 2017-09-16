package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.*;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private int size;

    protected Resume[] array = new Resume[LIMIT];

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
        int idx = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].getUuid().equals(uuid)) {
                idx = i;
            }
        }
        if (idx != -1) {
            System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
            array[--size] = null;
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> listOfResume = Arrays.asList(array);
        listOfResume.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.compareTo(o2);
            }
            return 0;
        });

        return listOfResume;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayStorage{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
