package com.example.sparkv_v1.ADMIN.Clases;

import java.io.Serializable;

public class Cleaner implements Serializable {
    private String id;
    private String email;

    public Cleaner(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
}