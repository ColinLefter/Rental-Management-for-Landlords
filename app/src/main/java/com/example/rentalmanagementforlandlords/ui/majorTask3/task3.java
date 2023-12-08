package com.example.rentalmanagementforlandlords.ui.majorTask3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task3Binding;

public class task3 extends Fragment {

    private Task3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task3ViewModel notificationsViewModel =
                new ViewModelProvider(this).get(task3ViewModel.class);

        binding = Task3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAnotherLayout();
            }
        });

        Button button2 = root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToViewApplicationsLayout();
            }
        });

        Button button3 = root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAgreementLayout();
            }
        });

        return root;
    }

    private void switchToAnotherLayout() {
        // Inflate the layout from task_3_viewlistings.xml
        View viewListingsView = getLayoutInflater().inflate(R.layout.task_3_viewlistings, null);

        // Replace the current content with the new layout
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(viewListingsView);

    }
    private void switchToViewApplicationsLayout() {
        View viewApplicationsView = getLayoutInflater().inflate(R.layout.task_3_viewapplications, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(viewApplicationsView);
    }

    private void switchToAgreementLayout() {
        View agreementView = getLayoutInflater().inflate(R.layout.task_3_agreement, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(agreementView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
