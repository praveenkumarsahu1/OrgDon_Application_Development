package com.example.orgdon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateDonorActivity extends AppCompatActivity {

    private TextInputEditText nameEdit, addressEdit, donatedEdit, ageEdit, bloodEdit, phoneEdit, hospitalEdit;
    private Button usubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogcontent); // <-- IMPORTANT

        nameEdit = findViewById(R.id.nameEdit);
        addressEdit = findViewById(R.id.addressEdit);
        donatedEdit = findViewById(R.id.donatedEdit);
        ageEdit = findViewById(R.id.ageEdit);
        bloodEdit = findViewById(R.id.bloodEdit);
        phoneEdit = findViewById(R.id.phoneEdit);
        hospitalEdit = findViewById(R.id.hospitalEdit);
        usubmit = findViewById(R.id.usubmit);

        // (Optional) Pre-fill from intent
        Intent i = getIntent();
        if (i != null) {
            nameEdit.setText(i.getStringExtra("name"));
            addressEdit.setText(i.getStringExtra("address"));
            donatedEdit.setText(i.getStringExtra("organ"));
            ageEdit.setText(i.getStringExtra("age"));
            bloodEdit.setText(i.getStringExtra("blood"));
            phoneEdit.setText(i.getStringExtra("phone"));
            hospitalEdit.setText(i.getStringExtra("hospital"));
        }

        usubmit.setOnClickListener(v -> {
            // TODO: update Firebase with edited values
        });
    }
}
