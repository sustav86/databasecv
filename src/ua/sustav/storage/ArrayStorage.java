package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.util.*;

/**
 * Created by SUSTAVOV
 * on 15.09.2017.
 */
public class ArrayStorage extends AbstractStorage<Integer> {
    private static final int LIMIT = 100;
    private int size;

    private Resume[] array = new Resume[LIMIT];

    @Override
    protected Integer getContext(String uuid) {
        return findIndex(uuid);
    }

    @Override
    protected void doClear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Integer ctx) {
        return ctx != -1;
    }

    @Override
    protected void doSave(Integer ctx, Resume resume) {
        checkCapacity(size);
        array[size++] = resume;
    }

    @Override
    protected void doUpdate(Integer ctx, Resume resume) {
        array[ctx] = resume;
    }

    @Override
    protected Resume doLoad(Integer ctx) {
        return array[ctx];
    }

    @Override
    protected void doDelete(Integer ctx) {
        System.arraycopy(array, ctx + 1, array, ctx, size - ctx - 1);
        array[--size] = null;
    }


    @Override
    protected List<Resume> doSorted() {
        List<Resume> listOfResume = Arrays.asList(Arrays.copyOf(array, size));
//        listOfResume.sort((o1, o2) -> {
//            if (o1 != null && o2 != null) {
//                return o1.compareTo(o2);
//            }
//            return 0;
//        });

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
