package com.abhimanyu.charity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhimanyu.charity.BuildConfig;
import com.abhimanyu.charity.History;
import com.abhimanyu.charity.R;
import com.abhimanyu.charity.ThankyouActivity;
import com.abhimanyu.charity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

import java.util.HashMap;
import java.util.Map;

public class DonationActivity extends AppCompatActivity {

    Context context = DonationActivity.this;
    TextView nameTV, causeTV, phoneTV, emailTV, locationTV;
    EditText donateET, amountET;
    String name, donate, amount;
    History history;
    DatabaseReference databaseReference;
    String uid;
    ProgressDialog progressDialog;
    Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);


        nameTV = findViewById(R.id.name);
        causeTV = findViewById(R.id.cause);
        phoneTV = findViewById(R.id.phone);
        emailTV = findViewById(R.id.email);
        locationTV = findViewById(R.id.location);

        nameTV.setText(getIntent().getStringExtra("name"));
        causeTV.setText(getIntent().getStringExtra("cause"));
        phoneTV.setText(getIntent().getStringExtra("phone"));
        emailTV.setText(getIntent().getStringExtra("email"));
        locationTV.setText(getIntent().getStringExtra("location"));

        name = getIntent().getStringExtra("name");

        donateET = findViewById(R.id.donate);
        amountET = findViewById(R.id.amount);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Donating...");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance(BuildConfig.database_url).getReference().child("History").child(uid).push();


    }


    public void proceedClicked(android.view.View view) {
        donate = donateET.getText().toString();
        amount = amountET.getText().toString();
        Map map = new HashMap();
        map.put("timestamp", ServerValue.TIMESTAMP);

        if (donate.isEmpty() || amount.isEmpty()) {
            Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            history = new History(name, donate, amount, map);
            progressDialog.show();
            databaseReference.setValue(history).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "Donated", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, ThankyouActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void cancelClicked(android.view.View view) {
        finish();
    }
}