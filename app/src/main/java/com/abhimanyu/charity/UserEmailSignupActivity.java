package com.abhimanyu.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abhimanyu.charity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserEmailSignupActivity extends AppCompatActivity {

    Context context;
    EditText emailET, passwordET, rePasswordET;
    String email, password, rePassword;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_email_signup);

        context = UserEmailSignupActivity.this;

        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        rePasswordET = findViewById(R.id.rePasswordEditText);

        progressDialog = new ProgressDialog(context);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signupClicked(View view) {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        rePassword = rePasswordET.getText().toString();

        if (email.isEmpty()) {
            emailET.setError("Enter Email");
        }
        if (password.isEmpty()) {
            passwordET.setError("Enter Password");
        }
        if (rePassword.isEmpty()) {
            passwordET.setError("Re-enter Password is empty");
        }
        if (!password.equals(rePassword)) {
            rePasswordET.setError("Password does't match");
        }
        else {
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            signup(email, password);
        }

    }

    public void signup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show();
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

    public void login(View view) {
        Intent intent = new Intent(context, UserEmailLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}