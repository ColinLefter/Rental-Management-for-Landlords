package com.example.rentalmanagementforlandlords.ui.majorTask1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rentalmanagementforlandlords.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class task1_AddTenant extends Fragment {

    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task1__add_tenant, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        Button addTenantButton = view.findViewById(R.id.task1_addtenant_add);
        addTenantButton.setOnClickListener(l -> {
            String name = ((TextInputEditText)view.findViewById(R.id.addtenant_name)).getText().toString();
            String birthdate = ((EditText)view.findViewById(R.id.addtenant_birthdate)).getText().toString();
            String email = ((TextInputEditText)view.findViewById(R.id.addtenant_email)).getText().toString();
            String phoneNumber = ((TextInputEditText)view.findViewById(R.id.addtenant_phone)).getText().toString();
            String address = ((TextInputEditText)view.findViewById(R.id.addtenant_address)).getText().toString();
            Tenant tenant = new Tenant(name, birthdate, email, phoneNumber, address);
            DatabaseReference tenantsRef = mDatabase.child("tenants");
            DatabaseReference pushRef = tenantsRef.push();
            pushRef.setValue(tenant).addOnSuccessListener(aVoid -> {
                System.out.println("tenant added");
            }).addOnFailureListener(e -> {
                System.out.println("tenant failed");
            });
            navController.popBackStack();
        });
        return view;
    }

    @IgnoreExtraProperties
    public static class Tenant {

        public String name;
        public String birthdate;
        public String email;
        public String phoneNumber;
        public String address;

        public Tenant() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Tenant(String name, String birthdate, String email, String phoneNumber, String address) {
            this.name = name;
            this.birthdate = birthdate;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
    }
}
