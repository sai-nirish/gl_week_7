package com.greatlearning.reflections;

import javax.lang.model.SourceVersion;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {

    public static void showMenu() {
        System.out.println("\nEnter the operation that you want to peform");
        System.out.println("1. Methods");
        System.out.println("2. Class");
        System.out.println("3. Subclasses");
        System.out.println("4. Parent classes");
        System.out.println("5. Constructors");
        System.out.println("6. Data members");
    }

    public static void showExitMenu() {
        System.out.println("1. Store information into file");
        System.out.println("2. See all previous files created");
        System.out.println("3. Exit without storing");
    }

    private static void printArray(Object array) {
        for (int i = 0; i < Array.getLength(array); i++) {
            System.out.println(Array.get(array, i));
        }
    }

    static boolean isValidName(String className) {
        if (SourceVersion.isName(className)) {
            try {
                Class.forName(className);
                return true;
            } catch (ClassNotFoundException ex) {
                return false;
            }
        }
        return false;
    }


    public static void listAllFiles() {
        //Creating a File object for directory
        File directoryPath = new File("output");
        //List of all files and directories
        String contents[] = directoryPath.list();
        System.out.println("List of stored files:");
        for (int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        int selected = -1;
        boolean exit = false;
        String className;
        Class inputClass = null;
        Reader reader = null;
        while (!exit) {
            System.out.println("Please enter the class name");
            try {
                className = in.nextLine();
                if (!isValidName(className)) {
                    System.out.println("Enter valid class name");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Enter valid class name");
                return;
            }
            inputClass = Class.forName(className);
            showMenu();
            try {
                selected = in.nextInt();
                in.nextLine();
                if (selected < 1 || selected > 6) {
                    System.out.println("Please enter a number in range 1-6");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid number");
                return;
            }
            reader = new Reader(inputClass);
            switch (selected) {
                case 1:
                    printArray(reader.getMethods());
                    break;
                case 2:
                    System.out.println(reader.getClassName());
                    break;
                case 3:
                    printArray(reader.getSubClasses());
                    break;
                case 4:
                    printArray(reader.getSuperClasses());
                    break;
                case 5:
                    printArray(reader.getConstructors());
                    break;
                case 6:
                    printArray(reader.getDataMembers());
                    break;
            }

            System.out.println("\nDo you want to see any more information ?");
            System.out.println("Press yes to recheck the menu and no to continue");
            String input;
            try {
                input = in.nextLine();
                if (!input.equals("yes") && !input.equals("no")) {
                    System.out.println("Please enter either yes or no");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Enter valid string");
                return;
            }
            if (input.equals("no")) {
                exit = true;
            }
        }
        showExitMenu();
        try {
            selected = in.nextInt();
            if (selected < 1 || selected > 3) {
                System.out.println("Please enter a number in range 1-3");
                return;
            }
        } catch (Exception e) {
            System.out.println("Enter a valid number");
            return;
        }
        Writer writer = new Writer(reader);
        switch (selected) {
            case 1:
                try {
                    writer.storeClass();
                } catch (IOException e) {
                    System.out.println("unable to store class information into file");
                }
                break;
            case 2:
                listAllFiles();
                break;
            case 3:
                break;
        }
        in.close();
    }
}
