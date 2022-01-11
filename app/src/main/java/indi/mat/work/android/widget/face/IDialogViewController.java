package indi.mat.work.android.widget.face;

import android.view.View;

public interface IDialogViewController {

    void setLeftButton(String text, View.OnClickListener clickListener);

    void setRightButton(String text, View.OnClickListener clickListener);

    void setSingleButton(String text, View.OnClickListener clickListener);

    void setTitle(String title);

    void setContent(String content);

    void setContentSize(int size);

    View getContentView();

    void addView(View view);

    View getRightButton();

    View getLeftButton();

    View getContentText();

}
