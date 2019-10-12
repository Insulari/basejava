package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        updateElement(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else saveElement(r, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getElement(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        removeElement(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void removeElement(Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void updateElement(Resume r, Object searchKey);

    protected abstract void saveElement(Resume r, Object searchKey);

    protected abstract Object getSearchKey(String uuid);
}
