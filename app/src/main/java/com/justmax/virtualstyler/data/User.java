package com.justmax.virtualstyler.data;

public class User {
    private static int ID;
    private static String name, email;

    public static void setID(int ID) {
        User.ID = ID;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static int getID() {
        return ID;
    }

    public static String getEmail() {
        return email;
    }

    public static String getName() {
        return name;
    }
}
