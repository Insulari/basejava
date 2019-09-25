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
    protected void replaceElement(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void insertElement(Resume r, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Not enough array length " +
                    "for saving resume!", r.getUuid());
        } else {
            addPos(r, index);
            size++;
        }
    }

    @Override
    protected Resume getElement(int index) {
        return storage[index];
    }

    @Override
    protected void removeElement(int index) {
        if (index != (size - 1)) {
            shiftPos(index);
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

    protected abstract void addPos(Resume r, int binarySearchIndex);

    protected abstract void shiftPos(int index);

    protected abstract int getIndex(String uuid);
}