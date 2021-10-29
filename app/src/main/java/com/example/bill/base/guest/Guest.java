package com.example.bill.base.guest;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "guests")
public class Guest {
    private int id;
    private String name;

    public Guest(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Ignore
    public Guest(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
