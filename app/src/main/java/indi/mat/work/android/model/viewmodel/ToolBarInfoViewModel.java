package com.newegg.logistics.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.newegg.logistics.base.BaseViewModel;

public class ToolBarInfoViewModel extends BaseViewModel {

    private MutableLiveData<String> title;
    private MutableLiveData<Boolean> isVisible;

    public ToolBarInfoViewModel() {
        title = new MutableLiveData<>();
        title.setValue("Default");
        isVisible = new MutableLiveData<>();
        isVisible.setValue(true);
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setIsVisible(MutableLiveData<Boolean> isVisible) {
        this.isVisible = isVisible;
    }

    public MutableLiveData<Boolean> getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible.setValue(isVisible);
    }

}
