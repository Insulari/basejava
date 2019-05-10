package com.basejava.webapp.storage;

import com.basejava.webapp.exception.*;
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

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        try {
            storage.update(new Resume("dummy"));
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(NotExistStorageException.class, e.getClass());
        }
    }

    @Test
    public void save() {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i));
        }
        try {
            storage.save(new Resume("one_more_uuid"));
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(StorageException.class, e.getClass());
        }
        try {
            storage.save(new Resume("uuid1111"));
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(ExistStorageException.class, e.getClass());
        }
    }

    @Test
    public void get_Exist() {
        assertEquals("uuid2", storage.get(UUID_2).toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void get_NotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        try {
            storage.delete("uuid1");
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(NotExistStorageException.class, e.getClass());
        }
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}