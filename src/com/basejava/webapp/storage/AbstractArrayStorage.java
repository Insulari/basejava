package com.basejava.webapp.storage;

import com.basejava.webapp.exception.*;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        int index = (Integer) searchKey;
        storage[index] = r;
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Not enough array length " +
                    "for saving resume!", r.getUuid());
        } else {
            int index = (Integer) searchKey;
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected Resume getElement(Object searchKey) {
        int index = (Integer) searchKey;
        return storage[index];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((Integer) searchKey >= 0);
    }

    @Override
    protected void removeElement(Object searchKey) {
        int index = (Integer) searchKey;
        if (index != (size - 1)) {
            fillDeletedElement(index);
        }
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume r, int binarySearchIndex);

    protected abstract void fillDeletedElement(int index);

    protected abstract Object getSearchKey(String uuid);
}