package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.databinding.Task1Binding;
import com.example.rentalmanagementforlandlords.databinding.Task2Binding;

public class task2 extends Fragment {

    private Task2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task2ViewModel homeViewModel =
                new ViewModelProvider(this).get(task2ViewModel.class);

        binding = Task2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}