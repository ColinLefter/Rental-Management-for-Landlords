package com.example.rentalmanagementforlandlords.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.CreateAccountBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class createAccount extends Fragment {

    private CreateAccountBinding binding;
    private DatabaseReference root;
    private String userID;
    private EditText usernameEditText, passwordEditText, firstNameEditText, lastNameEditText, emailEditText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = FirebaseDatabase.getInstance().getReference();

        binding = CreateAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        usernameEditText = view.findViewById(R.id.createAccountUsername);
        passwordEditText = view.findViewById(R.id.createAccountPassword);
        firstNameEditText = view.findViewById(R.id.createAccountFirstName);
        lastNameEditText = view.findViewById(R.id.createAccountLastName);
        emailEditText = view.findViewById(R.id.createAccountEmail);

        Button createAccountButton = view.findViewById(R.id.submitNewAccount);
        createAccountButton.setOnClickListener(v -> {
            createNewAccount();
        });

        return view;
    }

    private void createNewAccount() {
        // Get user input
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        userID = username;
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setUserID(userID); // For export to all files

        // this mock data is being used because this feature requires an API connection to the user's bank account
        // the feature reflects the act of parsing the user's transaction details and automatically identifying,
        // categorizing and reporting expenses made on maintaining properties under management
        String[][] expensesMatrix = {
                {"We Fix It", "Shopping", "13 Dec 2023", "$75.67"},
                {"Max & Maximum Repairs", "Shopping", "14 Dec 2023", "$250.00"},
                {"Thomas' Handyman", "Repairs", "07 Dec 2023", "$19.50"},
                {"John Matthew Kayne", "Deposit", "06 Dec 2023", "$1350.00"},
                {"Ann Marlin", "Deposit", "31 Nov 2023", "$1430.00"},
                {"XYZ Services", "Maintenance", "15 Dec 2023", "$300.00"} // Example of new row
        };

        // Validate inputs
        if (!isInputValid(username, password, firstName, lastName, email)) {
            // Input validation failed
            return;
        }

        // Check if username already exists in the database
        root.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Username already exists
                    Toast.makeText(getContext(), "Please choose a unique username", Toast.LENGTH_SHORT).show();
                } else {
                    // Username is unique, proceed with account creation
                    writeToDB(username, password, firstName, lastName, email, expensesMatrix);
                    navigateToTask4FinancialData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private boolean isInputValid(String username, String password, String firstName, String lastName, String email) {
        // Username validation
        if (username.isEmpty() || username.length() < 3) {
            Toast.makeText(getContext(), "Username must be at least 3 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Password complexity check
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            Toast.makeText(getContext(), "Password must be at least 8 characters and include a mix of upper, lower, numbers, and special characters", Toast.LENGTH_LONG).show();
            return false;
        }

        // First and Last Name checks
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(getContext(), "Please enter both first and last name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Email format validation
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            Toast.makeText(getContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }

        // All checks passed
        return true;
    }

    private void navigateToTask4FinancialData() {
        // Assuming 'userID' is the username you want to pass
        Bundle bundle = new Bundle();
        bundle.putString("userID", userID);

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_createAccount_to_task4FinancialData, bundle);
    }

    public void writeToDB(String username, String password, String firstName, String lastName,
                          String email, String[][] expensesMatrix) {

        DatabaseReference user = root.child(username);
        user.child("password").setValue(password);
        user.child("firstName").setValue(firstName);
        user.child("lastName").setValue(lastName);
        user.child("email").setValue(email);

        List<List<String>> expensesList = new ArrayList<>();
        for (String[] expensesRow : expensesMatrix) {
            List<String> rowList = new ArrayList<>(Arrays.asList(expensesRow));
            expensesList.add(rowList);
        }
        user.child("expensesMatrix").setValue(expensesList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}