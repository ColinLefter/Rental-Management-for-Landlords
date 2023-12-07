package com.example.rentalmanagementforlandlords.ui.majorTask5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalmanagementforlandlords.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class task5_Activity extends AppCompatActivity {
    DatabaseReference root;
    TextView nameList;
    EditText user;
    Spinner spinner;
    Spinner spinnerNew;
    String names = "";
    String username;
    String [] oldLevelNames;
    String oldLevelNamesEdited = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_5_activity);

        //setting the title screen
        nameList = findViewById(R.id.nameList);
        root = FirebaseDatabase.getInstance().getReference();
        root.get().addOnCompleteListener(onValuesFetched);
    }

    public void onClickAddUser(View view) {
        setContentView(R.layout.task_5_add);
    }

    public void onClickEditUser(View view) {
        setContentView(R.layout.task_5_edit);
    }

    public void onClickAdd(View view){
        context = this;
        root = FirebaseDatabase.getInstance().getReference();
        root.get().addOnCompleteListener(onValuesFetchedAdd);
        setContentView(R.layout.task_5_activity);
    }

    public void onClickEdit(View view){
        context = this;
        root = FirebaseDatabase.getInstance().getReference();
        root.get().addOnCompleteListener(onValuesFetchedEdit);
        setContentView(R.layout.task_5_activity);
    }
    private OnCompleteListener<DataSnapshot> onValuesFetchedEdit = new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                DataSnapshot receivedValue = task.getResult();
                user = findViewById(R.id.usernameAdd);

                spinner = findViewById(R.id.spinnerEdit);
                ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(context, R.array.arraySpinnerChange, android.R.layout.simple_spinner_item);
                courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(courseAdapter);

                spinnerNew = findViewById(R.id.spinnerEditNew);
                ArrayAdapter<CharSequence> courseAdapterNew = ArrayAdapter.createFromResource(context, R.array.arraySpinnerChange, android.R.layout.simple_spinner_item);
                courseAdapterNew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(courseAdapterNew);

                String level = spinner.getSelectedItem().toString().toLowerCase();
                String levelNew = spinner.getSelectedItem().toString().toLowerCase();


                for (DataSnapshot node : receivedValue.getChildren()) {
                    if (username.equals(node.getKey())) {
                        names += node.child(levelNew).getValue().toString();
                        names += user.getText().toString();
                        oldLevelNames = node.child(level).getValue().toString().split("\n");
                    }
                }

                for (int i = 0; i < oldLevelNames.length; i++){
                    if (oldLevelNames.equals(user.getText().toString()) == false){
                        oldLevelNamesEdited += oldLevelNames[i];
                    }
                }
                DatabaseReference currentStudent = root.child(username);
                currentStudent.child(level).setValue(oldLevelNamesEdited);
                currentStudent.child(levelNew).setValue(names);
                Toast.makeText(context, "Edited!", Toast.LENGTH_SHORT).show();

            }
        }
    };
    private OnCompleteListener<DataSnapshot> onValuesFetchedAdd = new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                DataSnapshot receivedValue = task.getResult();
                user = findViewById(R.id.usernameAdd);

                spinner = findViewById(R.id.spinnerAdd);
                ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(context, R.array.arraySpinner, android.R.layout.simple_spinner_item);
                courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(courseAdapter);
                String level = spinner.getSelectedItem().toString().toLowerCase();

                for (DataSnapshot node : receivedValue.getChildren()) {
                    if (username.equals(node.getKey())) {
                        names += node.child(level).getValue().toString();
                        names += user.getText().toString();
                    }
                }
                DatabaseReference currentStudent = root.child(username);
                currentStudent.child(level).setValue(names);
                Toast.makeText(context, "Added!", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private OnCompleteListener<DataSnapshot> onValuesFetched = new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                DataSnapshot receivedValue = task.getResult();
                for (DataSnapshot node : receivedValue.getChildren()) {
                    if (username.equals(node.getKey())) {
                        names += "Owner:\n";
                        names += node.child("owner").getValue().toString();
                        names += "Administrator:\n";
                        names += node.child("admin").getValue().toString();
                        names += "Editor:\n";
                        names += node.child("editor").getValue().toString();
                        names += "Viewer:\n";
                        names += node.child("viewer").getValue().toString();
                    }
                }
                nameList.setText(names);
            }
        }
    };
}
