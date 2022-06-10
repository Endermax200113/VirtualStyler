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
import com.justmax.virtualstyler.databinding.ActivityAuthRegisterBinding;
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
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.justmax.virtualstyler.databinding.ActivityAuthRegisterBinding binding = ActivityAuthRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText fldEmail = binding.authRegisterEmail;
        EditText fldName = binding.authRegisterName;
        EditText fldPassword = binding.authRegisterPassword;
        EditText fldRepeat = binding.authRegisterRepeat;
        Button btnRegister = binding.authRegisterBtn;
        TextView txtToLogin = binding.authRegisterToLogin;

        txtToLogin.setOnClickListener(e -> {
            Intent toLogin = new Intent(this, LoginActivity.class);
            startActivity(toLogin);
            finish();
        });

        btnRegister.setOnClickListener(e -> {
            String email = String.valueOf(fldEmail.getText());
            String name = String.valueOf(fldName.getText());
            String password = String.valueOf(fldPassword.getText());
            String repeat = String.valueOf(fldRepeat.getText());

            Pattern patternEmail = Pattern.compile("^[A-Za-z0-9]{1}[A-Za-z0-9+_.-]*@(.+)$");
            Pattern patternName = Pattern.compile("^[А-ЯЁ]{1}[а-яё]*$");

            if (email.isEmpty()) {
                Toast.makeText(this, "Вы не заполнили почту!", Toast.LENGTH_SHORT).show();
                return;
            } else if (name.isEmpty()) {
                Toast.makeText(this, "Вы не написали имя", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Вы не заполнили пароль", Toast.LENGTH_SHORT).show();
                return;
            } else if (!password.contentEquals(repeat)) {
                Toast.makeText(this, "Пароли не соответствуют друг с другом", Toast.LENGTH_SHORT).show();
                return;
            } else if (!patternEmail.matcher(email).matches()) {
                Toast.makeText(this, "Почта указана неверно!", Toast.LENGTH_SHORT).show();
                return;
            } else if (name.length() < 2) {
                Toast.makeText(this, "Имя должно быть больше 2-х символов!", Toast.LENGTH_SHORT).show();
                return;
            } else if (name.length() > 30) {
                Toast.makeText(this, "Имя должно быть меньше 30-и символов!", Toast.LENGTH_SHORT).show();
                return;
            } else if (!patternName.matcher(name).matches()) {
                Toast.makeText(this, "В имени указаны некорректные символы!", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.length() < 8) {
                Toast.makeText(this, "Пароль должен иметь не менее 8 символов!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                MySQL.connect();

                String max = "SELECT MAX(`id`) FROM `users`";
                ResultSet rs = MySQL.select(max);
                Objects.requireNonNull(rs).next();
                int iMax = rs.getInt(1);

                rs.close();
                rs.getStatement().close();

                String $ = "INSERT INTO `users`(" +
                        "`email`, " +
                        "`name`, " +
                        "`password`) VALUES(" +
                        "'" + email + "', " +
                        "'" + name + "', " +
                        "'" + encodeString(password) + "')";

                MySQL.update($);

                User.setID(iMax + 1);
                User.setEmail(email);
                User.setName(name);
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
