package indi.mat.work.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import indi.mat.work.android.R;
import indi.mat.work.android.widget.controller.DefaultDialogViewController;
import indi.mat.work.android.widget.face.IDialogViewController;
import indi.mat.work.android.widget.face.IOnButtonClickListener;

public class PromptDialog extends Dialog {

    IDialogViewController dialogViewController;

    protected PromptDialog(@NonNull Context context) {
        super(context, R.style.PromptDialog);
    }

    protected PromptDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PromptDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected void setDialogViewController(IDialogViewController view){
        this.dialogViewController =view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dialogViewController.getContentView());
    }

    @Override
    public void show() {
        super.show();
        getWindow().setWindowAnimations(R.style.dialogWindowAnim);
    }

    public static class PromptDialogBuilder {

        private PromptDialog promptDialog;
        private int dialogType=0;
        private boolean mCancelable=true;
        private IDialogViewController dialogView;
        private Context mContext;

        public PromptDialogBuilder(Context context) {
            mContext = context;
        }

        public PromptDialogBuilder setDialogView(IDialogViewController dialogView) {
            this.dialogView = dialogView;
            return this;
        }

        public PromptDialogBuilder setTitle(String text) {
            getDialogView().setTitle(text);
            return this;
        }

        public PromptDialogBuilder setContent(String text) {
            getDialogView().setContent(text);
            return this;
        }

        public PromptDialogBuilder hasProgress() {
            dialogType=1;
            return this;
        }

        public PromptDialogBuilder setCancelable(Boolean  cancelable) {
            mCancelable=cancelable;
            return this;
        }


        public PromptDialogBuilder setLeftButton(String text, IOnButtonClickListener clickListener) {
            getDialogView().setLeftButton(text, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v,promptDialog);
                }
            });
            return this;
        }

        public PromptDialogBuilder setRightButton(String text, IOnButtonClickListener clickListener) {
            getDialogView().setRightButton(text, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v,promptDialog);
                }
            });
            return this;
        }

        public PromptDialog create(){
            if(dialogType==0){
                promptDialog=new PromptDialog(mContext);
            }else if (dialogType==1){
                promptDialog=new UpdatePromptDialog(mContext);
            }
            promptDialog.setCancelable(mCancelable);
            promptDialog.setDialogViewController(getDialogView());
            return promptDialog;
        }

        private IDialogViewController getDialogView() {
            if (dialogView == null) {
                dialogView = new DefaultDialogViewController(mContext);
            }
            return dialogView;
        }
    }
}
