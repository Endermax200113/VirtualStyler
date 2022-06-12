package com.justmax.virtualstyler.ui.nav.catalog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.ui.MainActivity;

import java.util.ArrayList;

public class NavCatalog extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;
    private static ActionBar actionBar;
    @SuppressLint("StaticFieldLeak")
    private static ListView lvCategories;
    private static ArrayList<String> listCategories;

    private static class Listeners {
        private static final AdapterView.OnItemClickListener ON_MAIN = (p, v, i, id) -> {
            switch (listCategories.get(i)) {
                case Categories.MEN:
                    toMen();
                    break;
                case Categories.WOMEN:
                    toWomen();
                    break;
                case Categories.CHILDREN:
                    toChildren();
                    break;
            }
        };

        private static final AdapterView.OnItemClickListener ON_MEN = (p, v, i, id) -> {
            if (listCategories.get(i).equals(Categories.Men.Clothes.SHOE))
                toMenShoe();
            else
                showProduct(listCategories.get(i));
        };

        private static final AdapterView.OnItemClickListener ON_MEN_SHOE = (p, v, i, id) ->
                showProduct(listCategories.get(i));

        private static final AdapterView.OnItemClickListener ON_WOMEN = (p, v, i, id) -> {
            if (listCategories.get(i).equals(Categories.Women.Clothes.SHOE))
                toWomenShoe();
            else
                showProduct(listCategories.get(i));
        };

        private static final AdapterView.OnItemClickListener ON_WOMEN_SHOE = (p, v, i, id) ->
                showProduct(listCategories.get(i));

        private static final AdapterView.OnItemClickListener ON_CHILDREN = (p, v, i, id) -> {
            switch (listCategories.get(i)) {
                case Categories.Children.BOY:
                    toChildrenBoy();
                    break;
                case Categories.Children.GIRL:
                    toChildrenGirl();
                    break;
            }
        };

        private static final AdapterView.OnItemClickListener ON_CHILDREN_BOY = (p, v, i, id) -> {
            if (listCategories.get(i).equals(Categories.Children.Boy.Clothes.SHOE))
                toChildrenBoyShoe();
            else
                showProduct(listCategories.get(i));
        };

        private static final AdapterView.OnItemClickListener ON_CHILDREN_BOY_SHOE = (p, v, i, id) ->
                showProduct(listCategories.get(i));

        private static final AdapterView.OnItemClickListener ON_CHILDREN_GIRL = (p, v, i, id) -> {
            if (listCategories.get(i).equals(Categories.Children.Girl.Clothes.SHOE))
                toChildrenGirlShoe();
            else
                showProduct(listCategories.get(i));
        };

        private static final AdapterView.OnItemClickListener ON_CHILDREN_GIRL_SHOE = (p, v, i, id) ->
                showProduct(listCategories.get(i));

        private static void toMain() {
            actionBar.setDisplayHomeAsUpEnabled(false);
            listCategories = new ArrayList<>();
            listCategories.add(Categories.MEN);
            listCategories.add(Categories.WOMEN);
            listCategories.add(Categories.CHILDREN);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_MAIN);
        }

        private static void toMen() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toMain);
            listCategories = Categories.Men.getListClothes();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_MEN);
        }

        private static void toMenShoe() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toMen);
            listCategories = Categories.Men.getListShoe();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_MEN_SHOE);
        }

        private static void toWomenShoe() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toWomen);
            listCategories = Categories.Women.getListShoe();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_WOMEN_SHOE);
        }

        private static void toWomen() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toMain);
            listCategories = Categories.Women.getListClothes();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_WOMEN);
        }

        private static void toChildren() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toMain);
            listCategories = new ArrayList<>();
            listCategories.add(Categories.Children.BOY);
            listCategories.add(Categories.Children.GIRL);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_CHILDREN);
        }

        private static void toChildrenBoy() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toChildren);
            listCategories = Categories.Children.Boy.getListClothes();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_CHILDREN_BOY);
        }

        private static void toChildrenBoyShoe() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toChildrenBoy);
            listCategories = Categories.Children.Boy.getListShoe();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_CHILDREN_BOY_SHOE);
        }

        private static void toChildrenGirl() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toChildren);
            listCategories = Categories.Children.Girl.getListClothes();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_CHILDREN_GIRL);
        }

        private static void toChildrenGirlShoe() {
            actionBar.setDisplayHomeAsUpEnabled(true);
            MainActivity.setOnBack(Listeners::toChildrenGirl);
            listCategories = Categories.Children.Girl.getListShoe();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, listCategories);
            lvCategories.setAdapter(adapter);
            lvCategories.setOnItemClickListener(ON_CHILDREN_GIRL_SHOE);
        }

        private static void showProduct(String title) {
            Toast.makeText(ctx, "Типа появляется окно товара каталога \"" +
                    title + "\"", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nav_catalog, container, false);
        ctx = root.getContext();
        actionBar = MainActivity.actionBar;
        lvCategories = root.findViewById(R.id.nav_catalog_list);
        Listeners.toMain();

        return root;
    }
}
