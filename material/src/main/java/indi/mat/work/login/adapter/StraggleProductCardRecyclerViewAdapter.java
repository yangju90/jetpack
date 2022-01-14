package indi.mat.work.login.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import indi.mat.work.login.R;
import indi.mat.work.login.bean.Product;
import indi.mat.work.login.utilities.ImageRequester;
import indi.mat.work.login.viewholder.ProductCardViewHolder;
import indi.mat.work.login.viewholder.StraggleProductCardViewHolder;

public class StraggleProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StraggleProductCardViewHolder>{

    private List<Product> productList;
    private ImageRequester imageRequester;
    private AdapterView.OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public StraggleProductCardRecyclerViewAdapter(List<Product> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public StraggleProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_material_product_card, parent, false);
        return new StraggleProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StraggleProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            Product product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            //Glide
            Glide.with(holder.itemView).load(product.url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    // 根据图片的实际长宽判断，如果宽大于长则拉伸，如果宽小于等于长则居中显示
                    int imageWidth = resource.getIntrinsicWidth();
                    int imageHeight = resource.getIntrinsicHeight();
                    Log.i("Glide" , "width:" + imageWidth + ",height" + imageHeight);
//                    int height = ScreenUtils.getScreenWidth() * imageHeight / imageWidth;
                    int imageViewWidth = holder.productImage.getWidth();
                    int imageViewHeight = imageViewWidth * imageHeight / imageWidth;
                    Log.i("Glide" , "newWidth:" + imageViewWidth + ",newHeight" + imageViewHeight);
                    ViewGroup.LayoutParams para = holder.productImage.getLayoutParams();
                    para.height = imageViewHeight;
                    para.width = imageViewWidth;
                    return false;
                }
            }).into(holder.productImage);




        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(null, v, holder.getAdapterPosition(), 0);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
