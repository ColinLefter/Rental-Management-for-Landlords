package com.example.rentalmanagementforlandlords.ui.majorTask1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rentalmanagementforlandlords.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link task1_AddTenant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class task1_AddTenant extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public task1_AddTenant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment task1_AddTenant.
     */
    // TODO: Rename and change types and number of parameters
    public static task1_AddTenant newInstance(String param1, String param2) {
        task1_AddTenant fragment = new task1_AddTenant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task1__add_tenant, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        Button addTenantButton = view.findViewById(R.id.task1_addtenant_add);
        addTenantButton.setOnClickListener(l -> {
            navController.popBackStack();
        });
        return view;
    }
}