package indi.mat.work.login.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
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
        System.out.println("HomeFragment : onCreate");
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
        System.out.println("HomeFragment : onCreateView");

        NavController navController = Navigation.findNavController(getParentFragment().getView());
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(R.id.home_nav_graph);

        System.out.println("======" + viewModelStoreOwner);

        return root;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("HomeFragment : onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("HomeFragment : onDestroyView");
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("HomeFragment : onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("HomeFragment : onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("HomeFragment : onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("HomeFragment : onStop");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HomeFragment : onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("HomeFragment : onDetach");
    }

    public void setListener() {
        binding.button.setOnClickListener((View view) -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToMoveInFragment();
            Navigation.findNavController(view).navigate(navDirections);
        });


        binding.button2.setOnClickListener((View view) -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToPlanTaskNavGraph();
            Navigation.findNavController(view).navigate(navDirections);
        });
    }
}