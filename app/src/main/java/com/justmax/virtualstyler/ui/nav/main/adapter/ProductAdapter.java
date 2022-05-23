package com.justmax.virtualstyler.ui.nav.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;
import com.justmax.virtualstyler.util.Download;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final Context ctx;
    private final List<Product.RecommendationMain> listProducts;
    private Handler hand;

    public ProductAdapter(Context ctx, List<Product.RecommendationMain> listProducts) {
        this.ctx = ctx;
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItem = LayoutInflater.from(ctx).inflate(R.layout.item_sec1_product, parent, false);
        hand = new Handler(ctx.getMainLooper());
        return new ProductAdapter.ProductViewHolder(productItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product.RecommendationMain recMain = listProducts.get(position);
        /*File fileRec = new File(ctx.getExternalCacheDir().getAbsolutePath() + "/main_recommendation/");
        File fileImg = new File(fileRec.getAbsolutePath() + "/" + recMain.getID() + ".png");

        Drawable[] dr = new Drawable[] {
                holder.imgBg.getDrawable(),
                Drawable.createFromPath(fileImg.getPath())
        };
        TransitionDrawable td = new TransitionDrawable(dr);*/

        try {
            Log.d("Download", "Попытка скачать");
            URL url = new URL(recMain.getPathImg());
            Bitmap imgBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            Drawable dr = new BitmapDrawable(holder.imgBg.getResources(), imgBitmap);
            holder.imgBg.setImageBitmap(imgBitmap);
//            holder.imgBg.setImageDrawable(dr);
            Log.d("Bitmap", "Должно получиться");
        } catch (IOException e) {
            e.printStackTrace();
        }



        holder.txtTitle.setText(recMain.getTitle());

        if (recMain.existsDiscount()) {
            holder.txtDiscount.setVisibility(View.VISIBLE);
            double price = recMain.getPrice();
            double discount = recMain.getDiscount();
            int percentDiscount = 100 - (int) (discount * 100 / price);
            StringBuilder sb = new StringBuilder("Скидка -" + percentDiscount + "%");
            holder.txtDiscount.setText(sb);
        } else
            holder.txtDiscount.setVisibility(View.INVISIBLE);

        holder.txtDescription.setText(recMain.getDescription());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgBg;
        private final TextView txtTitle, txtDiscount, txtDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBg = itemView.findViewById(R.id.item_sec1_product_img);
            txtTitle = itemView.findViewById(R.id.item_sec1_product_details_title);
            txtDiscount = itemView.findViewById(R.id.item_sec1_product_details_discount);
            txtDescription = itemView.findViewById(R.id.item_sec1_product_details_description);
        }
    }
}
