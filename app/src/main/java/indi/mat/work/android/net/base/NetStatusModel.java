package indi.mat.work.android.net.base;

import androidx.lifecycle.MutableLiveData;

import indi.mat.work.android.utilities.AppStatus;


public class NetStatusModel {

    private MutableLiveData<AppStatus> appActionStatus;

    private NetStatusModel() {
        this.appActionStatus = new MutableLiveData<>();
        this.appActionStatus.setValue(AppStatus.APP_ACTION_STATUS_INIT);
    }

    public static NetStatusModel getInstance(){
        return Builder.netStatusModel;
    }

    public MutableLiveData<AppStatus> getActionStatus() {
        return appActionStatus;
    }

    public void startLoading() {
        appActionStatus.setValue(AppStatus.APP_ACTION_STATUS_PROCESS_START);
    }

    public void dismissLoading() {
        appActionStatus.setValue(AppStatus.APP_ACTION_STATUS_PROCESS_END);
    }

    public void loginOut(){
        appActionStatus.setValue(AppStatus.APP_ACTION_STATUS_LOGIN_OUT);
    }


    private static class Builder{
        private final static NetStatusModel netStatusModel = new NetStatusModel();
    }

}
