package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4ExpensesDataBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class task4ExpensesData extends Fragment {

    private Task4ExpensesDataBinding expensesBinding;
    private String userID;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        expensesBinding = Task4ExpensesDataBinding.inflate(inflater, container, false);
        View root = expensesBinding.getRoot();  // Decide which root to return based on your logic

        // Initialize Firebase Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Accessing EditTexts from expensesBinding
        EditText addressInput = expensesBinding.addressInput;
        EditText bedsInput = expensesBinding.bedsInput;
        EditText bathInput = expensesBinding.bathInput;
        EditText monthExpInput = expensesBinding.monthExpInput;
        EditText quartExpInput = expensesBinding.quartExpInput;
        EditText ytdExpInput = expensesBinding.ytdExpInput;

        // Get user ID from arguments
        if (getArguments() != null) {
            userID = getArguments().getString("userID");
        }

        Button completeAccountButton = root.findViewById(R.id.completeAccount);
        completeAccountButton.setOnClickListener(v -> {
            // Extracting values from EditTexts
            String address = addressInput.getText().toString();
            String beds = bedsInput.getText().toString();
            String bath = bathInput.getText().toString();
            String monthExp = monthExpInput.getText().toString();
            String quartExp = quartExpInput.getText().toString();
            String ytdExp = ytdExpInput.getText().toString();

            // Writing to the db
            DatabaseReference user = databaseReference.child(userID);
            user.child("address").setValue(address);
            user.child("beds").setValue(beds);
            user.child("bath").setValue(bath);
            user.child("monthlyExpenses").setValue(monthExp);
            user.child("quarterlyExpenses").setValue(quartExp);
            user.child("yearToDateExpenses").setValue(ytdExp);

            // Navigate back to the main (sign-in) page
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4ExpensesData_to_mainFragment);
        });

        return root; // Return the appropriate root
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        expensesBinding = null; // Ensure to set this to null as well
    }
}
