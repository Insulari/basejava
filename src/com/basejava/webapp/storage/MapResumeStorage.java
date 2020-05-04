package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

/**
 * Map based storage for Resumes.
 * <tt>searchKey</tt> is the instant of Resume.
 */

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (map.containsValue(searchKey));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Resume r = (Resume) searchKey;
        map.remove(r.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
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
