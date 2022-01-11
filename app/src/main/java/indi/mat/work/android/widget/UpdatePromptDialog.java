package indi.mat.work.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;

import indi.mat.work.android.R;
import indi.mat.work.android.databinding.ViewUpdateDialogProgressLayoutBinding;
import indi.mat.work.android.widget.controller.DefaultDialogViewController;
import indi.mat.work.android.widget.face.IDialogViewController;
import indi.mat.work.android.widget.face.IOnButtonClickListener;

public class UpdatePromptDialog extends PromptDialog {
    ViewUpdateDialogProgressLayoutBinding binding;
    final Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.scale_dialog_progress_bar_enter);

    protected UpdatePromptDialog(@NonNull Context context) {
        super(context);
    }

    protected UpdatePromptDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UpdatePromptDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewUpdateDialogProgressLayoutBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.view_update_dialog_progress_layout, null));
        dialogViewController.addView(binding.getRoot());
        dialogViewController.setContentSize((int) getContext().getResources().getDimensionPixelSize(R.dimen.text_size_24));
        binding.getRoot().setVisibility(View.GONE);
    }

    public void showProgressBar() {
        binding.getRoot().setVisibility(View.VISIBLE);
        dialogViewController.getLeftButton().setVisibility(View.GONE);
        dialogViewController.getRightButton().setVisibility(View.GONE);
        dialogViewController.getContentText().setVisibility(View.GONE);
        binding.getRoot().startAnimation(animation);
    }

    public void setCancelDownloadButton(String text, IOnButtonClickListener clickListener){
        dialogViewController.setSingleButton(text, v -> {
            if(clickListener!=null){
                clickListener.onClick(v,UpdatePromptDialog.this);
            }
        });
        dialogViewController.getRightButton().startAnimation(animation);
    }

    public void setProgress(int num) {
        binding.updateDialogProgressBar.setProgress(num, true);
        binding.updateDialogProgressNum.setText("updating  "+num+"%");
    }

    public void setSpeedBytes(double speed) {
        SpeedUnit speedUnit=getSeedUnit(speed);
        binding.updateDialogDownloadSpeed.setText(speedUnit.value+" "+speedUnit.unit);
    }

    class SpeedUnit{
        String value;
        String unit;
    }

    private SpeedUnit getSeedUnit(double bytes){
        SpeedUnit speedUnit=new SpeedUnit();
        DecimalFormat decimalFormat=new DecimalFormat("#");

        speedUnit.value=decimalFormat.format(bytes);
        speedUnit.unit="bytes/s";

        if(bytes/1024>1){
            bytes=bytes/1024;
            speedUnit.value=decimalFormat.format(bytes);
            speedUnit.unit="KB/s";
        }

        if(bytes/1024>1){
            bytes=bytes/1024;
            decimalFormat=new DecimalFormat("#.##");
            speedUnit.value=decimalFormat.format(bytes);
            speedUnit.unit="M/s";
        }
        return speedUnit;
    }
}
