package indi.mat.work.android;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> topBarTitle = null;

    public HomeViewModel(){
        topBarTitle = new MutableLiveData<>();
        topBarTitle.setValue("Home_Title");
    }

    public void setTopBarTitle(String topBarTitle) {
        this.topBarTitle.setValue(topBarTitle);
    }

    public MutableLiveData<String> getTopBarTitle() {
        return topBarTitle;
    }
}
