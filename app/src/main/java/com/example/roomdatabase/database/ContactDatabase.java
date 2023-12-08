package com.example.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.dao.ContactDao;
import com.example.roomdatabase.model.Contact;

@Database( entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getdao();

    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){

            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class,"contact_db").build();
        }
        return dbInstance;
    }
}