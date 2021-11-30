package indi.mat.work.login.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolBarInfoViewModel extends ViewModel {

    private MutableLiveData<String> title;
    private MutableLiveData<Boolean> isVisible;
    private MutableLiveData<Boolean> menuVisible;

    public ToolBarInfoViewModel() {
        title = new MutableLiveData<>();
        title.setValue("Home");

        isVisible = new MutableLiveData<>();
        isVisible.setValue(true);

        menuVisible = new MutableLiveData<>();
        menuVisible.setValue(false);
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

    public MutableLiveData<Boolean> getMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(MutableLiveData<Boolean> menuVisible) {
        this.menuVisible = menuVisible;
    }


    public void setMenuVisible(Boolean menuVisible) {
        this.menuVisible.setValue(menuVisible);
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

            title.setValue("add");
        }
    }


    public void substract(){
        System.out.println("------------");
        if(title != null) {
            title.setValue("substract");
        }
    }
}
