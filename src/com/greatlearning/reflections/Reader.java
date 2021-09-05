package com.greatlearning.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private final Class clas;


    public Reader(Class clas) {
        this.clas = clas;
    }

    public Method[] getMethods() {
        return this.clas.getDeclaredMethods();
    }

    public String getClassName() {
        return this.clas.getName();
    }

    public Object[] getSubClasses() {
        return this.clas.getDeclaredClasses();
    }

    public Object[] getSuperClasses() {
        Class C = this.clas;
        List<String> superClasses = new ArrayList<>();
        while (C != null) {
            superClasses.add(C.getName());
            C = C.getSuperclass();
        }
        return superClasses.toArray();
    }

    public Constructor[] getConstructors() {
        return this.clas.getConstructors();
    }

    public Field[] getDataMembers() {
        return this.clas.getDeclaredFields();
    }
}
