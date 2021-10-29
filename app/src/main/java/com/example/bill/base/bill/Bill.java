package com.example.bill.base.bill;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "bill")
public class Bill {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String prodName;
    private int price;

    public Bill(int id, String name, String prodName, int price) {
        this.id = id;
        this.name = name;
        this.prodName = prodName;
        this.price = price;
    }

    @Ignore
    public Bill(String name, String prodName, int price) {
        this.name = name;
        this.prodName = prodName;
        this.price = price;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
