package com.example.bill.base.bill;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BillDao {
    //bill command

    @Query("SELECT * FROM bill ORDER BY id DESC")
    LiveData<List<Bill>> allBill();
    @Query("SELECT * FROM bill ORDER BY name DESC")
    List<Bill> allListBill();
    @Query("SELECT * from bill where id ==:id")
    Bill insertId(long id);
    @Insert
    void insert(Bill bill);
    @Delete
    void delete(Bill bill);
    @Query("DELETE FROM bill")
    void deleteAll();

}
