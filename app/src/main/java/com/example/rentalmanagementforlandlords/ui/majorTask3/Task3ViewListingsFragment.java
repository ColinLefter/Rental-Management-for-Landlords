package com.example.rentalmanagementforlandlords.ui.majorTask3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rentalmanagementforlandlords.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Task3ViewListingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Task3ViewListingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Task3ViewListingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Task3ViewListingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Task3ViewListingsFragment newInstance(String param1, String param2) {
        Task3ViewListingsFragment fragment = new Task3ViewListingsFragment();
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
        View root = inflater.inflate(R.layout.task_3_viewlistings, container, false);

        Button button7 = root.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToCreateListFragment();
            }
        });

        return root;
    }
    private void switchToCreateListFragment() {
        Task3CreateListFragment createListFragment = new Task3CreateListFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, createListFragment)
                .addToBackStack(null)
                .commit();
    }
}