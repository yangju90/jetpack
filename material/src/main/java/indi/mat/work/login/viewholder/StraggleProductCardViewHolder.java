package indi.mat.work.login.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import indi.mat.work.login.R;

public class StraggleProductCardViewHolder extends RecyclerView.ViewHolder{
    public TextView productTitle;
    public TextView productPrice;
    public ImageView productImage;


    public StraggleProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productPrice = itemView.findViewById(R.id.product_price);
        productTitle = itemView.findViewById(R.id.product_title);
        productImage = itemView.findViewById(R.id.product_image);
    }
}
