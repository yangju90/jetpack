package indi.mat.work.login;

import android.widget.Toolbar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import indi.mat.work.login.attr.ToolBarInfo;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ToolBarInfo> title;
    private MutableLiveData<Boolean> isVisible;

    public MainActivityViewModel() {
        title = new MutableLiveData<>();
        ToolBarInfo toolBarInfo = new ToolBarInfo();
        toolBarInfo.setTitle("Home");

        title.setValue(toolBarInfo);

        isVisible = new MutableLiveData<>();
        isVisible.setValue(true);
    }

    public MutableLiveData<ToolBarInfo> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<ToolBarInfo> title) {
        this.title = title;
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


    public void add(){
        System.out.println("++++++++++++++");
        if(title != null) {
            ToolBarInfo toolBarInfo = title.getValue();
            toolBarInfo.setTitle("add");
            title.setValue(toolBarInfo);
        }
    }


    public void substract(){
        System.out.println("------------");
        if(title != null) {
            ToolBarInfo toolBarInfo = title.getValue();
            toolBarInfo.setTitle("substract");
            title.setValue(toolBarInfo);
        }
    }
}
