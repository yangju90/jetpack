package indi.mat.work.android.utilities;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewUtils {

    public static <T extends View> List<View> findViewWithType(View root, Class<T> type){
        List<View> views = new ArrayList<>();
        findViewWithType(root, type, views);
        return views;
    }

    private static <T extends View> void findViewWithType(View root, Class<T> type, List<View> views) {
        if(type.isInstance(root)){
            views.add(root);
        }

        if(root instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) root;
            for(int  i = 0; i < viewGroup.getChildCount(); i++){
                findViewWithType(viewGroup.getChildAt(i), type, views);
            }
        }
    }
}
