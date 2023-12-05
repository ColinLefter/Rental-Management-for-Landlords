package com.example.rentalmanagementforlandlords.ui.majorTask2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.rentalmanagementforlandlords.R;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class task2_NotifActivity extends AppCompatActivity {
    TextView notifs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2_activity_notif);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //String qnNo = bundle.getString("no. of questions");

        notifs = findViewById(R.id.notifs);

        try {
            FileInputStream input = this.openFileInput("notif.txt");
            InputStreamReader reader = new InputStreamReader(input);

            // It creates a way to convert the raw data from the file into human-readable text.
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            String lineView = "";

            //checking that the file is not empty
            //runs loop to retrieve the first 3 lines, or if there is <3 lines ensure that it runs as long as there are lines
            while ((line = buffer.readLine()) != null){
                lineView += line +"\n\n";
            }
            notifs.setText(lineView);
        } catch (IOException e) {
            e.printStackTrace();
            notifs.setText("");
        }
    }

    public void onClickBack (View view){
        Intent intent = new Intent(this, Task2_Activity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }
}


