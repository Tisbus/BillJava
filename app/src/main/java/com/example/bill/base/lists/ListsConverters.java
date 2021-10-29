package com.example.bill.base.lists;
import androidx.room.TypeConverter;

import com.example.bill.base.bill.Bill;
import com.example.bill.base.total.Total;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class ListsConverters{
    @TypeConverter
    public static Date fromData(Long value){
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long toData(Date date){
        return date == null ? null : date.getTime();
    }
    @TypeConverter
    public String fromVBillToList(List<Bill> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Bill>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<Bill> toOptionBillList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Bill>>() {
        }.getType();
        return gson.fromJson(value, type);
    }
    @TypeConverter
    public String fromTotalToList(List<Total> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Total>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<Total> toOptionTotalList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Total>>() {
        }.getType();
        return gson.fromJson(value, type);
    }
}
