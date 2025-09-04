package com.example.orgdon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/*
This is the 'Organ Donors' page of our program.
*/

public class DonorList extends AppCompatActivity {

    RecyclerView recView;
    MyAdapter adapter;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //On creation of the page, the program calls the activity_donor_list.xml file
        //in the layout folder which is the layout of this page.
        setContentView(R.layout.activity_donor_list);
        setTitle("Search Donors");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        //This code allows the return arrow in the page, but it is not complete without
        //some action.
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //We use this part of the code to generate the Card view of the donor list.
        recView = findViewById(R.id.recview);
        recView.setLayoutManager(new LinearLayoutManager(this));

        // We fetch all Donors from Firebase
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donors"), model.class)
                        .build();

        adapter = new MyAdapter(options);
        recView.setAdapter(adapter);

        //This part allows the '+' button at the bottom-right of the screen.
        //This allows us to add an entry to our database.
        fb = findViewById(R.id.fadd);
        fb.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddDonor.class)));
    }

    // This is where we specify where will the program go if we press back.
    // As you can see it goes back to the MainActivity page.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // This is the second part of the return arrow on the top left corner of the
    // screen. This part of the code allows us to go back to the last page, which we
    // have to specify(MainActivity)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // If we have added some entry to our database, this notifies us that, either
    // entering the data was successfull or not.
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        recView.getRecycledViewPool().clear();
        adapter.startListening();
        recView.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    // This part of the code helps us use the search button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // We take the string from the search bar and compare it our database entries
            // based on some specific criteria.
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s.length() == 0 ? s : s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s.length() == 0 ? s : s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        // This is where we specify the criteria to filter our database and display
        // accordingly. In this case, we filter our search for donor according to
        // their names.
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donors").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), model.class)
                        .build();

        adapter = new MyAdapter(options);
        adapter.startListening();
        recView.setAdapter(adapter);
    }


}