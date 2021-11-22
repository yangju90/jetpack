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
import indi.mat.work.login.databinding.FragmentMoveTaskSuccessBinding;


public class MoveTaskSuccessFragment extends Fragment {

    private ToolBarInfoViewModel toolBarInfoViewModel;
    private FragmentMoveTaskSuccessBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_move_task_success, container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        toolBarInfoViewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Task Success");
        toolBarInfoViewModel.setTitle(toolBarInfo);

        setListener();
        return binding.getRoot();
    }

    public void setListener() {
        binding.button.setOnClickListener((View view) -> {
            NavDirections navDirections = MoveTaskSuccessFragmentDirections.actionMoveTaskSuccessFragmentToMovePlanFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });

        binding.button1.setOnClickListener((View view) -> {
            NavDirections navDirections = MoveTaskSuccessFragmentDirections.actionMoveTaskSuccessFragmentToMoveTaskFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });
    }
}