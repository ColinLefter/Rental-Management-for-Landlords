package com.example.rentalmanagementforlandlords.ui.majorTask5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task5Binding;

public class task5 extends Fragment {

    private Task5Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task5ViewModel notificationsViewModel =
                new ViewModelProvider(this).get(task5ViewModel.class);

        binding = Task5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up button click listeners
        binding.addUsers.setOnClickListener(v -> navigateToAddUsers());
        binding.editUsers.setOnClickListener(v -> navigateToEditUsers());

        return root;
    }

    private void navigateToAddUsers() {
        // Navigate to the Add Users fragment
        NavHostFragment.findNavController(this).navigate(R.id.action_task5_to_task5Add);
    }

    private void navigateToEditUsers() {
        // Navigate to the Edit Users fragment
        NavHostFragment.findNavController(this).navigate(R.id.action_task5_to_task5Edit);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
