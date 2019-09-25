package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException {
        Resume r = new Resume();
        Class<? extends Resume> rClass = r.getClass();
        Field field = rClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");

        // HW4
        Method method = rClass.getMethod("toString");
        System.out.println("Reflection r.toString: " + method.invoke(r));
    }
}