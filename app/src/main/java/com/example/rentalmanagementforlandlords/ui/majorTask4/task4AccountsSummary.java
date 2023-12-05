package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.media3.common.util.Log;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4AccountsSummaryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class task4AccountsSummary extends Fragment {

    private DatabaseReference databaseReference;
    private TextView netIncome, revenueAmount, expensesAmount, incomeBTaxAmount, taxAmount, incomeATaxAmount, netIncomeAmount, numPropertiesOwned;
    private String username;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_4_accounts_summary, container, false);

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

        if (getArguments() != null) {
            username = getArguments().getString("username");
        }

        // Fetch data from Firebase
        fetchData();

        return view;
    }

    private void fetchData() {
        if (username != null) {
            databaseReference.child(username) // Use username to form the correct path
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Retrieve data from the snapshot
                                String netIncomeValue = dataSnapshot.child("netIncome").getValue(String.class);
                                String revenueValue = dataSnapshot.child("revenue").getValue(String.class);
                                String expensesValue = dataSnapshot.child("expenses").getValue(String.class);
                                String incomeBeforeTaxValue = dataSnapshot.child("incomeBeforeTax").getValue(String.class);
                                String taxValue = dataSnapshot.child("tax").getValue(String.class);
                                String incomeAfterTaxValue = dataSnapshot.child("incomeAfterTax").getValue(String.class);
                                String netIncomeTotalValue = dataSnapshot.child("netIncomeTotal").getValue(String.class);
                                String numPropertiesOwnedValue = dataSnapshot.child("numPropertiesOwned").getValue(String.class);

                                System.out.println("Net Income: " + netIncomeValue);

                                // Set values to TextViews
                                netIncome.setText(netIncomeValue);
                                revenueAmount.setText(revenueValue);
                                expensesAmount.setText(expensesValue);
                                incomeBTaxAmount.setText(incomeBeforeTaxValue);
                                taxAmount.setText(taxValue);
                                incomeATaxAmount.setText(incomeAfterTaxValue);
                                netIncomeAmount.setText(netIncomeTotalValue);
                                numPropertiesOwned.setText(numPropertiesOwnedValue);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle possible errors
                        }
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Your binding cleanup
    }
}