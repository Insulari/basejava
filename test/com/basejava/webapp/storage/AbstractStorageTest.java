package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String NAME_1 = "Petr1";
    private static final String NAME_2 = "Petr2";
    private static final String NAME_3 = "Petr3";
    private static final String DUMMY = "dummy";
    private static final String ONE_MORE_NAME = "Dummyr";
    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume RESUME_DUMMY;
    protected static final Resume ONE_MORE_RESUME;

    static {
        R1 = ResumeTestData.setAllFields(UUID_1, NAME_1);
        R2 = ResumeTestData.setAllFields(UUID_2, NAME_2);
        R3 = ResumeTestData.setAllFields(UUID_3, NAME_3);
        RESUME_DUMMY = ResumeTestData.setAllFields(DUMMY, NAME_1);
        ONE_MORE_RESUME = ResumeTestData.setAllFields(new Resume(ONE_MORE_NAME).getUuid(), ONE_MORE_NAME);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        assertSize(3);
        storage.clear();
        assertSize(0);
    }

    @Test
    public void updateExist() {
        Resume newResume = new Resume(UUID_1, "New Name");
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
        storage.save(R3);
    }

    @Test
    public void getExist() {
        assertGet(R3);
        assertGet(R2);
        assertGet(R1);
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
    public void getAllSorted() {
        Resume[] resumes = new Resume[3];
        resumes[0] = R1;
        resumes[1] = R2;
        resumes[2] = R3;
        Set<Resume> set = new TreeSet<>(storage.getAllSorted());
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