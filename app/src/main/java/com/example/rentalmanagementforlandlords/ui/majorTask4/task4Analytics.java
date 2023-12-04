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

public class task4Analytics extends Fragment {

    private Task4AnalyticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task4AnalyticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button monthlyButton = root.findViewById(R.id.monthly_button);
        Button quarterlyButton = root.findViewById(R.id.quarterly_button);
        Button ytdButton = root.findViewById(R.id.ytd_button);
        Button expensesButton = root.findViewById(R.id.expensesButton);
        Button accountsButton = root.findViewById(R.id.accountsButton2);

        monthlyButton.setOnClickListener(v -> {
            // Navigation or other logic for monthly button
        });

        quarterlyButton.setOnClickListener(v -> {
            // Navigation or other logic for quarterly button
        });

        ytdButton.setOnClickListener(v -> {
            // Navigation or other logic for YTD button
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}