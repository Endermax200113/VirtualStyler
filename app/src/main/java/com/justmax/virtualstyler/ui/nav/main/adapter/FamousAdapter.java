package com.justmax.virtualstyler.ui.nav.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FamousAdapter extends BaseAdapter {

    private final Context ctx;
    private final List<Product.FamousMain> list;

    private LayoutInflater inflater;

    public FamousAdapter(Context ctx, List<Product.FamousMain> list) {
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

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Product.FamousMain famMain = list.get(i);

        if (inflater == null)
            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.item_sec2_famous, null);

        ConstraintLayout lytMain = view.findViewById(R.id.item_sec2_famous_main);
        ImageView imgBg = view.findViewById(R.id.item_sec2_famous_img);
        TextView txtOldPrice = view.findViewById(R.id.item_sec2_famous_details_oldPrice);
        TextView txtNewPrice = view.findViewById(R.id.item_sec2_famous_details_newPrice);
        LinearLayout lytBgDiscount = view.findViewById(R.id.item_sec2_famous_details_bgDiscount);
        TextView txtDiscount = view.findViewById(R.id.item_sec2_famous_details_discount);
        TextView txtTitle = view.findViewById(R.id.item_sec2_famous_details_title);
        TextView txtDescription = view.findViewById(R.id.item_sec2_famous_details_description);

        int top = lytMain.getPaddingTop();
        int left = lytMain.getPaddingStart();
        int bottom = lytMain.getPaddingBottom();
        int right = lytMain.getPaddingRight();
        int orienration = ctx.getResources().getConfiguration().orientation;

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

        try {
            URL url = new URL(famMain.getPathImg());
            Bitmap imgBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgBg.setImageBitmap(imgBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int res = (int) Math.round(famMain.getOldPrice());
        StringBuilder sb = new StringBuilder("" + res + " руб.");
        if ((famMain.getNewPrice() < famMain.getOldPrice() && famMain.getNewPrice() > 0) || famMain.existsDiscount()) {
            txtOldPrice.setText(sb);
            txtOldPrice.setPaintFlags(txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            res = (int) Math.round(famMain.getNewPrice());
            sb = new StringBuilder("" + res + " руб.");
            txtNewPrice.setText(sb);

            int percentDiscount = 100 - (int) (famMain.getNewPrice() * 100 / famMain.getOldPrice());
            lytBgDiscount.setVisibility(View.VISIBLE);
            sb = new StringBuilder("-" + percentDiscount + "%");
            txtDiscount.setText(sb);
        } else {
            txtNewPrice.setText(sb);
            lytBgDiscount.setVisibility(View.GONE);
        }

        txtTitle.setText(famMain.getTitle());
        txtDescription.setText(famMain.getDescription());

        return view;
    }
}
