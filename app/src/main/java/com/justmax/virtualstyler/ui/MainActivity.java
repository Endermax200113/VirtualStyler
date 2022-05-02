package com.justmax.virtualstyler.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.justmax.virtualstyler.R;
import com.justmax.virtualstyler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_settings) {
            startActivity(new Intent(this, Settings));
        }
    }*/
}
