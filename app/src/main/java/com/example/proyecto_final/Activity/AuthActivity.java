package com.example.proyecto_final.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.ActivityAuthBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends BaseActivity {
    ActivityAuthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        binding.Registrarbtn.setOnClickListener(v -> startActivity(new Intent(AuthActivity.this,RegistrarActivity.class)));

        binding.Loginbtn.setOnClickListener(v -> {
            if (binding.EmailTxt.getText().toString().isEmpty() || binding.PasswordTxt.getText().toString().isEmpty()) {
                showAlertDialog("Error", "Por favor, Ingrese ambos campos de correo electrónico y contraseña.");
            } else {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(binding.EmailTxt.getText().toString(),
                                binding.PasswordTxt.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                            } else {
                                showAlertDialog("Error de Autenticación", "Se ha producido un error en la autenticación del usuario.");
                            }
                        });
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}