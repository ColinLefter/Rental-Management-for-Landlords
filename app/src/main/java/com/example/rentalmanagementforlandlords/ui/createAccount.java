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

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.CreateAccountBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class createAccount extends Fragment {

    private CreateAccountBinding binding;
    private DatabaseReference root;
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

        // Handle the Create Account button click
        Button submitButton = view.findViewById(R.id.submitNewAccount);
        submitButton.setOnClickListener(v -> createNewAccount());

        return view;
    }

    private void createNewAccount() {
        // Get user input
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        double totalAssetValuation = 895000;
        double mFees = 2139;
        double qFees = mFees * 3;
        double ytdFees = mFees * 6;
        double revenue = 64230;
        double expenses = 4320;
        double incomeBTax = revenue - expenses;
        double tax = 6678.88;
        double incomeATax = incomeBTax - tax;
        double numProperties = 2;

        String[][] expensesMatrix = {
                {"We Fix It", "Shopping", "13 Dec 2023", "$75.67"},
                {"Max & Maximum Repairs", "Shopping", "14 Dec 2023", "$250.00"},
                {"Thomas' Handyman", "Repairs", "07 Dec 2023", "$19.50"},
                {"John Matthew Kayne", "Deposit", "06 Dec 2023", "$1350.00"},
                {"Ann Marlin", "Deposit", "31 Nov 2023", "$1430.00"}
        };

        // Write to database
        writeToDB(username, password, firstName, lastName, email,
                  totalAssetValuation, mFees, qFees, ytdFees,
                  revenue, expenses, incomeBTax, tax, incomeATax, incomeATax, numProperties,
                  expensesMatrix);

        // After writing to the database, navigate back to the main activity
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigateUp();
    }

    public void writeToDB(String username, String password, String firstName, String lastName,
                          String email, double totalAssetValuation, double mFees,
                          double qFees, double ytdFees, double revenue, double expenses,
                          double incomeBTax, double tax, double incomeATax, double netIncome, double numProperties,
                          String[][] expensesMatrix) {

        DatabaseReference currentStudent = root.child(username);
        currentStudent.child("password").setValue(password);
        currentStudent.child("firstName").setValue(firstName);
        currentStudent.child("lastName").setValue(lastName);
        currentStudent.child("email").setValue(email);
        currentStudent.child("totalAssetValuation").setValue(totalAssetValuation);
        currentStudent.child("mFees").setValue(mFees);
        currentStudent.child("qFees").setValue(qFees);
        currentStudent.child("ytdFees").setValue(ytdFees);
        currentStudent.child("revenue").setValue(revenue);
        currentStudent.child("expenses").setValue(expenses);
        currentStudent.child("incomeBeforeTax").setValue(incomeBTax);
        currentStudent.child("tax").setValue(tax);
        currentStudent.child("incomeAfterTax").setValue(incomeATax);
        currentStudent.child("netIncome").setValue(netIncome);

        List<List<String>> expensesList = new ArrayList<>();
        for (String[] expensesRow : expensesMatrix) {
            List<String> rowList = new ArrayList<>(Arrays.asList(expensesRow));
            expensesList.add(rowList);
        }
        currentStudent.child("expensesMatrix").setValue(expensesList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}