package com.justmax.virtualstyler.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justmax.virtualstyler.data.User;
import com.justmax.virtualstyler.databinding.ActivityAuthLoginBinding;
import com.justmax.virtualstyler.mysql.MySQL;
import com.justmax.virtualstyler.ui.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.justmax.virtualstyler.databinding.ActivityAuthLoginBinding binding = ActivityAuthLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText fldEmail = binding.authLoginEmail;
        EditText fldPassword = binding.authLoginPassword;
        Button btnLogin = binding.authLoginBtn;
        TextView txtToRegister = binding.authLoginToRegister;

        txtToRegister.setOnClickListener(e -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        btnLogin.setOnClickListener(e -> {
            String email = String.valueOf(fldEmail.getText());
            String password = String.valueOf(fldPassword.getText());

            Pattern patternEmail = Pattern.compile("^[A-Za-z0-9]{1}[A-Za-z0-9+_.-]*@(.+)$");

            if (email.isEmpty()) {
                Toast.makeText(this, "Вы не заполнили почту!", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Вы не заполнили пароль", Toast.LENGTH_SHORT).show();
                return;
            } else if (!patternEmail.matcher(email).matches()) {
                Toast.makeText(this, "Почта указана неверно!", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.length() < 8) {
                Toast.makeText(this, "Пароль должен иметь не менее 8 символов!", Toast.LENGTH_SHORT).show();
                return;
            }

            MySQL.connect();

            String $ = "SELECT * FROM `users` WHERE `email` = '" + email + "'";
            ResultSet rs = MySQL.select($);

            if (rs == null) {
                Toast.makeText(this, "E-mail или пароль не верны или не существуют", Toast.LENGTH_LONG).show();
                MySQL.disconnect();
                return;
            }

            try {
                rs.next();

                int ID = rs.getInt("id");
                String mail = rs.getString("email");
                String nam = rs.getString("name");
                String pass = rs.getString("password");

                if (!pass.contentEquals(encodeString(password))) {
                    Toast.makeText(this, "E-mail или пароль не верны или не существуют", Toast.LENGTH_LONG).show();

                    rs.close();
                    rs.getStatement().close();
                    return;
                }

                User.setID(ID);
                User.setEmail(mail);
                User.setName(nam);

                rs.close();
                rs.getStatement().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                MySQL.disconnect();
            }

            File file = new File(getExternalCacheDir().getAbsolutePath() + "/users.properties");
            Properties config = new Properties();

            try {
                config.setProperty("entered", "true");
                config.setProperty("id", "" + User.getID());
                config.setProperty("email", User.getEmail());
                config.setProperty("name", User.getName());

                FileOutputStream fos = new FileOutputStream(file);
                config.store(fos, "");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private static String encodeString(@NonNull String original) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(original.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder(2 * hash.length);

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1)
                    sb.append('0');

                sb.append(hex);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return original;
    }
}
