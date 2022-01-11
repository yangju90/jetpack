package indi.mat.work.login.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import indi.mat.work.login.R;
import indi.mat.work.login.adapter.ProductCardRecyclerViewAdapter;
import indi.mat.work.login.base.BaseFragment;
import indi.mat.work.login.bean.Product;
import indi.mat.work.login.databinding.FragmentProductBinding;
import indi.mat.work.login.databinding.StaggeredFragmentProductBinding;
import indi.mat.work.login.model.ToolBarInfoViewModel;
import indi.mat.work.login.style.ProductGridItemDecoration;

public class StraggleProductFragment extends BaseFragment {

    private StaggeredFragmentProductBinding binding;
    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.staggered_fragment_product,  container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        View root = binding.getRoot();
        toolBarInfoViewModel.setIsVisible(true);
        toolBarInfoViewModel.setTitle("StaggeredProduct");
        toolBarInfoViewModel.setMenuVisible(true);


        // Set up the RecyclerView staggered
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                Product.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.mater_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.mater_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        return root;
    }
}
