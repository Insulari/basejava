package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addPos(Resume r, int binarySearchIndex) {
        int index = -binarySearchIndex - 1;      // sorted insertion place
        if (index < size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = r;
    }

    @Override
    protected void shiftPos(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}