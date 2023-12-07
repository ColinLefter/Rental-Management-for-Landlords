package com.example.rentalmanagementforlandlords.ui.majorTask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalmanagementforlandlords.R;

public class task2_RepairsActivity extends AppCompatActivity {

    ImageView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_2_activity_repairs);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
    }

    public void onClickRepairs (View view){
        setContentView(R.layout.task_2_activity_repairs);
    }

    public void onClickGen1(View view){
        setContentView(R.layout.task_2_maps);
        mapView = findViewById(R.id.mapView);
        mapView.setImageResource(R.drawable.map);
    }
}
