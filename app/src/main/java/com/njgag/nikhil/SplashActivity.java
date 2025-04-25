package com.njgag.nikhil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); // এটা ইউজ করলে layout ফাইল বানাতে হবে

        // কিছুক্ষণ দেখানোর জন্য Delay (optional)
        new Handler().postDelayed(() -> {
          FirebaseAuth mAuth = FirebaseAuth.getInstance();
          FirebaseUser useree = mAuth.getCurrentUser();
          if (useree != null) {
    // User is signed in
     startActivity(new Intent(this, MainActivity.class));
            finish();
    
}else{
  
   startActivity(new Intent(this, LoginActivity.class));
            finish();
}
          
           
        }, 1500); // 1.5 seconds
    }
}