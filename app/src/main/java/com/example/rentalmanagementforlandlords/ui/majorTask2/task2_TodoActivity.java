package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalmanagementforlandlords.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class task2_TodoActivity extends AppCompatActivity {
    TextView tasks;
    DatabaseReference root;
    EditText taskInput;
    String todoCompletedOutput ="";
    String todoOutput ="";
    String username;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2_activity_todo);
        tasks = findViewById(R.id.task1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        root = FirebaseDatabase.getInstance().getReference();
    }
    public void onClickMark (View view){
        tasks.setText("Completed!");
        root = FirebaseDatabase.getInstance().getReference();
        updateDataCompleted();
    }
    public void onClickSubmit(View view) {
        taskInput = findViewById(R.id.taskInput);
        updateData();
        setContentView(R.layout.task_2_activity_todo);
    }
    public void onClickNew (View view){
        setContentView(R.layout.task_2_activity_new);
    }
    public void onClickDraft(View view){
        setContentView(R.layout.task_2_activity_drafts);
    }
    public void onClickCompleted(View view){
        setContentView(R.layout.task_2_activity_completed);
        tasks = findViewById(R.id.task1);
    }
    public void onClickBackTodo(View view){
        setContentView(R.layout.task_2_activity_todo);
    }

    public void onClickNext(View view){
        num++;
        root = FirebaseDatabase.getInstance().getReference();
        root.get().addOnCompleteListener(onValuesFetched);
    }

    public void onClickPrevious(View view){
        num--;
        root = FirebaseDatabase.getInstance().getReference();
        root.get().addOnCompleteListener(onValuesFetched);
    }

    private OnCompleteListener<DataSnapshot> onValuesFetched = new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                DataSnapshot receivedValue = task.getResult();
                for (DataSnapshot node : receivedValue.getChildren()) {
                    if (node.getKey().equals(username)){
                        todoOutput = node.child("todo").getValue().toString();
                    }
                }
                String [] todoOutputCount = todoOutput.split("\n");
                if (num > todoOutputCount.length-1) {
                    num = 0;
                }
                else if (num < 0){
                    num = todoOutputCount.length-1;
                }
                tasks.setText(todoOutputCount[num]);
            }
        }
    };

    void updateDataCompleted() {
        if (username != null) {
            root.child(username) // Use username to form the correct path
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot node) {
                            if (node.exists()) {
                                taskInput = findViewById(R.id.taskInput);
                                // Retrieve data from the snapshot
                                todoCompletedOutput = node.child("todoCompleted").getValue().toString();
                                todoCompletedOutput += taskInput.getText().toString() +"\n";


                                String [] todoSplit = node.child("todo").getValue().toString().split("\n");

                                for (int i = 0; i < todoSplit.length ; i++){
                                    if (todoSplit[i].equals(taskInput.getText().toString()+"\n") == false){
                                        todoOutput += todoSplit;
                                    }
                                }

                                //updating firebase at the relevent key and child todoCompleted
                                DatabaseReference completed = root.child(username);
                                completed.child("todoCompleted").setValue(taskInput.getText().toString());
                                completed.child("todo").setValue(taskInput.getText().toString());
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle possible errors
                        }
                    });
        }
    }

    void updateData() {
        if (username != null) {
            root.child(username) // Use username to form the correct path
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot node) {
                            if (node.exists()) {
                                tasks = findViewById(R.id.task1);
                                // Retrieve data from the snapshot
                                todoOutput = node.child("todo").getValue().toString();
                                todoOutput += tasks.getText().toString() +"\n";

                                //updating firebase at the relevent key and child todoCompleted
                                DatabaseReference completed = root.child(username);
                                completed.child("todo").setValue(tasks.getText().toString());
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle possible errors
                        }
                    });
        }
    }
}
