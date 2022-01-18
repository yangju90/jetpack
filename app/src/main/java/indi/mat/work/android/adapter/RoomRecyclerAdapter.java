package indi.mat.work.android.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import indi.mat.work.android.R;
import indi.mat.work.android.model.entity.Word;
import indi.mat.work.android.model.viewmodel.RoomViewModel;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private  List<Word> wordList;
    private RoomViewModel roomViewModel;

    public RoomRecyclerAdapter(RoomViewModel roomViewModel) {
        this.wordList  = roomViewModel.getListLiveDataWords().getValue();
        this.roomViewModel = roomViewModel;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_ceil_layout, parent, false);
        return new RoomViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(null);
        Word word = wordList.get(position);
        holder.textViewNumber.setText(String.valueOf(word.getId()));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());
        if(word.getChineseInvisible()){
            holder.textViewChinese.setVisibility(View.GONE);
            holder.aSwitchChineseInvisible.setChecked(true);
        }else{
            holder.textViewChinese.setVisibility(View.VISIBLE);
            holder.aSwitchChineseInvisible.setChecked(false);
        }
        holder.itemView.setOnClickListener((v) ->{
            Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.textViewChinese.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                    roomViewModel.update(word);
                }else{
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                    roomViewModel.update(word);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList != null ? wordList.size() : 0;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }
}
