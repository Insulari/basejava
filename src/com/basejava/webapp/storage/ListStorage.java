package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
        resumeList.trimToSize();
    }

    @Override
    protected void replaceElement(Resume r, int index) {
        resumeList.set(index, r);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        resumeList.add(r);
    }

    @Override
    protected Resume getElement(int index) {
        return resumeList.get(index);
    }

    @Override
    protected void removeElement(int index) {
        resumeList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        return resumeList.toArray(resumes);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Iterator<Resume> iterator = resumeList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getUuid().equals(uuid)) {
                return i;     // how to get iterator index?
            } else {
                i++;
            }
        }
        return -1;
    }
}
