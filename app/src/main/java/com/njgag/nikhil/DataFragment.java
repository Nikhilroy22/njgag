package com.njgag.nikhil;


import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;


public class DataFragment extends Fragment {
  private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
  
  	@Override
		public View onCreateView( LayoutInflater _inflater,  ViewGroup _container,  Bundle _savedInstanceState) {
		  
		  
				View _view = _inflater.inflate(R.layout.datafragment, _container, false);
				
				TextView showtext = _view.findViewById(R.id.textview9);
				//userAdapter
				
				 recyclerView = _view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

				
				//Data
		FirebaseFirestore db = FirebaseFirestore.getInstance();

db.collection("use")
    .get()
    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                StringBuilder allData = new StringBuilder();
                
                showtext.setText(task.getResult().toString());
                for (QueryDocumentSnapshot document : task.getResult()) {
                  
                  
                    String name = document.getString("name"); // ধরুন আপনার ফিল্ডের নাম "name"
                    userList.add(new User(name));
                    //allData.append(name).append("\n");
                }

                // Toast এ সব নাম দেখানো

userAdapter.notifyDataSetChanged();

              //  Toast.makeText(requireContext(), allData.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(requireContext(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        }
    });		
				
				
				
				
				return _view;
				
		}
}