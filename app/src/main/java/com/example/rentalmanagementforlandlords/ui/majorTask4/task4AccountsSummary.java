package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class task4AccountsSummary extends Fragment {

    private DatabaseReference databaseReference;
    private TextView netIncome, revenueAmount, expensesAmount, incomeBTaxAmount, taxAmount, incomeATaxAmount, netIncomeAmount, numPropertiesOwned;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_4_accounts_summary, container, false);

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        userID = sharedViewModel.getUserID(); // Retrieve the userID

        // Initialize Firebase Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Initialize TextViews
        netIncome = view.findViewById(R.id.netIncome);
        revenueAmount = view.findViewById(R.id.revenueAmount);
        expensesAmount = view.findViewById(R.id.expensesAmount);
        incomeBTaxAmount = view.findViewById(R.id.incomeBTaxAmount);
        taxAmount = view.findViewById(R.id.taxAmount);
        incomeATaxAmount = view.findViewById(R.id.incomeATaxAmount);
        netIncomeAmount = view.findViewById(R.id.netIncomeAmount);
        numPropertiesOwned = view.findViewById(R.id.numPropertiesOwned);

        fetchData();

        return view;
    }

    private void fetchData() {
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve and set data for each TextView
                    String revenue = dataSnapshot.child("revenue").getValue(String.class);
                    String expenses = dataSnapshot.child("expenses").getValue(String.class);
                    String incomeTaxPc = dataSnapshot.child("incomeTaxPc").getValue(String.class);
                    String numProperties = dataSnapshot.child("numProperties").getValue(String.class);

                    double revenueValue = Double.parseDouble(revenue);
                    double expensesValue = Double.parseDouble(expenses);
                    double incomeTaxPcValue = Double.parseDouble(incomeTaxPc) / 100;
                    double incomeBTax = revenueValue - expensesValue;
                    double tax = incomeTaxPcValue * incomeBTax;
                    double incomeATax = incomeBTax - tax;

                    revenueAmount.setText(revenue);
                    expensesAmount.setText(expenses);
                    incomeBTaxAmount.setText(incomeTaxPc);
                    taxAmount.setText(tax + "");
                    incomeATaxAmount.setText(incomeATax + "");
                    netIncomeAmount.setText(incomeATax + "");
                    netIncome.setText(incomeATax + "");
                    numPropertiesOwned.setText(numProperties);
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
        // Binding cleanup
    }
}
