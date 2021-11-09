package com.example.bill.base.total;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "total")
public class Total {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int sum;

    public Total(int id, String name, int sum) {
        this.id = id;
        this.name = name;
        this.sum = sum;
    }

    @Ignore
    public Total(String name, int sum) {
        this.name = name;
        this.sum = sum;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
