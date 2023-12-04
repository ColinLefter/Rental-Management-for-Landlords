package com.example.rentalmanagementforlandlords.ui.majorTask3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.databinding.Task1Binding;
import com.example.rentalmanagementforlandlords.databinding.Task3Binding;

public class task3 extends Fragment {

    private Task3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task3ViewModel notificationsViewModel =
                new ViewModelProvider(this).get(task3ViewModel.class);

        binding = Task3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}