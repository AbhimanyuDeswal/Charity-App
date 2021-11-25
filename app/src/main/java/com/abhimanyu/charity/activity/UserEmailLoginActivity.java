package com.abhimanyu.charity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhimanyu.charity.MainActivity;
import com.abhimanyu.charity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserEmailLoginActivity extends AppCompatActivity {

    EditText emailET, passwordET;
    String email, password, login;
    TextView loginTV;
    Context context = UserEmailLoginActivity.this;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_email_login);

        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);

        login = getIntent().getStringExtra("login");
        loginTV = findViewById(R.id.textView);
        loginTV.setText(login);

        progressDialog = new ProgressDialog(context);

        mAuth = FirebaseAuth.getInstance();


    }

    public void loginClicked(View view) {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        if (email.isEmpty()) {
            emailET.setError("Enter your Email");
        }
        if (password.isEmpty()) {
            passwordET.setError("Enter your Password");
        }
        else {
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            login(email, password);
        }

    }

    private void login( String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(context,"Authentication Failed",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        progressDialog.dismiss();
        if (user != null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void forgotPassword(View view) {
        progressDialog.setMessage("Sending you email");
        String email = emailET.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Task failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signupClicked(View view) {
        Intent intent = new Intent(context, UserEmailSignupActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}