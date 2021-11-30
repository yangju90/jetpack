package indi.mat.work.login.ui.transfer.movein;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.model.ToolBarInfoViewModel;
import indi.mat.work.login.base.BaseFragment;


public class MoveInFragment extends BaseFragment {

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
        toolBarInfoViewModel.setTitle("Move In");
        toolBarInfoViewModel.setMenuVisible(true);


        return inflater.inflate(R.layout.fragment_move_in, container, false);
    }
}