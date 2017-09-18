package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.*;

/**
 * Created by SUSTAVOV on 18.09.2017.
 */
public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> mapOfResume = new HashMap<>();

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected void doClear() {
        mapOfResume.clear();
    }

    @Override
    protected boolean isExist(String uuid) {
        return mapOfResume.containsKey(uuid);
    }

    @Override
    protected void doSave(String uuid, Resume resume) {
        mapOfResume.put(uuid, resume);
    }

    @Override
    protected void doUpdate(String uuid, Resume resume) {
        mapOfResume.put(uuid, resume);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return mapOfResume.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        mapOfResume.remove(uuid);
    }

    @Override
    protected List<Resume> doSorted() {
        return new ArrayList<>(mapOfResume.values());
    }

    @Override
    protected int doSize() {
        return mapOfResume.size();
    }
}
