package com.example.roomdatabase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.roomdatabase.ContactAdapter;
import com.example.roomdatabase.MainActivityClickHandler;

import com.example.roomdatabase.R;
import com.example.roomdatabase.database.ContactDatabase;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.model.Contact;
import com.example.roomdatabase.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private ContactDatabase contactDatabase;
    private ContactAdapter adapter;
    private MainActivityClickHandler mainActivityClickHandler;
    ViewModel viewModel;
    private ArrayList<Contact> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityClickHandler = new MainActivityClickHandler(this);

        mainBinding.setAdd(mainActivityClickHandler);
        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        contactDatabase = ContactDatabase.getInstance(this);
        adapter= new ContactAdapter(arrayList,this,getSupportFragmentManager());
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        viewModel.getAllContact().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                arrayList.clear();
                for (Contact c : contacts) {
                    arrayList.add(c);
                }
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact deletecontact = arrayList.get(viewHolder.getAdapterPosition());

                viewModel.delete(deletecontact);

                Snackbar snackbar = Snackbar.make(mainBinding.getRoot(), "Item Deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.addnewcontact(deletecontact);
                    }
                });
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);
    }
}