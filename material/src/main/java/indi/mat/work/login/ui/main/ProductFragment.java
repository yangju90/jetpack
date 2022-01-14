package indi.mat.work.login.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import indi.mat.work.login.R;
import indi.mat.work.login.model.ToolBarInfoViewModel;
import indi.mat.work.login.adapter.ProductCardRecyclerViewAdapter;
import indi.mat.work.login.base.BaseFragment;
import indi.mat.work.login.bean.Product;
import indi.mat.work.login.databinding.FragmentProductBinding;
import indi.mat.work.login.style.ProductGridItemDecoration;
import indi.mat.work.login.webview.ProductDetailInWebViewActivity;

public class ProductFragment extends BaseFragment {
    private FragmentProductBinding binding;
    private ToolBarInfoViewModel toolBarInfoViewModel;
    ProductCardRecyclerViewAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        View root = binding.getRoot();
        toolBarInfoViewModel.setIsVisible(true);
        toolBarInfoViewModel.setTitle("Product");
        toolBarInfoViewModel.setMenuVisible(true);


        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        adapter = new ProductCardRecyclerViewAdapter(
                Product.initProductEntryList(getResources()));
        binding.recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.mater_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.mater_product_grid_spacing_small);
        binding.recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        return root;
    }


    public void setListener() {
        adapter.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
                Product product = adapter.getProductList().get(position);
                Intent intent = new Intent();
                intent.putExtra("product", product.title);
                intent.setClass(getContext(), ProductDetailInWebViewActivity.class);
                startActivity(intent);
        });
    }
}