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
import com.example.rentalmanagementforlandlords.databinding.Task4FinancialDataBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class task4FinancialData extends Fragment {

    private Task4FinancialDataBinding financialBinding;
    private String userID;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        financialBinding = Task4FinancialDataBinding.inflate(inflater, container, false);
        View root = financialBinding.getRoot();  // Decide which root to return based on your logic

        // Initialize Firebase Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Accessing EditTexts from financialBinding
        EditText revenueInput = financialBinding.revenueInput;
        EditText expensesInput = financialBinding.expensesInput;
        EditText incomeTaxPcInput = financialBinding.incomeTaxPcInput;
        EditText numPropertiesInput = financialBinding.numPropertiesInput;

        // Get user ID from arguments
        if (getArguments() != null) {
            userID = getArguments().getString("userID");
        }

        Button continueButton = root.findViewById(R.id.continueSection1);
        continueButton.setOnClickListener(v -> {
            // Extracting values from EditTexts
            String revenue = revenueInput.getText().toString();
            String expenses = expensesInput.getText().toString();
            String incomeTaxPc = incomeTaxPcInput.getText().toString();
            String numProperties = numPropertiesInput.getText().toString();

            // Writing to the db
            DatabaseReference user = databaseReference.child(userID);
            user.child("revenue").setValue(revenue);
            user.child("expenses").setValue(expenses);
            user.child("incomeTaxPc").setValue(incomeTaxPc);
            user.child("numProperties").setValue(numProperties);

            // Navigate to task4ExpensesData fragment
            navigateToTask4ExpensesData();
        });

        return root; // Return the appropriate root
    }

    private void navigateToTask4ExpensesData() {
        // Assuming 'userID' is the username you want to pass
        Bundle bundle = new Bundle();
        bundle.putString("userID", userID);

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_task4FinancialData_to_task4ExpensesData, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        financialBinding = null;
    }
}
