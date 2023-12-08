package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomdatabase.databinding.ActivityAddNewContactBinding;
import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.viewmodel.ViewModel;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler handler;
    private Contact contact;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);

        contact = new Contact();

         viewModel = new ViewModelProvider(this).get(ViewModel.class);

         handler= new AddNewContactClickHandler(contact,this,viewModel);

        binding.setContacts(contact);
        binding.setHandler(handler);
    }
}