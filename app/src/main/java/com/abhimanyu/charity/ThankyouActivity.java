package com.abhimanyu.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.abhimanyu.charity.activity.SplashActivity;
import com.abhimanyu.charity.activity.UserEmailLoginActivity;

public class ThankyouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankyouActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }
}