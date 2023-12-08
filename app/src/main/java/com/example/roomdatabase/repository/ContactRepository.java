package com.example.roomdatabase.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.roomdatabase.dao.ContactDao;
import com.example.roomdatabase.database.ContactDatabase;
import com.example.roomdatabase.model.Contact;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {

    private final ContactDao contactDao;

    private final ExecutorService executorService;

    private final Handler handler;

    public ContactRepository(Application application){
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDao = contactDatabase.getdao();

        executorService = Executors.newSingleThreadExecutor();

        handler = new Handler(Looper.getMainLooper());
    }
    public void addContact(Contact contact){
        executorService.execute(() -> {
            contactDao.insert(contact);
        });
    }
    public void deleteContact(Contact contact) {
        executorService.execute(() -> {
            contactDao.delete(contact);
        });
    }

    public void updateContact(Contact contact) {
        executorService.execute(() -> {
            contactDao.updateContact(contact);
        });
    }

    public LiveData<List<Contact>> getContact(){
        return contactDao.getAllContact();
    }
}