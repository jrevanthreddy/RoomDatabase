package com.example.roomdatabase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContact();
}
