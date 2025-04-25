package com.njgag.nikhil;

public class User {
    private String name;

    public User() {
        // Firestore এর জন্য দরকার
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}