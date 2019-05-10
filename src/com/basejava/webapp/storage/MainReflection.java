package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException,
                                                  ClassNotFoundException,
                                                  NoSuchMethodException,
                                                  InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");

        // HW4 toDo
        Method method = Class.forName("com.basejava.webapp.model.Resume").getMethod("toString");
        System.out.println("Reflection r.toString: " + method.invoke(r));
    }
}