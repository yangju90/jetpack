package indi.mat.work.android.adapter;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import indi.mat.work.android.R;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    TextView textViewNumber, textViewEnglish, textViewChinese;
    Switch aSwitchChineseInvisible;
    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewNumber = itemView.findViewById(R.id.textView);
        textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
        textViewChinese = itemView.findViewById(R.id.textViewChinese);
        aSwitchChineseInvisible = itemView.findViewById(R.id.switchChineseInvisable);
    }
}
