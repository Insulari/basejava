package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;

    static {
        RESUME1 = new Resume(UUID_1);
        RESUME2 = new Resume(UUID_2);
        RESUME3 = new Resume(UUID_3);
    }

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME3);
        collection.add(RESUME2);
        collection.add(RESUME1);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME1);
        map.put(UUID_2, RESUME2);
        map.put(UUID_3, RESUME3);

        for (Map.Entry<String, Resume> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println(map.toString());
    }
}
