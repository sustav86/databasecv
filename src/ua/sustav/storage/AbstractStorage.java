package ua.sustav.storage;

import ua.sustav.DataBaseCVException;
import ua.sustav.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by SUSTAVOV on 18.09.2017.
 */
public abstract class AbstractStorage<E> implements IStorage {
    protected static Logger logger = Logger.getLogger(ArrayStorage.class.getName());

    protected abstract E getContext(String uuid);

    @Override
    public void clear() {
        logger.info("Delete all resume.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean isExist(E ctx);

    @Override
    public void save(Resume resume) {
        logger.info("Save resume uuid = " + resume.getUuid());
        E ctx = getContext(resume.getUuid());
        if (isExist(ctx)) {
            throw new DataBaseCVException("Resume has already present uuid = " + resume.getUuid(), resume);
        }
        doSave(ctx, resume);
    }

    protected abstract void doSave(E ctx, Resume resume);

    @Override
    public void update(Resume resume) {
        logger.info("Update resume with uuid = " + resume.getUuid());
        E ctx = getContext(resume.getUuid());
        if (!isExist(ctx)) {
            throw new DataBaseCVException("Resume with uuid = " + resume.getUuid() + " is't exist", resume);
        }
        doUpdate(ctx, resume);
    }

    protected abstract void doUpdate(E ctx, Resume resume);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid = " + uuid);
        E ctx = getContext(uuid);
        if (!isExist(ctx)) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        return doLoad(ctx);
    }

    protected abstract Resume doLoad(E ctx);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid = " + uuid);
        E ctx = getContext(uuid);
        if (!isExist(ctx)) {
            throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
        }
        doDelete(ctx);
    }

    protected abstract void doDelete(E ctx);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Sorted array of resumes.");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    @Override
    public int size() {
        logger.info("Get size of resumes.");
        return doSize();
    }

    protected abstract int doSize();


}
