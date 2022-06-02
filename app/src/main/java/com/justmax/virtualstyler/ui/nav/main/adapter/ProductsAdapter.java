package com.justmax.virtualstyler.ui.nav.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;
import com.justmax.virtualstyler.ui.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends BaseAdapter {
    private static final int DELIVERY_NEVER = -1;
    private static final int DELIVERY_TODAY = 0;
    private static final int DELIVERY_TOMORROW = 1;
    private static final int DELIVERY_AFTER_TOMORROW = 2;

    private final Context ctx;
    private final List<Product.UsualMain> list;

    private LayoutInflater inflater;

    public ProductsAdapter(Context ctx, List<Product.UsualMain> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"InflateParams", "UseCompatLoadingForDrawables", "ClickableViewAccessibility"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Product.UsualMain proMain = list.get(i);

        if (inflater == null)
            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.item_sec3_products, null);

        DisplayMetrics metrics = new DisplayMetrics();
        MainActivity.window.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        view.setMinimumHeight(height / 6);

        ConstraintLayout lytMain = view.findViewById(R.id.item_sec3_products);
        ViewPager2 vp2Images = view.findViewById(R.id.item_sec3_products_pages);
        ImageButton ibFavorites = view.findViewById(R.id.item_sec3_products_favorites);
        LinearLayout lytBgDiscount = view.findViewById(R.id.item_sec3_products_bgDiscount);
        TextView txtDiscount = view.findViewById(R.id.item_sec3_products_discount);
        LinearLayout lytPagesNums = view.findViewById(R.id.item_sec3_products_pagesNums);
        TextView txtOldPrice = view.findViewById(R.id.item_sec3_products_details_oldPrice);
        TextView txtNewPrice = view.findViewById(R.id.item_sec3_products_details_newPrice);
        TextView txtTitle = view.findViewById(R.id.item_sec3_products_details_title);
        TextView txtType = view.findViewById(R.id.item_sec3_products_details_type);
        ImageView imgRate1 = view.findViewById(R.id.item_sec3_products_details_rating_rate1);
        ImageView imgRate2 = view.findViewById(R.id.item_sec3_products_details_rating_rate2);
        ImageView imgRate3 = view.findViewById(R.id.item_sec3_products_details_rating_rate3);
        ImageView imgRate4 = view.findViewById(R.id.item_sec3_products_details_rating_rate4);
        ImageView imgRate5 = view.findViewById(R.id.item_sec3_products_details_rating_rate5);
        TextView txtRatingCount = view.findViewById(R.id.item_sec3_products_details_rating_count);
        LinearLayout lytDelivery = view.findViewById(R.id.item_sec3_products_details_delivery);
        TextView txtWhen = view.findViewById(R.id.item_sec3_products_details_delivery_when);

        ArrayList<ImageView> arrRates = new ArrayList<>();
        arrRates.add(imgRate1);
        arrRates.add(imgRate2);
        arrRates.add(imgRate3);
        arrRates.add(imgRate4);
        arrRates.add(imgRate5);

        int top = lytMain.getPaddingTop();
        int left = lytMain.getPaddingStart();
        int bottom = lytMain.getPaddingBottom();
        int right = lytMain.getPaddingRight();
        int orienration = ctx.getResources().getConfiguration().orientation;
        float dp = ctx.getResources().getDisplayMetrics().density;

        if (orienration == Configuration.ORIENTATION_PORTRAIT) {
            if (i % 2 == 0)
                lytMain.setPadding(left, top, right / 2, bottom);
            else
                lytMain.setPadding(left / 2, top, right, bottom);
        } else {
            if (i % 3 == 0)
                lytMain.setPadding(left, top, right / 2, bottom);
            else if (i % 3 == 1)
                lytMain.setPadding(left / 2, top, right / 2, bottom);
            else
                lytMain.setPadding(left / 2, top, right, bottom);
        }

        vp2Images.setAdapter(new ViewPager2Adapter(ctx, proMain.getPathsImg()));

        if (proMain.getPathsImg().size() > 1) {
            lytPagesNums.setVisibility(View.VISIBLE);

            lytPagesNums.removeAllViews();

            for (int j = 0; j < proMain.getPathsImg().size(); j++) {
                LinearLayout circle = new LinearLayout(ctx);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10 * (int) dp, 10 * (int) dp);
                if (j > 0)
                    params.setMarginStart(10);

                circle.setLayoutParams(params);
                circle.setBackgroundResource(R.drawable.shape_products_pages_not);

                lytPagesNums.addView(circle);
            }
        } else
            lytPagesNums.setVisibility(View.GONE);

        vp2Images.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                lytPagesNums.removeAllViews();

                for (int j = 0; j < proMain.getPathsImg().size(); j++) {
                    LinearLayout circle = new LinearLayout(ctx);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10 * (int) dp, 10 * (int) dp);
                    if (j > 0)
                        params.setMarginStart(10);

                    circle.setLayoutParams(params);
                    if (j == position)
                        circle.setBackgroundResource(R.drawable.shape_products_pages_here);
                    else
                        circle.setBackgroundResource(R.drawable.shape_products_pages_not);

                    lytPagesNums.addView(circle);
                }
            }
        });

        ibFavorites.setOnClickListener(e -> {
            if (ibFavorites.getBackground() == ctx.getDrawable(R.drawable.ic_rating_null)) {
                ibFavorites.setBackgroundResource(R.drawable.ic_rating_full);
            } else
                ibFavorites.setBackgroundResource(R.drawable.ic_rating_null);
        });

        if (proMain.existDiscount()) {
            lytBgDiscount.setVisibility(View.VISIBLE);

            double oldPrice = proMain.getOldPrice();
            double newPrice = proMain.getNewPrice();
            int percentDiscount = 100 - (int) (newPrice * 100 / oldPrice);
            StringBuilder sb = new StringBuilder("-" + percentDiscount + "%");
            txtDiscount.setText(sb);
            sb = new StringBuilder().append(newPrice);
            txtNewPrice.setText(sb);
            sb = new StringBuilder().append(oldPrice);
            txtOldPrice.setText(sb);
            txtOldPrice.setPaintFlags(txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            lytBgDiscount.setVisibility(View.INVISIBLE);
            txtNewPrice.setVisibility(View.INVISIBLE);

            double price = proMain.getOldPrice();
            StringBuilder sb = new StringBuilder().append(price);
            txtNewPrice.setText(sb);
        }

        txtTitle.setText(proMain.getTitle());
        txtType.setText(proMain.getType());

        if (proMain.getRating() % 2 == 0) {
            for (int j = 0; j < 5; j++)
                arrRates.get(j).setImageResource(j * 2 <= proMain.getRating() ? R.drawable.ic_rating_full : R.drawable.ic_rating_null);
        } else {
            for (int j = 0; j < 5; j++)
                arrRates.get(j).setImageResource(j * 2 <= proMain.getRating() ? R.drawable.ic_rating_full : R.drawable.ic_rating_null);
            arrRates.get(proMain.getRating() / 2).setImageResource(R.drawable.ic_rating_half);
        }

        txtRatingCount.setText(new StringBuilder().append(proMain.getCount()));

        if (proMain.getWhen() == DELIVERY_NEVER)
            lytDelivery.setVisibility(View.INVISIBLE);
        else if (proMain.getWhen() == DELIVERY_TODAY) {
            lytDelivery.setVisibility(View.INVISIBLE);

            txtWhen.setText("Сегодня");
        } else if (proMain.getWhen() == DELIVERY_TOMORROW) {
            lytDelivery.setVisibility(View.INVISIBLE);

            txtWhen.setText("Завтра");
        } else if (proMain.getWhen() == DELIVERY_AFTER_TOMORROW) {
            lytDelivery.setVisibility(View.INVISIBLE);

            txtWhen.setText("Послезавтра");
        } else {
            lytDelivery.setVisibility(View.INVISIBLE);

            int when = proMain.getWhen();

            if (when % 100 >= 10 && when % 100 <= 19) {
                txtWhen.setText(new StringBuilder().append("Через ").append(when).append(" дней"));
            } else {
                if (when % 10 == 1)
                    txtWhen.setText(new StringBuilder().append("Через ").append(when).append(" день"));
                else if (when % 10 >= 2 && when % 10 <= 4)
                    txtWhen.setText(new StringBuilder().append("Через ").append(when).append(" дня"));
                else
                    txtWhen.setText(new StringBuilder().append("Через ").append(when).append(" дней"));
            }
        }

        return view;
    }

    private static class ViewPager2Adapter extends RecyclerView.Adapter<ImagesPages> {
        private final Context ctx;
        private final ArrayList<String> list;

        private ViewPager2Adapter(Context ctx, ArrayList<String> list) {
            this.ctx = ctx;
            this.list = list;
        }

        @NonNull
        @Override
        public ImagesPages onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ImagesPages(LayoutInflater.from(ctx).inflate(R.layout.item_sec3_products_page, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ImagesPages holder, int position) {
            String pathImg = list.get(position);

            try {
                InputStream is = (InputStream) new URL(pathImg).getContent();
                Drawable d = Drawable.createFromStream(is, "product" + position);
                holder.img.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private static final class ImagesPages extends RecyclerView.ViewHolder {

        private final ImageView img;

        public ImagesPages(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_sec3_products_pages_main_img);
        }
    }
}
