package com.example.rentalmanagementforlandlords.ui.majorTask2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class task2ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public task2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}