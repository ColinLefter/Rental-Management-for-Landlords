package com.example.rentalmanagementforlandlords.ui.majorTask4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task4AccountsSummaryBinding;

public class task4AccountsSummary extends Fragment {

    private Task4AccountsSummaryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.task_4_accounts_summary, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}