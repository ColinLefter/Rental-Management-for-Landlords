package com.example.rentalmanagementforlandlords.ui.majorTask4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class task4ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public task4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}