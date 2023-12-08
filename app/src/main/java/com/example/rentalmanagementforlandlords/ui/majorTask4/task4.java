package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4Binding;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class task4 extends Fragment {

    private Task4Binding binding;
    private TextView portfolioValuation;
    private String userID;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task4Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        userID = sharedViewModel.getUserID(); // Retrieve the userID

        ImageView accountsSummary = root.findViewById(R.id.imageView);
        ImageView analytics = root.findViewById(R.id.imageView2);
        ImageView expenses = root.findViewById(R.id.imageView3);

        accountsSummary.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4AccountsSummary);
        });

        analytics.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4Analytics);
        });

        expenses.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4Expenses);
        });

        portfolioValuation = root.findViewById(R.id.portfolioValuation);
        fetchData();

        return root;
    }

    private void fetchData() {
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String portfolioValuationAmount = dataSnapshot.child("valuation").getValue(String.class);
                    portfolioValuation.setText("CA$" + portfolioValuationAmount);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}