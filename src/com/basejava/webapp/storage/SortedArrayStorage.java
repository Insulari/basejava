package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int binarySearchIndex) {
        int index = -binarySearchIndex - 1;      // sorted insertion place
        if (index < size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "someName");
        return Arrays.binarySearch(storage, 0, size, searchKey,
                                   Comparator.comparing(Resume::getUuid));
    }
}