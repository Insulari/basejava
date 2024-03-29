package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO Error. Unpossible to write in file: "
                    + file.getAbsolutePath(), file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO Error. Unpossible to read file: "
                    + file.getAbsolutePath(), file.getName(), e);
        }
    }

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                list.add(doGet(f));
            }
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.delete()) {
                    throw new StorageException("IO Error. Unpossible to delete file: "
                            + f.getAbsolutePath(), f.getName());
                }
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            return 0;
        } else {
            return list.length;
        }
    }
}
