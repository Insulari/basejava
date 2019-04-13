package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int ch = getIndex(r.getUuid());
        if (ch < 0) {
            System.out.println("ERROR: Update fail! " +
                               "Couldn't find resume \"" + r + "\"!");
        } else {
            storage[ch] = r;
        }
    }

    public void save(Resume r) {
        int i = getIndex(r.getUuid());
        if (i >= 0) {
            System.out.println("WARNING: Resume \"" + r + "\" already exists!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Not enough array length " +
                               "for saving resume!");
        } else {
            add(r, i);
            size++;
        }
    }

    protected abstract void add(Resume r, int binarySearch);

    public Resume get(String uuid) {
        int ch = getIndex(uuid);
        if (ch < 0) {
            System.out.println("ERROR: Unknown uuid!");
            return null;
        }
        return storage[ch];
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i < 0) {
            System.out.println("ERROR: Couldn't find resume \"" + uuid + "\"!");
        } else {
            shiftPos(i);
        }
        storage[size - 1] = null;
        size--;
    }

    protected abstract void shiftPos(int index);

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

    protected abstract int getIndex(String uuid);
}