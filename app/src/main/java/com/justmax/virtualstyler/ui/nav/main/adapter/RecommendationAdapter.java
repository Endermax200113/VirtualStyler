package com.justmax.virtualstyler.ui.nav.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ProductViewHolder> {
    private final Context ctx;
    private final List<Product.RecommendationMain> listProducts;

    public RecommendationAdapter(Context ctx, List<Product.RecommendationMain> listProducts) {
        this.ctx = ctx;
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItem = LayoutInflater.from(ctx).inflate(R.layout.item_sec1_product, parent, false);
        return new RecommendationAdapter.ProductViewHolder(productItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product.RecommendationMain recMain = listProducts.get(position);

        try {
            InputStream is = (InputStream) new URL(recMain.getPathImg()).getContent();
            Drawable d = Drawable.createFromStream(is, "rec" + position);
            holder.imgBg.setImageDrawable(d);
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

        if (listProducts.size() - 1 == position) {

            float scale = ctx.getResources().getDisplayMetrics().density;
            int left = (int) (25 * scale);
            int right = (int) (25 * scale);
            int top = 0;
            int bottom = 0;
            int width = (int) (360 * scale);
            int height = (int) (200 * scale);

            ConstraintLayout.LayoutParams lytMargins = new ConstraintLayout.LayoutParams(width, height);
            lytMargins.setMargins(left, top, right, bottom);
            lytMargins.setMarginStart(left);
            lytMargins.setMarginEnd(right);
            lytMargins.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            lytMargins.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
            lytMargins.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            lytMargins.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
            lytMargins.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            lytMargins.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            holder.cvCrop.setLayoutParams(lytMargins);
        }
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgBg;
        private final TextView txtTitle, txtDiscount, txtDescription;
        private final CardView cvCrop;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBg = itemView.findViewById(R.id.item_sec1_product_img);
            txtTitle = itemView.findViewById(R.id.item_sec1_product_details_title);
            txtDiscount = itemView.findViewById(R.id.item_sec1_product_details_discount);
            txtDescription = itemView.findViewById(R.id.item_sec1_product_details_description);
            cvCrop = itemView.findViewById(R.id.item_sec1_product_card);
        }
    }
}
