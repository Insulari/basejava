package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

/**
 * Map based storage for Resumes.
 * <tt>searchKey</tt> is the instant of String - uuid.
 */

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (map.containsKey(searchKey));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Set<Resume> set = new TreeSet<>(Comparator.comparing(Resume::getName)
                                                  .thenComparing(Resume::getUuid));
        set.addAll(map.values());
        return new ArrayList<>(set);
    }

    @Override
    public int size() {
        return map.size();
    }
}
