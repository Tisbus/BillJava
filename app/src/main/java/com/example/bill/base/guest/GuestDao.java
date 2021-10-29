package com.example.bill.base.guest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GuestDao {
    @Query("SELECT * FROM guests ORDER BY id DESC")
    LiveData<List<Guest>> allGuest();
    @Query("SELECT * FROM guests ORDER BY id DESC")
    List<Guest> allListGuest();
    @Insert
    void insert(Guest guest);
    @Delete
    void delete(Guest guest);
    @Query("DELETE FROM guests")
    void deleteAll();
}
