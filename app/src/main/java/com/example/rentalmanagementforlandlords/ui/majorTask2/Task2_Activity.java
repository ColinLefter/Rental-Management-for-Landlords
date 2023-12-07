package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalmanagementforlandlords.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//home page for task2
public class Task2_Activity extends AppCompatActivity {
    TextView notifCount;
    TextView tasksCount;
    TextView notif;
    TextView repairs;
    TextView todo;
    DatabaseReference root;
    String notifOutput;
    String repairsOutput;
    String todoOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2);

        //setting the title screen
        notifCount = findViewById(R.id.notifCount);
        tasksCount = findViewById(R.id.tasksCount);



        //displaying the required things to do for notifs, repairs and to do
        //shows only the first 3 of the list
        notif = findViewById(R.id.notif);
        repairs = findViewById(R.id.repairs);
        todo = findViewById(R.id.todo);

        repairs.setText("\nRepairs & Services\n\nGeneral Services\nElectricians\nMechanics\nMovers\n");

        root = FirebaseDatabase.getInstance().getReference();
    }
    public void onClickNotif (View view){
        Intent intent = new Intent(this, task2_NotifActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickRepairs (View view){
        Intent intent = new Intent(this, task2_RepairsActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickTodo (View view){
        Intent intent = new Intent(this, task2_TodoActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
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
                    notifOutput = node.child("notif").getValue().toString();
                    repairsOutput = node.child("repairs").getValue().toString();
                    todoOutput = node.child("todo").getValue().toString();
                }
                String []  notifOutputCount = notifOutput.split("\n");
                String [] todoOutputCount = todoOutput.split("\n");
                notifCount.setText("Notifications: " + notifOutputCount.length);
                tasksCount.setText("Tasks: " + todoOutputCount);
                notif.setText(notifOutput);
                repairs.setText(repairsOutput);
                todo.setText(todoOutput);
            }
        }
    };
}