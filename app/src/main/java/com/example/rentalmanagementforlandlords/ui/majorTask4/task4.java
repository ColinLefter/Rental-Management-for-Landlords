package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4Binding;

public class task4 extends Fragment {

    private Task4Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task4Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView accountsSummary = root.findViewById(R.id.imageView);
        ImageView analytics = root.findViewById(R.id.imageView2);
        ImageView expenses = root.findViewById(R.id.imageView3);

        accountsSummary.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4AccountsSummary);
        });

        analytics.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4Analytics);
        });

        expenses.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_task4_to_task4Expenses);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}