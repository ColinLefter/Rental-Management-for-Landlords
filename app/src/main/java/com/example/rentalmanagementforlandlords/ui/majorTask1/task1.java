package com.example.rentalmanagementforlandlords.ui.majorTask1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.databinding.Task1Binding;

public class task1 extends Fragment {

    private Task1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task1ViewModel dashboardViewModel =
                new ViewModelProvider(this).get(task1ViewModel.class);

        binding = Task1Binding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}