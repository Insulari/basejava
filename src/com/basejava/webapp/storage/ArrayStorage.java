package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addPos(Resume r, int binarySearchIndex) {
        storage[size] = r;
    }

    @Override
    protected void shiftPos(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;                   // resume position
            }
        }
        return -1;                        // then method couldn't find uuid
    }
}