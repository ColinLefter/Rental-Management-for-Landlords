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
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public void writeToDB(String username, String password, String firstName, String lastName,
                          String email, double totalAssetValuation, double mFees,
                          double qFees, double ytdFees, double revenue, double expenses,
                          double incomeBTax, double tax, double incomeATax, double netIncome,
                          double[][] expensesMatrix) {

        DatabaseReference currentStudent = root.child(username);
        currentStudent.child("password").setValue(password);
        currentStudent.child("firstName").setValue(firstName);
        currentStudent.child("lastName").setValue(lastName);
        currentStudent.child("email").setValue(email);
        currentStudent.child("totalAssetValuation").setValue(totalAssetValuation);
        currentStudent.child("mFees").setValue(mFees);
        currentStudent.child("qFees").setValue(qFees);
        currentStudent.child("ytdFees").setValue(ytdFees);
        currentStudent.child("revenue").setValue(revenue);
        currentStudent.child("expenses").setValue(expenses);
        currentStudent.child("incomeBeforeTax").setValue(incomeBTax);
        currentStudent.child("tax").setValue(tax);
        currentStudent.child("incomeAfterTax").setValue(incomeATax);
        currentStudent.child("netIncome").setValue(netIncome);

        List<List<Double>> expensesList = new ArrayList<>();
        for (double[] expensesRow : expensesMatrix) {
            List<Double> rowList = new ArrayList<>();
            for (double expense : expensesRow) {
                rowList.add(expense);
            }
            expensesList.add(rowList);
        }
        currentStudent.child("expensesMatrix").setValue(expensesList);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.majorTask1);
    }
}