package com.example.mia.gestionenig;

/**
 * Created by mia on 27/03/2018.
 */

public class Notes {
    String name;
    int id;

    public Notes(int id, String name) {
        this.id=id;
        this.name = name;
    }

    public Notes() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

