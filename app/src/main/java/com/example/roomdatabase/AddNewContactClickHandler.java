package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.view.MainActivity;
import com.example.roomdatabase.viewmodel.ViewModel;

public class AddNewContactClickHandler {
    Contact contact;
    Context context;
    ViewModel viewModel;

    public AddNewContactClickHandler(Contact contact, Context context, ViewModel viewModel) {
        this.contact = contact;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitButtonClicked(View view){

        if (contact.getName() == null || contact.getPhonenum() == null){
            Toast.makeText(context, "fields can not be empty", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, MainActivity.class);

            Contact c = new Contact(contact.getName(),contact.getPhonenum());

            viewModel.addnewcontact(c);
            context.startActivity(intent);
        }
    }
}