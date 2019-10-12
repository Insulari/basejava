package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";
    private static final String ONE_MORE_UUID = "oneMoreUuid";
    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;
    private static final Resume RESUME_DUMMY;
    protected static final Resume ONE_MORE_RESUME;

    static {
        RESUME1 = new Resume(UUID_1);
        RESUME2 = new Resume(UUID_2);
        RESUME3 = new Resume(UUID_3);
        RESUME_DUMMY = new Resume(DUMMY);
        ONE_MORE_RESUME = new Resume(ONE_MORE_UUID);
    }

    protected AbstractStorageTest(Storage storage) {
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
        assertSize(3);
        storage.clear();
        assertSize(0);
    }

    @Test
    public void updateExist() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertGet(newResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() {
        assertSize(3);
        storage.save(ONE_MORE_RESUME);
        assertSize(4);
        assertGet(ONE_MORE_RESUME);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test
    public void getExist() {
        assertGet(RESUME3);
        assertGet(RESUME2);
        assertGet(RESUME1);
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
        Set<Resume> set = new TreeSet<>(Arrays.asList(storage.getAll()));
        assertArrayEquals(resumes, set.toArray(new Resume[0]));
        assertSize(3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertSame(resume, storage.get(resume.getUuid()));
    }
}