package com.justmax.virtualstyler.ui.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.justmax.virtualstyler.databinding.ActivitySearchBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private static final int BACK = android.R.id.home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        SearchView searchView = new SearchView(this);
        ListView listView = binding.searchListView;

        setTitle("");

        if (actionBar != null) {
            actionBar.setCustomView(searchView);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchView.setQuery("", true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setLayoutDirection(SearchView.LAYOUT_DIRECTION_RTL);

        searchView.setOnCloseListener(() -> {
            onBackPressed();
            return true;
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("Футболка");
        list.add("Шорты");
        list.add("Бейсболка");
        list.add("Кроссовки");
        list.add("Куртка");
        list.add("Шапка");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //TODO Доработать поиск
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BACK) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
