package com.example.rentalmanagementforlandlords;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rentalmanagementforlandlords.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference root;
    private EditText signInUsername, signInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = FirebaseDatabase.getInstance().getReference();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.majorTask1,
                R.id.majorTask2,
                R.id.majorTask3,
                R.id.majorTask4,
                R.id.majorTask5)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main); // start from settings
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Find views by ID
        signInUsername = findViewById(R.id.signInUsername);
        signInPassword = findViewById(R.id.signInPassword);
        Button signInButton = findViewById(R.id.signInButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        // Set OnClickListener for signInButton
        signInButton.setOnClickListener(v -> attemptSignIn());
        createAccountButton.setOnClickListener(v -> navigateToCreateAccount());
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private void attemptSignIn() {
        String username = signInUsername.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();

        // Check credentials in Firebase
        root.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.child("password").getValue(String.class);
                    if (storedPassword != null && storedPassword.equals(password)) {
                        // Login successful
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        navigateToMajorTask1();
                    } else {
                        // Login failed
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private void navigateToMajorTask1() {
        Bundle bundle = new Bundle();
        bundle.putString("username", signInUsername.getText().toString().trim());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.majorTask1, bundle);
    }
    private void navigateToCreateAccount() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.createAccount);
    }
}