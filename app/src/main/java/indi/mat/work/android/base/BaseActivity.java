package indi.mat.work.android.base;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import indi.mat.work.android.net.base.NetStatusModel;

public class BaseActivity extends AppCompatActivity {

    protected NetStatusModel netStatusModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netStatusModel = NetStatusModel.getInstance();

    }

    @Override
    protected void onStart() {
        setObserver();
        setListener();
        super.onStart();
    }


    protected void setObserver() {
        netStatusModel.getActionStatus().observe(this, s -> {
            switch (s){
                case APP_ACTION_STATUS_INIT: {
                    break;
                }
                case APP_ACTION_STATUS_PROCESS_START: {
                    startLoading();
                    break;
                }
                case APP_ACTION_STATUS_PROCESS_END: {
                    dismissLoading();
                    break;
                }

                case APP_ACTION_STATUS_LOGIN_OUT:{
                    logout();
                    break;
                }
            }
        });
    }



    protected void setListener(){
    }

    public void logout(){
    }

    protected void startLoading() {
        if(progressBar != null){
            if(progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }else {
            progressBar = createLoadingBar(this);
        }
    }

    protected void dismissLoading() {
        if (progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public ProgressBar createLoadingBar(Context context) {
        ProgressBar progressBar = new ProgressBar(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        addContentView(progressBar, layoutParams);

        return progressBar;
    }
}
