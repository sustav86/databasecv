package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.util.*;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public class ArrayStorage extends AbstractStorage {
    private static final int LIMIT = 100;
    private int size;

    protected Resume[] array = new Resume[LIMIT];

    @Override
    protected void doClear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doSave(Resume resume) {
        checkCapacity(size);
        int idx = findIndex(resume.getUuid());
        array[size++] = resume;
    }

    @Override
    protected void doUpdate(Resume resume) {
        int idx = findIndex(resume.getUuid());
        array[idx] = resume;
    }

    @Override
    protected Resume doLoad(String uuid) {
        int idx = findIndex(uuid);
        return array[idx];
    }

    @Override
    protected void doDelete(String uuid) {
        int idx = findIndex(uuid);
        System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
        array[--size] = null;
    }

    @Override
    protected Collection<Resume> doSorted() {
        List<Resume> listOfResume = Arrays.asList(Arrays.copyOf(array, size));
        listOfResume.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.compareTo(o2);
            }
            return 0;
        });

        return listOfResume;
    }

    @Override
    protected int doSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayStorage{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }

    private void checkCapacity(int size) {
        if (size > array.length) {
            throw new DataBaseCVException("Exceeding the capacity of the array!");
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}
