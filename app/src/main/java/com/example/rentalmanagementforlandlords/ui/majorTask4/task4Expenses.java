package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.databinding.Task4ExpensesBinding;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.List;

public class task4Expenses extends Fragment {

    private Task4ExpensesBinding binding;
    private DatabaseReference databaseReference;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task4ExpensesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize Firebase and userID
        databaseReference = FirebaseDatabase.getInstance().getReference();
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        userID = sharedViewModel.getUserID();

        // Fetch expenses matrix data
        fetchExpensesMatrixData();

        return root;
    }

    private void fetchExpensesMatrixData() {
        databaseReference.child(userID).child("expensesMatrix").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        List<String> expense = (List<String>) snapshot.getValue();
                        updateExpenseRow(expense, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private void updateExpenseRow(List<String> expense, String rowKey) {
        int row = Integer.parseInt(rowKey);
        try {
            // Construct field names based on row number
            String receiverFieldName = (row == 0 ? "receiver" : "receiver" + row);
            String typeFieldName = (row == 0 ? "type" : "type" + row);
            String dateFieldName = (row == 0 ? "date" : "date" + row);
            String amountFieldName = (row == 0 ? "amount" : "amount" + row);

            // Use reflection to get fields dynamically
            Field receiverField = binding.getClass().getField(receiverFieldName);
            Field typeField = binding.getClass().getField(typeFieldName);
            Field dateField = binding.getClass().getField(dateFieldName);
            Field amountField = binding.getClass().getField(amountFieldName);

            TextView receiverTextView = (TextView) receiverField.get(binding);
            TextView typeTextView = (TextView) typeField.get(binding);
            TextView dateTextView = (TextView) dateField.get(binding);
            TextView amountTextView = (TextView) amountField.get(binding);

            // Set text to the TextViews
            receiverTextView.setText(expense.get(0));
            typeTextView.setText(expense.get(1));
            dateTextView.setText(expense.get(2));
            amountTextView.setText(expense.get(3));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exceptions (field not found, illegal access)
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
