package com.example.rentalmanagementforlandlords.ui.majorTask5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task5AddBinding;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class task5Add extends Fragment {

    private Task5AddBinding binding;
    private DatabaseReference root;
    private EditText usernameAdd;
    private Spinner spinnerAdd;
    private String names = "";
    private String username;
    private String level;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task5AddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        usernameAdd = binding.usernameAdd;
        spinnerAdd = binding.spinnerAdd;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.arraySpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdd.setAdapter(adapter);

        binding.confirmAdd.setOnClickListener(v -> onClickAdd());

        return root;
    }

    public void onClickAdd() {
        username = usernameAdd.getText().toString();
        level = spinnerAdd.getSelectedItem().toString().toLowerCase();
        root = FirebaseDatabase.getInstance().getReference();

        // Logic to add user to Firebase
        // This is an example, adapt according to your actual Firebase structure
        root.child("users").child(username).child(level).setValue("Some Value")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "User added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to add user", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
