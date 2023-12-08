package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;

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
    private String userID;
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
            userID = getArguments().getString("userID");
        }

        // Fetch data from Firebase
        fetchData();

        return view;
    }

    private void fetchData() {
        if (userID != null) {
            databaseReference.child(userID) // Use userID to form the correct path
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Retrieve data from the snapshot
                                Double netIncomeValue = dataSnapshot.child("netIncome").getValue(Double.class);
                                Double revenueValue = dataSnapshot.child("revenue").getValue(Double.class);
                                // Assuming expensesValue is not needed for a TextView update
                                Double incomeBeforeTaxValue = dataSnapshot.child("incomeBeforeTax").getValue(Double.class);
                                Double taxValue = dataSnapshot.child("tax").getValue(Double.class);
                                Double incomeAfterTaxValue = dataSnapshot.child("incomeAfterTax").getValue(Double.class);
                                Double netIncomeTotalValue = dataSnapshot.child("netIncomeTotal").getValue(Double.class);
                                Integer numPropertiesOwnedValue = dataSnapshot.child("numPropertiesOwned").getValue(Integer.class);

                                Log.d("Net Income", netIncomeValue != null ? netIncomeValue.toString() : "null");

                                // Set values to TextViews
                                netIncome.setText(netIncomeValue != null ? String.format("%.2f", netIncomeValue) : "N/A");
                                revenueAmount.setText(revenueValue != null ? String.format("%.2f", revenueValue) : "N/A");
                                // expensesAmount - needs specific handling based on its data structure
                                incomeBTaxAmount.setText(incomeBeforeTaxValue != null ? String.format("%.2f", incomeBeforeTaxValue) : "N/A");
                                taxAmount.setText(taxValue != null ? String.format("%.2f", taxValue) : "N/A");
                                incomeATaxAmount.setText(incomeAfterTaxValue != null ? String.format("%.2f", incomeAfterTaxValue) : "N/A");
                                netIncomeAmount.setText(netIncomeTotalValue != null ? String.format("%.2f", netIncomeTotalValue) : "N/A");
                                numPropertiesOwned.setText(numPropertiesOwnedValue != null ? numPropertiesOwnedValue.toString() : "N/A");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("DatabaseError", databaseError.getMessage());
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