package indi.mat.work.android.ui.room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import indi.mat.work.android.R;
import indi.mat.work.android.adapter.RoomRecyclerAdapter;
import indi.mat.work.android.base.BaseFragment;
import indi.mat.work.android.base.LViewModelProviders;
import indi.mat.work.android.databinding.FragmentHomeBinding;
import indi.mat.work.android.databinding.FragmentRoomBinding;
import indi.mat.work.android.model.entity.Word;
import indi.mat.work.android.model.viewmodel.RoomViewModel;


public class RoomFragment extends BaseFragment {

    FragmentRoomBinding binding;
    RoomViewModel roomViewModel;
    RoomRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setRoomViewModel(roomViewModel = LViewModelProviders.of(this, RoomViewModel.class));
        binding.setLifecycleOwner(this);
        toolBarInfo.setIsVisible(true);
        recyclerView = binding.recyclerView;
        adapter = new RoomRecyclerAdapter(roomViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    protected void setListener() {
        super.setListener();
        binding.add.setOnClickListener((View v) -> {
            String[] english = {
              "Hello", "World","Android","Google","Studio","Project","Database","Recycler", "View"
            };

            String[] chinese = {
                    "你好", "世界","安卓","谷歌","工程","项目","数据库","回收者", "视图"
            };

            Word[] words = new Word[english.length];
            for (int i = 0; i< english.length; i++) {
                words[i] = new Word(english[i], chinese[i], false);
            }

            roomViewModel.add(words);
        });


        binding.clear.setOnClickListener((View v) -> {
            roomViewModel.clear();
        });
    }

    @Override
    protected void setObserver() {
        super.setObserver();

        roomViewModel.getAllWords().observe(this, (List<Word> words) -> {
            int temp = adapter.getItemCount();
            adapter.setWordList(words);
//            if(temp != adapter.getItemCount()){
                adapter.notifyDataSetChanged();
//            }
        });
    }
}