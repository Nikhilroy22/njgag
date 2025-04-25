package com.njgag.nikhil;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
  
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
  setContentView(R.layout.mainactivity);
  

  
    //getFcmToken();
 
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation1);

       if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, new HomeFragment()) // your default fragment
            .commit();
            
    }
    bottomNavigationView.setOnItemSelectedListener(item -> {
        int id = item.getItemId();
        Fragment selectedFragment = null;
       
        if (id == R.id.nav_home) {
            // Handle home
            
            selectedFragment = new HomeFragment();
          
            
            
        } else if (id == R.id.nav_menu) {
            // Handle profile
           selectedFragment = new MenuFragment();
           
            
            
        } else if (id == R.id.nav_search) {
            // Handle settings
           // startActivity(new Intent(this, LoginActivity.class));
            selectedFragment = new DataFragment();
            
            
            
            
        }
        
        if (selectedFragment != null) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit();
                    
        }
        return true;
    
});
  
      
      
    }
@Override
public void onBackPressed() {
    // আপনার ব্যাক প্রেস হ্যান্ডলিং লজিক এখানে
   
}
  
/* private void getFcmToken() {
    FirebaseMessaging.getInstance().getToken()
        .addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
               String token = task.getResult();
            Toast.makeText(this, "faild ttt", Toast.LENGTH_SHORT).show();
                return;
            }
            String token = task.getResult();
ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("FCM Token", token);
        clipboard.setPrimaryClip(clip);


            Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        });
        
}

  */
  
  
  
}


