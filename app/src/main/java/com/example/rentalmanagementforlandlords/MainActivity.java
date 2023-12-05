package com.example.rentalmanagementforlandlords;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rentalmanagementforlandlords.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference root;

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

}