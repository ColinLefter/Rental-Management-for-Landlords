package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.rentalmanagementforlandlords.R;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class task2_TodoActivity extends AppCompatActivity {
    TextView task1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2_activity_todo);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

    }
    public void onClickMark (View view){
        task1 = findViewById(R.id.task1);
        String data = (String) task1.getText();
        task1.setText("■ ̶F̶i̶x̶ ̶f̶e̶n̶c̶e̶ ̶p̶o̶s̶t̶s̶");

        String file = "todoCompleted.txt";
        FileOutputStream outputStream; //allow a file to be opened for writing
        try {
            outputStream = this.openFileOutput(file, this.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
            //Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void onClickSubmit(View view){
        setContentView(R.layout.task_2_activity_new);
        EditText taskInput = findViewById(R.id.taskInput);
        String data = taskInput.getText().toString();
        String file = "todo.txt";
        FileOutputStream outputStream; //allow a file to be opened for writing
        try {
            outputStream = this.openFileOutput(file, this.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
            //Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        task1 = findViewById(R.id.task1);
        String file = "todoCompleted.txt";
        try {
            FileInputStream input = this.openFileInput(file);
            InputStreamReader reader = new InputStreamReader(input);

            // It creates a way to convert the raw data from the file into human-readable text.
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            String lineAdd = "";

            //checking that the file is not empty

            while ((line = buffer.readLine()) != null){
                lineAdd += line +"\n";
            }
           task1.setText(lineAdd);
        } catch (IOException e) {
            e.printStackTrace();
            task1.setText("There are no completed tasks");
        }
    }

    public void onClickBackTodo(View view){
        setContentView(R.layout.task_2_activity_todo);
    }
}
