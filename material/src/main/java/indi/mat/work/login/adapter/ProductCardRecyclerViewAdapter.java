package indi.mat.work.login.adapter;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

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

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<Product> productList;
    private ImageRequester imageRequester;
    private AdapterView.OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public ProductCardRecyclerViewAdapter(List<Product> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            Product product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            //Glide
//            Glide.with(holder.itemView).load(product.url).transform(new CenterCrop()).into(holder.productImage);

//            listener(new RequestListener<Drawable>() {
//                @Override
//                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                    return false;
//                }
//
//                @Override
//                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                    // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
//                    int imageWidth = resource.getIntrinsicWidth();
//                    int imageHeight = resource.getIntrinsicHeight();
//                    Log.i("Glide" , "width:" + imageWidth + ",height" + imageHeight);
////                    int height = ScreenUtils.getScreenWidth() * imageHeight / imageWidth;
//                    int imageViewWidth = holder.productImage.getWidth();
//                    int imageViewHeight = imageViewWidth * imageHeight / imageWidth;
//                    Log.i("Glide" , "newWidth:" + imageViewWidth + ",newHeight" + imageViewHeight);
//                    ViewGroup.LayoutParams para = holder.productImage.getLayoutParams();
//                    para.height = imageViewHeight;
//                    para.width = imageViewWidth;
//                    return false;
//                }
//            })

            //volley
            imageRequester.setImageFromUrl(holder.productImage, product.url);
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
