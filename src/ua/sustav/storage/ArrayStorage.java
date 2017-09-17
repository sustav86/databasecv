package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public class ArrayStorage implements IStorage {
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());
    private static final int LIMIT = 100;
    private int size;

    protected Resume[] array = new Resume[LIMIT];

    @Override
    public void clear() {
        LOGGER.info("Delete all resume");
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        LOGGER.info("Save resume uuid = " + resume.getUuid());
        checkCapacity(size);
        int idx = findIndex(resume.getUuid());
        if (idx != -1) {
            throw new DataBaseCVException("Resume has already present uuid = " + resume.getUuid(), resume);
        }
        array[size++] = resume;
    }

    @Override
    public void update(Resume resume) {
        LOGGER.info("Update resume with uuid = " + resume.getUuid());
        int idx = findIndex(resume.getUuid());
        if (idx == -1) {
            throw new DataBaseCVException("Resume with uuid = " + resume.getUuid() + " is't exist", resume);
        }
        array[idx] = resume;
    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with uuid = " + uuid);
        int idx = findIndex(uuid);
        if (idx == -1) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        return array[idx];
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid = " + uuid);
        int idx = findIndex(uuid);
        if (idx == -1) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
        array[--size] = null;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        LOGGER.info("Sorted array of resumes");
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
