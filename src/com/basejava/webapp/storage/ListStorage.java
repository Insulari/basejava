package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List based storage for Resumes
 */

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> resumeList = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (searchKey != null);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeList.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeList.set((Integer) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    public void clear() {
        resumeList.clear();
        resumeList.trimToSize();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeList.stream().sorted(Comparator.comparing(Resume::getName))
                         .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
