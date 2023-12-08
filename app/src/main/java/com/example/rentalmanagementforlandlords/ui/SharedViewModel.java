package com.example.rentalmanagementforlandlords.ui;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
