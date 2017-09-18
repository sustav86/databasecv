package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by SUSTAVOV on 18.09.2017.
 */
public abstract class AbstractStorage implements IStorage {
    protected static Logger logger = Logger.getLogger(ArrayStorage.class.getName());

    @Override
    public void clear() {
        logger.info("Delete all resume.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean isExist(String uuid);

    @Override
    public void save(Resume resume) {
        logger.info("Save resume uuid = " + resume.getUuid());
        if (isExist(resume.getUuid())) {
            throw new DataBaseCVException("Resume has already present uuid = " + resume.getUuid(), resume);
        }
        doSave(resume);
    }

    protected abstract void doSave(Resume resume);

    @Override
    public void update(Resume resume) {
        logger.info("Update resume with uuid = " + resume.getUuid());
        if (!isExist(resume.getUuid())) {
            throw new DataBaseCVException("Resume with uuid = " + resume.getUuid() + " is't exist", resume);
        }
        doUpdate(resume);
    }

    protected abstract void doUpdate(Resume resume);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid = " + uuid);
        if (!isExist(uuid)) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid = " + uuid);
        if (!isExist(uuid)) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        doDelete(uuid);
    }

    protected abstract Resume doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Sorted array of resumes.");
        return doSorted();
    }

    protected abstract Collection<Resume> doSorted();

    @Override
    public int size() {
        logger.info("Get size of resumes.");
        return doSize();
    }

    protected abstract int doSize();




}
