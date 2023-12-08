package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {
    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }
    public void onFABClicked(View view){
        Intent intent = new Intent(context, AddNewContactActivity.class);
        context.startActivity(intent);
    }
}