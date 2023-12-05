package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.os.Bundle;
import android.view.View;

import com.example.rentalmanagementforlandlords.R;
import androidx.appcompat.app.AppCompatActivity;

public class task2_RepairsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2_activity_repairs);

    }


    public void onClickGen1(View view){
        setContentView(R.layout.task_2_maps);
    }
}
