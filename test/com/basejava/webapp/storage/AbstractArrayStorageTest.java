package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";
    private static final String ONE_MORE_UUID = "oneMoreUuid";
    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);
    private static final Resume RESUME_DUMMY = new Resume(DUMMY);
    private static final Resume ONE_MORE_RESUME = new Resume(ONE_MORE_UUID);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void updateExist() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));     // resume updated
        assertNotSame(RESUME1, storage.get(UUID_1));    // it`s really new resume
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() {
        assertEquals(3, storage.size());
        storage.save(ONE_MORE_RESUME);
        assertEquals(4, storage.size());
        assertSame(ONE_MORE_RESUME, storage.get(ONE_MORE_UUID));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            fail("Exception thrown in saving resume: " + e.getUuid());
        }
        storage.save(ONE_MORE_RESUME);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test
    public void getExist() {
        assertSame(RESUME2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExist() {
        try {
            storage.delete(UUID_1);
        } catch (NotExistStorageException e) {
            fail("Exception thrown to fast! Uuid " + e.getUuid() + " has to be exist!");
        }
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = RESUME1;
        resumes[1] = RESUME2;
        resumes[2] = RESUME3;
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}