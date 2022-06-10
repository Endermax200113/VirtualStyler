package com.justmax.virtualstyler.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justmax.virtualstyler.databinding.ScreenSplashBinding;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.auth.LoginActivity;
import com.justmax.virtualstyler.ui.auth.RegisterActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_MILLISECONDS = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQL.setDriverJDBC();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ScreenSplashBinding binding = ScreenSplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        File file = new File(getExternalCacheDir().getAbsoluteFile() + "/users.properties");
        Properties config = new Properties();

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(this, LoginActivity.class);
            if (!file.exists())
                mainIntent = new Intent(this, RegisterActivity.class);
            else {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    config.load(fis);

                    boolean entered = Boolean.parseBoolean(config.getProperty("entered"));

                    if (entered)
                        mainIntent = new Intent(this, MainActivity.class);
                    else
                        mainIntent = new Intent(this, LoginActivity.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            startActivity(mainIntent);
            finish();
            /*mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();*/
        }, SPLASH_MILLISECONDS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
