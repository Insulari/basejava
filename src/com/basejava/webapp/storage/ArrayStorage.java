package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    private int check(Resume r) {
        String uuid = r.getUuid();
        return check(uuid);
    }

    private int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;                   // resume position
            }
        }
        return size;                        // then method couldn't find uuid
    }

    public void update(Resume r) {
        if (check(r) == size) {
            System.out.println("ERROR: Update fail! Couldn't find resume \""
                    + r.getUuid() + "\"!");
        } else {
            storage[check(r)] = r;
        }
    }

    public void save(Resume r) {
        if (check(r) != size) {
            System.out.println("WARNING: Saving resume already exists!");
        } else if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: Not enough array length " +
                    "for saving resume!");
        }
    }

    public Resume get(String uuid) {
        if (check(uuid) == size) {
            System.out.println("ERROR: Unknown uuid!");
        }
        return storage[check(uuid)];
    }

    public void delete(String uuid) {
        int i = check(uuid);
        if (i == size) {
            System.out.println("ERROR: Couldn't find resume \"" + uuid + "\"!");
        } else if (i != (size - 1)) {
            storage[i] = storage[size - 1];
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
}