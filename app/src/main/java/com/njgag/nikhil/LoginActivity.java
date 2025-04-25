package com.njgag.nikhil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


public class LoginActivity extends AppCompatActivity {

private RewardedAd rewardedAd;
    EditText nameInput, emailInput;
    Button saveButton;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
      
      
        
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseUser useree = mAuth.getCurrentUser();

    
    
    
        nameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.passwordInput);
        
        saveButton = findViewById(R.id.loginButton);
        
        
        
        saveButton.setOnClickListener(v -> {
          progressdilog.showCustomProgressDialog(LoginActivity.this, "wait..");
          
          
            String email = nameInput.getText().toString();
            String pass = emailInput.getText().toString();
            
            if (email.isEmpty() || pass.isEmpty()) {
    Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
    loadRewardedAd();
    return;
}
            
            
            mAuth.signInWithEmailAndPassword(email, pass)
    .addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            FirebaseUser user = mAuth.getCurrentUser();
            // Signup successful, proceed
            Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(this, MainActivity.class));
            finish();
            
            
        } else {
            // Handle error: task.getException()
            Toast.makeText(this, "login Faild", Toast.LENGTH_SHORT).show();
            
        }
    });
            
            
            
            //startActivity(new Intent(this, MainActivity.class));
          
        });
       
    // চাইলে এখানে intent দিয়ে DetailsActivity বা EditActivity-তে যেতে পারো
        
        
    }
    
     private void loadRewardedAd() {
       
       
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
              progressdilog.hideCustomProgressDialog();
                rewardedAd = ad;
                rewardedAd.show(LoginActivity.this, rewardItem -> {
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    Toast.makeText(LoginActivity.this, "পুরস্কার: " + rewardAmount + " " + rewardType, Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                rewardedAd = null;
            }
        });
    }
   
}