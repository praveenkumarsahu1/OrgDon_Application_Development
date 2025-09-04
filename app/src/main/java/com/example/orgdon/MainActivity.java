package com.example.orgdon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * MainActivity - Front page of the app
 * - Handles navigation to Donors, Organs, Blood Groups, and Hospitals.
 * - Checks Firebase Authentication.
 * - Confirms Firebase Database connectivity.
 */
public class MainActivity extends AppCompatActivity {

    private Button donors, organs, bloodgroup, hospitals;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Hide ActionBar (avoid crash if null)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // ✅ Firebase Authentication
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            // If user not logged in → redirect to LoginActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        // ✅ Firebase Realtime Database connectivity check
        FirebaseDatabase.getInstance()
                .getReference("testNode")
                .setValue("Hello, Firebase!")
                .addOnSuccessListener(unused ->
                        Toast.makeText(MainActivity.this, "Firebase Connected ✅", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this, "Firebase Connection Failed ❌", Toast.LENGTH_SHORT).show());

        // ✅ Initialize UI buttons
        donors = findViewById(R.id.recyclerviewbtn);
        bloodgroup = findViewById(R.id.frequency);
        organs = findViewById(R.id.groupList);
        hospitals = findViewById(R.id.place);

        // ✅ Button navigation
        donors.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DonorList.class)));
        organs.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Organs.class)));
        bloodgroup.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GroupList.class)));
        hospitals.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HospitalList.class)));
    }

    /**
     * Logout current user and return to LoginActivity
     */
    private void logout() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
