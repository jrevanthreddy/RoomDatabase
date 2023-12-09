package com.example.roomdatabase.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact {

    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Contact_name")
    private String name;

    @ColumnInfo(name = "contact_Phonenum")
    private String phonenum;

    @ColumnInfo(name = "email")
    private String email;

    public Contact() {
    }

    public Contact(String name, String phonenum,String email) {
        this.id = id;
        this.name = name;
        this.phonenum = phonenum;
        this.email=email;
    }

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}
    public String getPhonenum() {
        return phonenum;
    }
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}