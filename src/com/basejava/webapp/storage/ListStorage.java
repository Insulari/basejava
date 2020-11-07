package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * List based storage for Resumes
 */

public class ListStorage extends AbstractStorage<Integer> {
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
    protected boolean isExist(Integer searchKey) {
        return (searchKey != null);
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        resumeList.add(r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        resumeList.remove(searchKey.intValue());
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        resumeList.set(searchKey, r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(resumeList);
    }

    @Override
    public void clear() {
        resumeList.clear();
        resumeList.trimToSize();
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
