package indi.mat.work.login.ui.transfer.moveplan;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.ToolBarInfoViewModel;
import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.FragmentMoveTaskBinding;


public class MoveTaskFragment extends Fragment {

    private ToolBarInfoViewModel toolBarInfoViewModel;
    private FragmentMoveTaskBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_move_task, container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);

        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Task");
        toolBarInfoViewModel.setTitle(toolBarInfo);
        toolBarInfoViewModel.setIsVisible(true);


        setListener();
        return binding.getRoot();
    }


    public void setListener() {
        binding.button.setOnClickListener((View view) -> {
            NavDirections navDirections = MoveTaskFragmentDirections.actionMoveTaskFragmentToMoveTaskDetailFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });
    }
}