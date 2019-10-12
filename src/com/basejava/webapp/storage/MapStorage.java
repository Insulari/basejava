package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return (map.containsKey((String) searchKey));
    }

    @Override
    protected void removeElement(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        map.replace((String) searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
