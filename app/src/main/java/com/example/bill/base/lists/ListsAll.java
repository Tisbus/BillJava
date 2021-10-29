package com.example.bill.base.lists;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.bill.base.bill.Bill;
import com.example.bill.base.total.Total;

import java.util.Date;
import java.util.List;

@Entity(tableName = "lists")
@TypeConverters({ListsConverters.class})
public class ListsAll {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String name;
    private List<Bill> bill;
    private List<Total> total;
    private int totalSum;

    public ListsAll(int id, String date, String name, List<Bill> bill, List<Total> total, int totalSum) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.bill = bill;
        this.total = total;
        this.totalSum = totalSum;
    }
    @Ignore
    public ListsAll(String date, String name, List<Bill> bill, List<Total> total, int totalSum) {
        this.date = date;
        this.name = name;
        this.bill = bill;
        this.total = total;
        this.totalSum = totalSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bill> getBill() {
        return bill;
    }

    public void setBill(List<Bill> bill) {
        this.bill = bill;
    }

    public List<Total> getTotal() {
        return total;
    }

    public void setTotal(List<Total> total) {
        this.total = total;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}


