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
    protected void updateElement(Resume r, Object searchKey) {
        int index = (Integer) searchKey;
        resumeList.set(index, r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        resumeList.add(r);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        int index = (Integer) searchKey;
        return resumeList.get(index);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((Integer)searchKey >= 0);
    }

    @Override
    protected void removeElement(Object searchKey) {
        int index = (Integer) searchKey;
        resumeList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Iterator<Resume> iterator = resumeList.iterator();
        Integer i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getUuid().equals(uuid)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }
}
