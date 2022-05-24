package com.justmax.virtualstyler.ui.nav.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.nav.main.adapter.FamousAdapter;
import com.justmax.virtualstyler.ui.nav.main.adapter.RecommendationAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavMain extends Fragment {
    private View root;
    private static List<Product.RecommendationMain> listRecMain;
    private static List<Product.FamousMain> listFamMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_nav_main, container, false);
        Handler hand = new Handler(root.getContext().getMainLooper());

        new Thread(() -> {
            listRecMain = new ArrayList<>();
            listFamMain = new ArrayList<>();

            try {
                MySQL.connect();

                ResultSet rsRecMain = MySQL.select("SELECT * FROM `main_recommendation` WHERE `display` = true");
                ResultSet rsFamMain = MySQL.select("SELECT * FROM `main_famous` WHERE `display` = true");

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
                }

                for (int i = 0; Objects.requireNonNull(rsFamMain).next() && i < 6; i++) {
                    int ID = rsFamMain.getInt("id");
                    String title = rsFamMain.getString("title");
                    String description = rsFamMain.getString("description");
                    String imgPath = rsFamMain.getString("image");

                    ResultSet rsProducts = MySQL.select("SELECT `price`, `discount` FROM `products` WHERE `id` = " + ID);
                    Objects.requireNonNull(rsProducts).next();
                    double oldPrice = rsProducts.getDouble("price");
                    double newPrice = rsProducts.getDouble("discount");
                    rsProducts.close();
                    rsProducts.getStatement().close();

                    if (newPrice > 0)
                        listFamMain.add(new Product.FamousMain(ID, title, description, imgPath, oldPrice, newPrice));
                    else
                        listFamMain.add(new Product.FamousMain(ID, title, description, imgPath, oldPrice));
                }

                rsRecMain.close();
                rsRecMain.getStatement().close();

                rsFamMain.close();
                rsFamMain.getStatement().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySQL.disconnect();
            }

            hand.post(() -> {
                setMainRecommendationRecycler(listRecMain);
                setMainFamousGrid(listFamMain);
            });
        }).start();

        return root;
    }

    private void setMainFamousGrid(List<Product.FamousMain> listFamMain) {
        GridView gvMainFam = root.findViewById(R.id.nav_main_sec2_famous);
        FamousAdapter adapter = new FamousAdapter(root.getContext(), listFamMain);
        gvMainFam.setAdapter(adapter);
    }

    private void setMainRecommendationRecycler(List<Product.RecommendationMain> listRecMain) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(root.getContext(), RecyclerView.HORIZONTAL, false);

        RecyclerView rvMainRec = root.findViewById(R.id.nav_main_sec1_recommendation_pages);
        rvMainRec.setLayoutManager(lm);

        RecommendationAdapter adapter = new RecommendationAdapter(root.getContext(), listRecMain);
        rvMainRec.setAdapter(adapter);
    }
}
