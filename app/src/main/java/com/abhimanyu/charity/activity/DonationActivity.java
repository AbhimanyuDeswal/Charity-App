package com.abhimanyu.charity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.abhimanyu.charity.R;

public class DonationActivity extends AppCompatActivity {

    TextView name, cause, phone, email, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);


        name = findViewById(R.id.name);
        cause = findViewById(R.id.cause);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);

        name.setText(getIntent().getStringExtra("name"));
        cause.setText(getIntent().getStringExtra("cause"));
        phone.setText(getIntent().getStringExtra("phone"));
        email.setText(getIntent().getStringExtra("email"));
        location.setText(getIntent().getStringExtra("location"));
    }
}