package com.example.orgdon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//This is the Blood Group List page.

public class GroupList extends AppCompatActivity {

    //The button of the page are specified here and the layout of the page is called below.
    Button aPositive, aNegative, bPositive, bNegative, abPositive, abNegative, oPositive, oNegative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        aPositive = findViewById(R.id.aPositive);
        aNegative = findViewById(R.id.aNegative);
        bPositive = findViewById(R.id.bPositive);
        bNegative = findViewById(R.id.bNegative);
        abPositive = findViewById(R.id.abPositive);
        abNegative = findViewById(R.id.abNegative);
        oPositive = findViewById(R.id.oPositive);
        oNegative = findViewById(R.id.oNegative);


        //Each button on being clicked goes to it's respective page.

        aPositive.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, APositive.class);
            startActivity(i);
        });

        aNegative.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, ANegative.class);
            startActivity(i);
        });

        bPositive.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, BPositive.class);
            startActivity(i);
        });

        bNegative.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, BNegative.class);
            startActivity(i);
        });

        abPositive.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, ABPositive.class);
            startActivity(i);
        });

        abNegative.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, ABNegative.class);
            startActivity(i);
        });

        oPositive.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, OPositive.class);
            startActivity(i);
        });

        oNegative.setOnClickListener(view -> {
            Intent i = new Intent(GroupList.this, com.example.orgdon.ONegative.class);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}