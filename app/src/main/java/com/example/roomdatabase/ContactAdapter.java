package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.databinding.ContactListItemBinding;
import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.view.MainActivity;
import com.example.roomdatabase.viewmodel.ViewModel;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<Contact> contactArrayList;
    Context context;

    FragmentManager fragmentManager;


    public ContactAdapter(ArrayList<Contact> contacts,Context context,FragmentManager fragmentManager) {
        this.contactArrayList= contacts;
        this.context=context;
        this.fragmentManager=fragmentManager;

    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(LayoutInflater.
                from(parent.getContext()), R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {

        Contact contact = contactArrayList.get(position);
        holder.contactListItemBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        if (contactArrayList != null){
       return contactArrayList.size();
        }else {
            return 0;
        }
    }

    public void setContact(ArrayList<Contact> contact) {
        this.contactArrayList = contact;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;

             contactListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int position = getAdapterPosition();
                     if (position != RecyclerView.NO_POSITION) {
                         Contact c = contactArrayList.get(position);
                         contactUpdate(c);}
                 }
             });
        }
        private void contactUpdate(Contact contact){
            if (contact != null){
                UpdateFragment updateFragment = new UpdateFragment();
                Bundle bundle = new Bundle();
                bundle.putString("CONTACT_NAME",contact.getName());
                bundle.putString("CONTACT_PHONENUM",contact.getPhonenum());
                bundle.putInt("CONTACT_ID",contact.getId());
                updateFragment.setArguments(bundle);

                updateFragment.show(fragmentManager, "updateFragment");
            }else {
                Toast.makeText(context, "Contact information is missing", Toast.LENGTH_SHORT).show();
            }



//            Intent intent = new Intent(context,UpdateActivity.class);
//            intent.putExtra("CONTACT_ID",contact.getId());
//            intent.putExtra("CONTACT_NAME",contact.getName());
//            intent.putExtra("CONTACT_PHONENUM",contact.getPhonenum());
//            context.startActivity(intent);
        }
    }
}