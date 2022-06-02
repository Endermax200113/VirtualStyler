package com.justmax.virtualstyler.ui;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.databinding.ActivityMainBinding;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.menu.SearchActivity;
import com.justmax.virtualstyler.ui.menu.SettingsActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int MAIN_MENUITEM_SEARCH = R.id.menuItem_main_search;
    private static final int MAIN_MENUITEM_SETTINGS = R.id.menuItem_main_settings;

    public static int scrWidth, scrHeight;
    public static WindowManager window;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySQL.setDriverJDBC();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.main_toolbar));

        BottomNavigationView navMain = binding.mainNav;
        AppBarConfiguration navConfig = new AppBarConfiguration.Builder(
                R.id.nav_main,
                R.id.nav_catalog,
                R.id.nav_mannequin,
                R.id.nav_cart,
                R.id.nav_profile
        ).build();
        NavController navCtrl = Navigation.findNavController(this, R.id.main_navView);
        NavigationUI.setupActionBarWithNavController(this, navCtrl, navConfig);
        NavigationUI.setupWithNavController(navMain, navCtrl);

        Point size = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Objects.requireNonNull(getDisplay()).getRealSize(size);
        } else {
            getWindowManager().getDefaultDisplay().getSize(size);
        }

        scrWidth = size.x;
        scrHeight = size.y;

        window = getWindowManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MAIN_MENUITEM_SETTINGS:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case MAIN_MENUITEM_SEARCH:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
