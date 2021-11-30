package indi.mat.work.login.utilities;


import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class NormalEditTextOnKeyListener implements View.OnKeyListener {

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (isValid(((EditText)v).getText())) {
            apply();
        }
        return false;
    }

    public void apply(){};


    private boolean isValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }
}
