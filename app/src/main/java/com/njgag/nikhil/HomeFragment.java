package com.njgag.nikhil;

import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import android.content.Intent;


import org.json.JSONException;
import org.json.JSONObject;


public class HomeFragment extends Fragment {
	Button button;
	
	static boolean isFirstLoad = true; // ✅ প্রথমবার ট্র্যাক করার জন্
	
		@Override
		public View onCreateView( LayoutInflater _inflater,  ViewGroup _container,  Bundle _savedInstanceState) {
		  
		  if (isFirstLoad) {
            progressdilog.showCustomProgressDialog(requireContext(), "wait..");
        }
		  
		  
		  
		  
				View _view = _inflater.inflate(R.layout.homefragment, _container, false);
				TextView textView = _view.findViewById(R.id.textview1);
				TextView showtext = _view.findViewById(R.id.textview2);
				Button signOutButton = _view.findViewById(R.id.signOutButton);
				
				FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

if (user != null) {
    String uid = user.getUid();
    String email = user.getEmail();
   // String name = user.getDisplayName(); // যদি নাম সেট করা থাকে

    // উদাহরণস্বরূপ TextView তে দেখানো
    textView.setText("ইমেইল: " + email + "\nUID: " + uid);
} else {
    textView.setText("কোনো ইউজার লগইন করা নেই");
}
	
	//fetch realtime data	
	
	FirebaseFirestore db = FirebaseFirestore.getInstance();
String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

db.collection("use").document(uid)
    .addSnapshotListener((documentSnapshot, error) -> {
        if (error != null) {
            Toast.makeText(requireContext(), "ত্রুটি: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (documentSnapshot != null && documentSnapshot.exists()) {
            String name = documentSnapshot.getString("name");
            String email = documentSnapshot.getString("city");
            String phone = documentSnapshot.getString("address");

if (isFirstLoad) {
                            progressdilog.hideCustomProgressDialog();
                            isFirstLoad = false; // ✅ পরবর্তীতে আর dialog দেখাবে না
                        }

            showtext.setText("নাম: " + name + "\nইমেইল: " + email + "\nফোন: " + phone);
        }
    });
				
				
			
signOutButton.setOnClickListener(v -> {
    FirebaseAuth.getInstance().signOut();
    Toast.makeText(requireContext(), "সাইন আউট সম্পন্ন", Toast.LENGTH_SHORT).show();

    // চাওয়ামতো এখানে Login স্ক্রিনে রিডাইরেক্ট করতে পারো
    Intent intent = new Intent(requireContext(), LoginActivity.class);
    startActivity(intent);
    requireActivity().finish(); // বর্তমান Activity বন্ধ করে দাও
});
				return _view;
		}
		
	
}