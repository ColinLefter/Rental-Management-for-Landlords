package com.example.rentalmanagementforlandlords.ui.majorTask3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.rentalmanagementforlandlords.R;

public class Task3ViewListingsFragment extends Fragment {

    public Task3ViewListingsFragment() {
        // Required empty public constructor
    }

    public static Task3ViewListingsFragment newInstance() {
        return new Task3ViewListingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.task_3_viewlistings, container, false);



        return root;
    }

    private void switchToCreateListFragment() {
        Intent intent = new Intent(requireContext(), Task3CreateListActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
