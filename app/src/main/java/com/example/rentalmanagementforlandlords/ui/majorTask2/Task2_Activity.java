package com.example.rentalmanagementforlandlords.ui.majorTask2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalmanagementforlandlords.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


//home page for task2
public class Task2_Activity extends AppCompatActivity {
    TextView notifCount;
    TextView tasksCount;
    TextView notif;
    TextView repairs;
    TextView todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2);

        //setting the title screen
        notifCount = findViewById(R.id.notifCount);
        tasksCount = findViewById(R.id.tasksCount);
        notifCount.setText("Notifications: " + counter("notif.txt"));
        tasksCount.setText("Tasks: " + counter("todo.txt"));

        //displaying the required things to do for notifs, repairs and to do
        //shows only the first 3 of the list
        notif = findViewById(R.id.notif);
        repairs = findViewById(R.id.repairs);
        todo = findViewById(R.id.todo);

        notif.setText("Tenant Notifications\n\n" + retrieval("notif.txt"));
        repairs.setText("Repairs & Services\n\n" + retrieval("repairs.txt"));
        todo.setText("To Do\n\n" + retrieval("todo.txt"));
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
    public int counter(String file) {
        try {
            FileInputStream input = this.openFileInput(file);
            InputStreamReader reader = new InputStreamReader(input);

            // It creates a way to convert the raw data from the file into human-readable text.
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            int count = 0;

            //checking that the file is not empty
            //counting the number of lines, as 1 line = 1 notifiication
            while ((line = buffer.readLine()) != null) {
                count++;
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String retrieval(String file){
        try {
            FileInputStream input = this.openFileInput(file);
            InputStreamReader reader = new InputStreamReader(input);

            // It creates a way to convert the raw data from the file into human-readable text.
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            String lineAdd = "";
            //counts the number of lines, ensures that only the first 3 lines are output
            int lineCounter = 0;

            //checking that the file is not empty
            //runs loop to retrieve the first 3 lines, or if there is <3 lines ensure that it runs
            //as long as there are lines
            while ((line = buffer.readLine()) != null && lineCounter < 3){
                //cutting off to ensure that only the first 35 chars are shown to maintain space efficiency
                if (line.length() > 35){
                    line = line.substring(0, 36     ) + "...";
                }
                lineAdd += line +"\n";
                lineCounter++;
            }
            return lineAdd;
        } catch (IOException e) {
            e.printStackTrace();
            return "There are no notifications";
        }
    }
}