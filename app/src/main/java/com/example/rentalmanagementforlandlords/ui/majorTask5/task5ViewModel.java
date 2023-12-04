package com.example.rentalmanagementforlandlords.ui.majorTask5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class task5ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public task5ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}