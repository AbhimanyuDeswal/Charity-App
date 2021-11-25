package com.abhimanyu.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    RecyclerView recyclerView;
    NGOAdapter adapter;
    DatabaseReference mbase;

    List<NGO> NGOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////////////// recycler view
        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        adapter = new NGOAdapter(NGOList);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance("https://charity-app-android-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("NGO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NGOList.clear();

                for(DataSnapshot dataSnapshot1: snapshot.getChildren()) {
                    NGO ngo = dataSnapshot1.getValue(NGO.class);
                    NGOList.add(ngo);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("debug", "not working");
//                Toast.makeText(MainActivity.class, error, Toast.LENGTH_LONG).show();
            }

        });


        ////////////////



        ///////////////// bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.myAccount:
                        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
                        finish();
                }

                return false;
            }
        });
        ////////////////
    }

}