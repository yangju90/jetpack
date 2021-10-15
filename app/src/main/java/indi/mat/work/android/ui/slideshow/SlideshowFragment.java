package indi.mat.work.android.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import indi.mat.work.android.R;
import indi.mat.work.android.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(getActivity()).get(SlideshowViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_slideshow, container, false);
        binding.setSlideShow(slideshowViewModel);
        binding.setLifecycleOwner(getActivity());

        getActivity().findViewById(R.id.top_bar_button).setVisibility(View.VISIBLE);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Button button = getView().findViewById(R.id.button_1);
        button.setOnClickListener((View view) ->{
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.action_nav_slideshow_to_nav_slideTask);
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}