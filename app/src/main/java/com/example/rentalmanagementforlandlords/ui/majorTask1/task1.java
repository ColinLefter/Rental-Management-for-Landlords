package com.example.rentalmanagementforlandlords.ui.majorTask1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task1Binding;

public class task1 extends Fragment {

    private Task1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NavController navController = NavHostFragment.findNavController(this);

        task1ViewModel dashboardViewModel =
                new ViewModelProvider(this).get(task1ViewModel.class);

        binding = Task1Binding.inflate(inflater, container, false);

        View view = binding.getRoot();
        Button addTenantButton = view.findViewById(R.id.task1_addtenant);
        addTenantButton.setOnClickListener(l -> {
            navController.navigate(R.id.action_majorTask1_to_task1_AddTenant);
        });

        Button viewConnectedAccountsButton = view.findViewById(R.id.task1_viewconnected);
        viewConnectedAccountsButton.setOnClickListener(l -> {
            navController.navigate(R.id.action_majorTask1_to_task1_ViewConnectedAccounts);
        });

        Button manageInvoicingButton = view.findViewById(R.id.task1_manageinvoicing);
        manageInvoicingButton.setOnClickListener(l -> {
            navController.navigate(R.id.action_majorTask1_to_task1_ManageInvoicing);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}