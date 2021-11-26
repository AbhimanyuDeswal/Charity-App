package com.abhimanyu.charity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhimanyu.charity.R;
import com.abhimanyu.charity.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAccountActivity extends AppCompatActivity {

    Context context = UserAccountActivity.this;
    User user;
    EditText nameET, phoneET, emailET,
            houseET, areaET, cityET, stateET, pincodeET;
    String name, phone, email,
            house, area, city, state, pincode;

    ProgressDialog progressDialog;
    String uid;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("My Account");
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        nameET = findViewById(R.id.nameEditText);
        phoneET = findViewById(R.id.phoneEditText);
        emailET = findViewById(R.id.emailEditText);

        houseET = findViewById(R.id.houseInfoET);
        areaET = findViewById(R.id.areaInfoET);
        cityET = findViewById(R.id.cityET);
        stateET = findViewById(R.id.stateET);
        pincodeET = findViewById(R.id.pincodeET);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving...");

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://charity-app-android-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(uid);

        if (uid != null) {
            loadData();
        }

    }

    public void loadData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    nameET.setText(user.getName());
                    phoneET.setText(user.getPhone());
                    emailET.setText(user.getEmail());
                    houseET.setText(user.getHouse());
                    areaET.setText(user.getArea());
                    cityET.setText(user.getCity());
                    stateET.setText(user.getState());
                    pincodeET.setText(user.getPincode());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveClicked(View view) {
        name = nameET.getText().toString();
        phone = phoneET.getText().toString();
        email = emailET.getText().toString();
        house = houseET.getText().toString();
        area = areaET.getText().toString();
        city = cityET.getText().toString();
        state = stateET.getText().toString();
        pincode = pincodeET.getText().toString();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()  ||
                house.isEmpty() || area.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
            Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            user = new User(name, phone, email, house, area, city, state, pincode);
            progressDialog.show();
            databaseReference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
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

    public void cancelClicked(View view) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return true;
    }

}