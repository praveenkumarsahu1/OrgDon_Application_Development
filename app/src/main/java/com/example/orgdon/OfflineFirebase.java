package com.example.orgdon;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

//This class is to initialize our firebase database
public class OfflineFirebase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Extra COde
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
