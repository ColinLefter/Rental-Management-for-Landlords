package com.example.rentalmanagementforlandlords.ui.majorTask5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rentalmanagementforlandlords.R;
import com.example.rentalmanagementforlandlords.databinding.Task5EditBinding;
import com.example.rentalmanagementforlandlords.ui.SharedViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class task5Edit extends Fragment {

    private Task5EditBinding binding;
    private DatabaseReference root;
    private EditText username;
    private Spinner spinnerEdit, spinnerEditNew;
    private String names = "";
    private String level, levelNew;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = Task5EditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        username = binding.username;
        spinnerEdit = binding.spinnerEdit;
        spinnerEditNew = binding.spinnerEditNew;

        // Set up spinner for current access level
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.arraySpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdit.setAdapter(adapter);

        // Set up spinner for new access level
        ArrayAdapter<CharSequence> adapterNew = ArrayAdapter.createFromResource(getContext(), R.array.arraySpinner, android.R.layout.simple_spinner_item);
        adapterNew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditNew.setAdapter(adapterNew);

        // Add click listener for confirmEdit button
        binding.confirmEdit.setOnClickListener(v -> onClickEdit());

        return root;
    }

    public void onClickEdit() {
        String usernameText = username.getText().toString();
        String currentLevel = spinnerEdit.getSelectedItem().toString().toLowerCase();
        String newLevel = spinnerEditNew.getSelectedItem().toString().toLowerCase();

        root = FirebaseDatabase.getInstance().getReference();

        // Example logic to edit user's access level
        // Adapt this according to your actual Firebase structure and requirements
        DatabaseReference userRef = root.child("users").child(usernameText);
        userRef.child(currentLevel).removeValue(); // Remove user from current level
        userRef.child(newLevel).setValue("Some Value") // Add user to new level
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "User edited successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to edit user", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}