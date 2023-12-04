package com.example.rentalmanagementforlandlords.ui.majorTask1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class task1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public task1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}