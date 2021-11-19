package indi.mat.work.login.ui.home;

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
import indi.mat.work.login.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        View root = binding.getRoot();
        toolBarInfoViewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Home Work");
        toolBarInfoViewModel.setTitle(toolBarInfo);

        setListener();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void setListener() {
        binding.button.setOnClickListener((View view) -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToMoveInFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });


        binding.button2.setOnClickListener((View view) -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToMovePlanFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });
    }
}