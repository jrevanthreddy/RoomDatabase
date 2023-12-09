package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.databinding.ActivityUpdateBinding;
import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.view.MainActivity;
import com.example.roomdatabase.viewmodel.ViewModel;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateBinding binding;
    private ViewModel viewModel;
    private String name,phonenum,email,contactId;
    private Contact contact;
    private Context context;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        contact = new Contact();

        if (getIntent().getExtras() != null){
            name = getIntent().getStringExtra("CONTACT_NAME");
            phonenum = getIntent().getStringExtra("CONTACT_PHONENUM");
            email = getIntent().getStringExtra("CONTACT_EMAIL");
            contactId = String.valueOf(getIntent().getIntExtra("CONTACT_ID", -1));
            contact.setId(Integer.parseInt(contactId));
            contact.setName(name);
            contact.setPhonenum(phonenum);
            contact.setEmail(email);
            binding.setContacts(contact);
        }
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getName().isEmpty() || contact.getPhonenum().isEmpty() || contact.getEmail().isEmpty()) {
                    Toast.makeText(context, "Please fill the Above fields ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    if (viewModel != null) {
                        viewModel.update(contact);
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Data is not Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}