package com.example.rentalmanagementforlandlords.ui.majorTask1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rentalmanagementforlandlords.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class task1_ManageInvoicing extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_task1__manage_invoicing, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        Button addTenantButton = view.findViewById(R.id.task1_manage_submit);
        addTenantButton.setOnClickListener(l -> {
            String tenantId = ((EditText)view.findViewById(R.id.manage_tenantid)).getText().toString();
            String rent = ((EditText)view.findViewById(R.id.manage_rent)).getText().toString();
            String deposit = ((EditText)view.findViewById(R.id.manage_deposit)).getText().toString();
            String cycle = ((TextInputEditText)view.findViewById(R.id.manage_cycle)).getText().toString();
            String instName = ((TextInputEditText)view.findViewById(R.id.manage_name)).getText().toString();
            String instNum = ((EditText)view.findViewById(R.id.manage_instnum)).getText().toString();
            String transNum = ((EditText)view.findViewById(R.id.manage_transnum)).getText().toString();
            String accNum = ((EditText)view.findViewById(R.id.manage_accnum)).getText().toString();
            Invoice invoice = new Invoice(tenantId, rent, deposit, cycle, instName, instNum, transNum, accNum);
            DatabaseReference invoicesRef = mDatabase.child("invoices");
            DatabaseReference pushRef = invoicesRef.push();
            pushRef.setValue(invoice).addOnSuccessListener(aVoid -> {
                System.out.println("invoice added");
            }).addOnFailureListener(e -> {
                System.out.println("invoice failed");
            });
            navController.popBackStack();
        });
        return view;
    }

    @IgnoreExtraProperties
    public static class Invoice {

        public String tenantId;
        public String rent;
        public String deposit;
        public String cycle;
        public String instName;
        public String instNum;
        public String transNum;
        public String accNum;
        public Invoice() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }
        public Invoice(String tenantId, String rent, String deposit, String cycle, String instName, String instNum, String transNum, String accNum) {
            this.tenantId = tenantId;
            this.rent = rent;
            this.deposit = deposit;
            this.cycle = cycle;
            this.instName = instName;
            this.instNum = instNum;
            this.transNum = transNum;
            this.accNum = accNum;
        }
    }
}