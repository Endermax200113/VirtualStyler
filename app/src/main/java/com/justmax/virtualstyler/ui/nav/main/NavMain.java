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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.data.Product;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.nav.main.adapter.FamousAdapter;
import com.justmax.virtualstyler.ui.nav.main.adapter.ProductsAdapter;
import com.justmax.virtualstyler.ui.nav.main.adapter.RecommendationAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NavMain extends Fragment {
    private View root;
    private List<Product.RecommendationMain> listRecMain;
    private List<Product.FamousMain> listFamMain;
    private List<Product.UsualMain> listProMain;
    private SwipeRefreshLayout swipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = rebuild(inflater, container);

        swipe = root.findViewById(R.id.nav_main_refresh);
        swipe.setOnRefreshListener(() -> rebuild(inflater, container));
        swipe.setColorSchemeResources(R.color.basic);

        return root;
    }

    @SuppressLint("InflateParams")
    private View rebuild(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        root = inflater.inflate(R.layout.fragment_nav_main, container, false);
        Handler hand = new Handler(root.getContext().getMainLooper());

        new Thread(() -> {
            listRecMain = new ArrayList<>();
            listFamMain = new ArrayList<>();
            listProMain = new ArrayList<>();

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
                }

                rsRecMain.close();
                rsRecMain.getStatement().close();

                ResultSet rsFamMain = MySQL.select("SELECT * FROM `main_famous` WHERE `display` = true");

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

                rsFamMain.close();
                rsFamMain.getStatement().close();

                ResultSet rsCount = MySQL.select("SELECT COUNT(*) FROM `products`");
                Objects.requireNonNull(rsCount).next();
                int countProducts = rsCount.getInt(1);
                Log.d("Количество", "" + countProducts);
                rsCount.close();
                rsCount.getStatement().close();

                if (countProducts <= 6) {
                    ResultSet rsProMain = MySQL.select("SELECT * FROM `products`");

                    while (Objects.requireNonNull(rsProMain).next()) {
                        int ID = rsProMain.getInt("ID");
                        String title = rsProMain.getString("title");
                        String type = rsProMain.getString("type");
                        String pathImages = rsProMain.getString("image");
                        double newPrice = rsProMain.getDouble("discount");
                        double oldPrice = rsProMain.getDouble("price");
                        int when = rsProMain.getInt("when");

                        ArrayList<String> listImages = new ArrayList<>(Arrays.asList(pathImages.split("\n")));

                        //Где 7 и 272 -- заглушки для рейтинга
                        if (newPrice > 0)
                            listProMain.add(new Product.UsualMain(ID, title, type, listImages, oldPrice, newPrice, 7, 272, when));
                        else
                            listProMain.add(new Product.UsualMain(ID, title, type, listImages, oldPrice, 7, 272, when));
                    }

                    rsProMain.close();
                    rsProMain.getStatement().close();
                } else {
                    ArrayList<Integer> randomID = new ArrayList<>();
                    Random rand = new Random();

                    for (int i = 0; i < 6; i++) {
                        int randID = Math.abs(1 + rand.nextInt() % countProducts);

                        while (findedNumber(randomID, randID)) {
                            if (randID == countProducts) randID = 1;
                            else randID++;
                        }

                        randomID.add(randID);
                    }

                    StringBuilder $ = new StringBuilder("SELECT * FROM `products` WHERE");
                    for (int i = 0; i < 6; i++) {
                        $.append(" `id` = ").append(randomID);
                        if (i < 5) $.append(" OR");
                    }

                    ResultSet rsProMain = MySQL.select(new String($));

                    while (Objects.requireNonNull(rsProMain).next()) {
                        int ID = rsProMain.getInt("ID");
                        String title = rsProMain.getString("title");
                        String type = rsProMain.getString("type");
                        String pathImages = rsProMain.getString("image");
                        double newPrice = rsProMain.getDouble("discount");
                        double oldPrice = rsProMain.getDouble("price");
                        int when = rsProMain.getInt("when");

                        ArrayList<String> listImages = new ArrayList<>(Arrays.asList(pathImages.split("\n")));

                        //Где 7 и 272 -- заглушки для рейтинга
                        if (newPrice > 0)
                            listProMain.add(new Product.UsualMain(ID, title, type, listImages, oldPrice, newPrice, 7, 272, when));
                        else
                            listProMain.add(new Product.UsualMain(ID, title, type, listImages, oldPrice, 7, 272, when));
                    }

                    rsProMain.close();
                    rsProMain.getStatement().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySQL.disconnect();
            }

            hand.post(() -> {
                setMainRecommendationRecycler(listRecMain);
                setMainFamousGrid(listFamMain);
                setMainProductsGrid(listProMain);
                swipe.setRefreshing(false);
            });
        }).start();

        return root;
    }

    private boolean findedNumber(@NonNull ArrayList<Integer> list, int number) {
        boolean finded = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == number) finded = true;
        }

        return finded;
    }

    private void setMainProductsGrid(List<Product.UsualMain> listProMain) {
        GridView gvMainPro = root.findViewById(R.id.nav_main_sec3_products);
        ProductsAdapter adapter = new ProductsAdapter(root.getContext(), listProMain);
        gvMainPro.setAdapter(adapter);
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
