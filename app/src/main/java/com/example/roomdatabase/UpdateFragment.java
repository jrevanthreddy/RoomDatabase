package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomdatabase.databinding.FragmentUpdateBinding;
import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.view.MainActivity;
import com.example.roomdatabase.viewmodel.ViewModel;

public class UpdateFragment extends DialogFragment {

    private FragmentUpdateBinding binding;
    private ViewModel viewModel;
    private String name, phonenum, contactId;
    private Contact contact;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        contact = new Contact();

        if (getArguments() != null) {
            name = getArguments().getString("CONTACT_NAME");
            phonenum = getArguments().getString("CONTACT_PHONENUM");
            contactId = String.valueOf(getArguments().getInt("CONTACT_ID", -1));
            contact.setId(Integer.parseInt(contactId));
            contact.setName(name);
            contact.setPhonenum(phonenum);
            binding.setContacts(contact);
        }

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getName().isEmpty() || contact.getPhonenum().isEmpty()) {
                    Toast.makeText(context, "Please fill the Above fields ", Toast.LENGTH_SHORT).show();
                } else {
                    if (viewModel != null) {
                        viewModel.update(contact);
                        Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "Data is not Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return binding.getRoot();
    }
}

