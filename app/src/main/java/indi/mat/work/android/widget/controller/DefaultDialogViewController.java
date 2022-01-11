package indi.mat.work.android.widget.controller;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;

import indi.mat.work.android.databinding.DialogDefaultViewBinding;
import indi.mat.work.android.widget.face.IDialogViewController;


public class DefaultDialogViewController implements IDialogViewController {

    DialogDefaultViewBinding binding;
    Context mContext;

    public DefaultDialogViewController(@NonNull Context context) {
        mContext=context;
        init();
    }

    private void init() {
        binding = DialogDefaultViewBinding.inflate(LayoutInflater.from(mContext));
    }

    @Override
    public void setLeftButton(String text, View.OnClickListener clickListener) {
        binding.dialogDefaultBtnLeft.setText(text);
        binding.dialogDefaultBtnLeft.setOnClickListener(clickListener);
    }

    @Override
    public void setRightButton(String text, View.OnClickListener clickListener) {
        binding.dialogDefaultBtnRight.setText(text);
        binding.dialogDefaultBtnRight.setOnClickListener(clickListener);
    }

    @Override
    public void setSingleButton(String text, View.OnClickListener clickListener) {
        binding.dialogDefaultBtnLeft.setVisibility(View.GONE);
        binding.dialogDefaultBtnRight.setVisibility(View.VISIBLE);
        binding.dialogDefaultBtnRight.setText(text);
        binding.dialogDefaultBtnRight.setOnClickListener(clickListener);
    }

    @Override
    public void setTitle(String title) {
        binding.dialogDefaultTvTitle.setVisibility(View.VISIBLE);
        binding.dialogDefaultTvTitle.setText(title);
    }

    @Override
    public void setContent(String content) {
        binding.dialogDefaultTvContent.setVisibility(View.VISIBLE);
        binding.dialogDefaultTvContent.setText(content);
    }

    @Override
    public void setContentSize(@Dimension int size) {
        binding.dialogDefaultTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    @Override
    public View getContentView() {
        return binding.getRoot();
    }

    @Override
    public void addView(View view) {
        binding.dialogDefaultContainer.addView(view);
    }

    @Override
    public View getRightButton() {
        return binding.dialogDefaultBtnRight;
    }

    @Override
    public View getLeftButton() {
        return binding.dialogDefaultBtnLeft;
    }

    @Override
    public View getContentText() {
        return binding.dialogDefaultTvContent;
    }
}
