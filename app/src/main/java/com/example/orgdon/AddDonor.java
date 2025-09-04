package com.example.orgdon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

// This is the Add Donor page which we get on pressing the '+' button on the
// Organ Donor List Page.
public class AddDonor extends AppCompatActivity {

    // Here, we specify the  different Text fields and button we are using in the page.
    EditText name, address, donated, age, blood, phone, hospital;
    Button submit, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        name = findViewById(R.id.addName);
        address = findViewById(R.id.addAddress);
        donated = findViewById(R.id.addDonated);
        age = findViewById(R.id.addAge);
        blood = findViewById(R.id.addBlood);
        phone = findViewById(R.id.addPhone);
        hospital = findViewById(R.id.addHospital);

        back = findViewById(R.id.add_back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DonorList.class);
            startActivity(intent);
        });

        submit = findViewById(R.id.add_submit);
        submit.setOnClickListener(view -> processInsert());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DonorList.class);
        startActivity(intent);
    }

    // This part of the code gets the text from the Text fields and then
    // inserts those values into a Firebase database.
    private void processInsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("address", address.getText().toString());
        map.put("donated", donated.getText().toString());
        map.put("age", age.getText().toString());
        map.put("blood", blood.getText().toString());
        map.put("phone", phone.getText().toString());
        map.put("hospital", hospital.getText().toString());

        // Add Donors to Firebase Database
        FirebaseDatabase.getInstance().getReference().child("donors").push()
                .setValue(map)
                .addOnSuccessListener(aVoid -> {
                    name.setText("");
                    address.setText("");
                    donated.setText("");
                    age.setText("");
                    blood.setText("");
                    phone.setText("");
                    hospital.setText("");
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), DonorList.class));
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Could not Added", Toast.LENGTH_LONG).show());

    }
}