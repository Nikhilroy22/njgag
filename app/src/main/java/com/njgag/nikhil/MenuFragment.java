package com.njgag.nikhil;


import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Map;

//firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;


public class MenuFragment extends Fragment {
  
  
  	@Override
		public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		  
		  
				View view = inflater.inflate(R.layout.menufragment, container, false);
				
				
				EditText name = view.findViewById(R.id.name);
				EditText city = view.findViewById(R.id.city);
				EditText address = view.findViewById(R.id.address);
				Button savebutton = view.findViewById(R.id.savebutton);
				
				
				FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
				FirebaseFirestore db = FirebaseFirestore.getInstance();
				
				
				savebutton.setOnClickListener(v ->{
				  String namen = name.getText().toString();
				  String cityn = city.getText().toString();
				  String addressn = address.getText().toString();
				  
				   String uid = user.getUid();
				  
				  Map<String, Object> userProfile = new HashMap<>();
            userProfile.put("name", namen);
            userProfile.put("city", cityn);
            userProfile.put("address", addressn);
				  
				  // Firestore এ সেভ করো
            db.collection("use").document(uid).set(userProfile)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(requireContext(), "প্রোফাইল Firestore এ সেভ হয়েছে", Toast.LENGTH_SHORT).show();
                });
				  
				  
				 
				  //savebutton.setText(namen);
				  
				  
				});
				
				
				FirebaseMessaging.getInstance().getToken()
    .addOnCompleteListener(task -> {
        if (!task.isSuccessful()) {
            name.setText("Fetching FCM registration token failed");
            return;
        }

        // Get new FCM registration token
        String token = task.getResult();

        // Firestore এ সেভ করো
        name.setText(token);
        
    });
				
				
				
				
				
				return view;
				
		}
}