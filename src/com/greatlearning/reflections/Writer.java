package com.greatlearning.reflections;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Writer {

    Reader reader;

    public Writer(Reader reader) {
        this.reader = reader;
    }

    public void storeClass()
            throws IOException {
        String fileName = reader.getClassName();
        String str = getClassDetails();
        File file = new File("output");
        if (!file.exists()) {
            file.mkdir();
        }
        File fileToWrite = new File(file, fileName);
        if (fileToWrite.exists()) {
            System.out.println("File: " + fileName + " already exists");
            return;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite));
        writer.write(str);
        System.out.println("File: " + fileName + " stored in output directory successfully");
        writer.close();
    }

    private String getClassDetails() {
        return reader.getClassName() + "\n" +
                "Constructors: " + Arrays.toString(reader.getConstructors()) + "\n" +
                "Members: " + Arrays.toString(reader.getDataMembers()) + "\n" +
                "Methods: " + Arrays.toString(reader.getMethods()) + "\n" +
                "Enclosing classes: " + Arrays.toString(reader.getSubClasses()) + "\n" +
                "SuperClasses: " + Arrays.toString(reader.getSuperClasses());
    }
}
