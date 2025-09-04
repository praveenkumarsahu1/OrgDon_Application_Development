package com.example.orgdon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Organs extends AppCompatActivity {

    Button heart, liver, lungs, kidneys, pancreas, smallBowel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organs);

        heart = findViewById(R.id.heart);
        liver = findViewById(R.id.liver);
        lungs = findViewById(R.id.lungs);
        kidneys = findViewById(R.id.kidneys);
        pancreas = findViewById(R.id.pancreas);
        smallBowel = findViewById(R.id.smallbowel);

        heart.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, Heart.class);
            startActivity(i);
        });

        liver.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, Liver.class);
            startActivity(i);
        });

        lungs.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, Lungs.class);
            startActivity(i);
        });

        kidneys.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, Kidneys.class);
            startActivity(i);
        });

        pancreas.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, Pancreas.class);
            startActivity(i);
        });

        smallBowel.setOnClickListener(view -> {
            Intent i = new Intent(Organs.this, SmallBowel.class);
            startActivity(i);
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}