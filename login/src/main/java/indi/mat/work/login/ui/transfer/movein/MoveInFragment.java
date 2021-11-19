package indi.mat.work.login.ui.transfer.movein;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.ToolBarInfoViewModel;
import indi.mat.work.login.attr.ToolBarInfo;


public class MoveInFragment extends Fragment {

    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        toolBarInfoViewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Move In");
        toolBarInfoViewModel.setTitle(toolBarInfo);


        return inflater.inflate(R.layout.fragment_move_in, container, false);
    }
}