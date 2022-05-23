package com.justmax.virtualstyler.ui.nav.main;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.nav.main.adapter.ProductAdapter;
import com.justmax.virtualstyler.util.Download;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavMain extends Fragment {
    private View root;
    private static boolean created = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_nav_main, container, false);

        if (!created) {

            Handler hand = new Handler(root.getContext().getMainLooper());

            new Thread(() -> {
                List<Product.RecommendationMain> listRecMain = new ArrayList<>();

                try {
                    MySQL.connect();

                    ResultSet rsRecMain = MySQL.select("SELECT * FROM `main_recommendation` WHERE `display` = true");

                    while (Objects.requireNonNull(rsRecMain).next()) {
                        int ID = rsRecMain.getInt("id");
                        String title = rsRecMain.getString("title");
                        String description = rsRecMain.getString("description");
                        String imgPath = rsRecMain.getString("image");

                        ResultSet rsProducts = MySQL.select("SELECT `price`, `discount` FROM `products` WHERE `id` = " + ID);
                        Objects.requireNonNull(rsProducts).next();
                        double price = rsProducts.getDouble("price");
                        double discount = rsProducts.getDouble("discount");
                        rsProducts.close();
                        rsProducts.getStatement().close();

                        if (discount > 0)
                            listRecMain.add(new Product.RecommendationMain(ID, title, description, imgPath, price, discount));
                        else
                            listRecMain.add(new Product.RecommendationMain(ID, title, description, imgPath, price));

                        try {
                            File[] fileRec = new File[1];
                            File[] fileImg = new File[1];

                            fileRec[0] = new File(root.getContext().getExternalCacheDir().getAbsolutePath() + "/main_recommendation/");
                            fileImg[0] = new File(fileRec[0].getAbsolutePath() + "/" + ID + ".png");

                            if (!fileRec[0].exists()) //noinspection ResultOfMethodCallIgnored
                                fileRec[0].mkdir();

                            Log.d("File", "Расположен -> " + fileImg[0]);
                            Log.d("Database", "Файл из сайта -> " + imgPath);
                            Download.downloadFile(imgPath, fileImg[0]);
                            Log.d("Download", "Должно скачаться");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    rsRecMain.close();
                    rsRecMain.getStatement().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    MySQL.disconnect();
                }

                hand.post(() -> setMainRecommendationRecycler(listRecMain));
            }).start();

            created = true;
        }

        return root;
    }

    private void setMainRecommendationRecycler(List<Product.RecommendationMain> listRecMain) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);

        RecyclerView rvMainRec = root.findViewById(R.id.nav_main_sec1_recommendation_pages);
        rvMainRec.setLayoutManager(lm);

        ProductAdapter paMainRec = new ProductAdapter(requireContext(), listRecMain);
        rvMainRec.setAdapter(paMainRec);
    }
}
