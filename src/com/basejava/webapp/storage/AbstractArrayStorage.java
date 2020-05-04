package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Resume> getAllSorted() {
        return Arrays.stream(storage, 0, size)
                     .sorted(Comparator.comparing(Resume::getName)
                                       .thenComparing(Resume::getUuid))
                     .collect(Collectors.toList());
    }

    @Override
    protected void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: Not enough array length " +
                    "for saving resume!", r.getName());
        } else {
            insertElement(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        if ((Integer) index != (size - 1)) {
            fillDeletedElement((Integer) index);
        }
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume r, int binarySearchIndex);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchKey(String uuid);
}