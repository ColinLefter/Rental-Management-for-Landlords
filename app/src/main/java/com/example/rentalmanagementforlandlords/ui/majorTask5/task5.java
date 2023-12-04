package com.example.rentalmanagementforlandlords.ui.majorTask5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.databinding.Task1Binding;
import com.example.rentalmanagementforlandlords.databinding.Task5Binding;

public class task5 extends Fragment {

    private Task5Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task5ViewModel notificationsViewModel =
                new ViewModelProvider(this).get(task5ViewModel.class);

        binding = Task5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}