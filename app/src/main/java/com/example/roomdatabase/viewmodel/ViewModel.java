package com.example.roomdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.repository.ContactRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public LiveData<List<Contact>> allcontact;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.contactRepository = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContact(){
        allcontact = contactRepository.getContact();
        return allcontact;
    }

    public void addnewcontact(Contact contact){contactRepository.addContact(contact);}

    public void delete(Contact contact){contactRepository.deleteContact(contact);}


    public void update(Contact contact){contactRepository.updateContact(contact);}
}