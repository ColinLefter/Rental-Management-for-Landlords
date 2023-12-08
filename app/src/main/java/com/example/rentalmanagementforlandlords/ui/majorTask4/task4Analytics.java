package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4AnalyticsBinding;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class task4Analytics extends Fragment {

    private Task4AnalyticsBinding binding;
    private DatabaseReference databaseReference;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task4AnalyticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize Firebase and userID
        databaseReference = FirebaseDatabase.getInstance().getReference();
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        userID = sharedViewModel.getUserID();

        // Set up button click listeners
        binding.monthlyButton.setOnClickListener(v -> fetchData("monthlyExpenses"));
        binding.quarterlyButton.setOnClickListener(v -> fetchData("quarterlyExpenses"));
        binding.ytdButton.setOnClickListener(v -> fetchData("yearToDateExpenses"));

        Button monthlyButton = root.findViewById(R.id.monthly_button);
        Button quarterlyButton = root.findViewById(R.id.quarterly_button);
        Button ytdButton = root.findViewById(R.id.ytd_button);
        Button expensesButton = root.findViewById(R.id.expensesButton);
        Button accountsButton = root.findViewById(R.id.accountsButton2);

        // Initial data fetch for other fields
        fetchDataForOtherFields();

        expensesButton.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            // Replace with actual action ID for navigating to expenses
            navController.navigate(R.id.task4Expenses);
        });

        accountsButton.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            // Replace with actual action ID for navigating to accounts
            navController.navigate(R.id.task4AccountsSummary);
        });

        return root;
    }

    private void fetchData(String key) {
        databaseReference.child(userID).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    // Update repairsValue TextView with the fetched value
                    binding.repairsValue.setText("CA$" + value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private void fetchDataForOtherFields() {
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String address = dataSnapshot.child("address").getValue(String.class);
                    String beds = dataSnapshot.child("beds").getValue(String.class);
                    String bath = dataSnapshot.child("bath").getValue(String.class);

                    binding.propertyAddress.setText(address);
                    binding.numBeds.setText(beds + " beds");
                    binding.numBaths.setText(bath + " baths");
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