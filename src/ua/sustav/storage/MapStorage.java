package ua.sustav.storage;

import ua.sustav.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SUSTAVOV on 18.09.2017.
 */
public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapOfResume = new HashMap<>();

    @Override
    protected void doClear() {
        mapOfResume.clear();
    }

    @Override
    protected boolean isExist(String uuid) {
        return mapOfResume.containsKey(uuid);
    }

    @Override
    protected void doSave(Resume resume) {
        mapOfResume.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume) {
        mapOfResume.put(resume.getUuid(), resume);
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
    protected Collection<Resume> doSorted() {
        return null;
    }

    @Override
    protected int doSize() {
        return mapOfResume.size();
    }
}
