package indi.mat.work.login.ui.transfer.moveplan;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.model.ToolBarInfoViewModel;
import indi.mat.work.login.base.BaseFragment;
import indi.mat.work.login.databinding.FragmentMoveTaskBinding;


public class MoveTaskFragment extends BaseFragment {

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

        toolBarInfoViewModel.setIsVisible(true);
        toolBarInfoViewModel.setTitle("Task");
        toolBarInfoViewModel.setMenuVisible(true);


        NavController navController = Navigation.findNavController(getParentFragment().getView());
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(R.id.plan_task_nav_graph);
        System.out.println("======" + viewModelStoreOwner);


        return binding.getRoot();
    }


    public void setListener() {
        binding.button.setOnClickListener((View view) -> {
            NavDirections navDirections = MoveTaskFragmentDirections.actionMoveTaskFragmentToMoveTaskDetailFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });
    }
}