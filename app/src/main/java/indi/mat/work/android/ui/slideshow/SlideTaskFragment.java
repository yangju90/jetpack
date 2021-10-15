package indi.mat.work.android.ui.slideshow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import indi.mat.work.android.R;
import indi.mat.work.android.databinding.FragmentSlideTaskBinding;
import indi.mat.work.android.databinding.FragmentSlideshowBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlideTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlideTaskFragment extends Fragment {


    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideTaskBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SlideTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SlideTaskFragment.
     */
    public static SlideTaskFragment newInstance(String param1, String param2) {
        SlideTaskFragment fragment = new SlideTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        slideshowViewModel = new ViewModelProvider(getActivity()).get(SlideshowViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_slide_task, container, false);
        binding.setSlideShow(slideshowViewModel);
        binding.setLifecycleOwner(getActivity());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getView().findViewById(R.id.button_1).setOnClickListener((View view) ->{
            slideshowViewModel.setmText("this is Task Edit");
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.action_nav_slideTask_to_nav_slideItem);
        });

        getActivity().findViewById(R.id.top_bar_button).setVisibility(View.GONE);

        super.onActivityCreated(savedInstanceState);
    }

}