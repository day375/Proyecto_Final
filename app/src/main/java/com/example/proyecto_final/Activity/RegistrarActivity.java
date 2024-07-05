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
import com.example.proyecto_final.databinding.ActivityRegistrarBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends BaseActivity {
    ActivityRegistrarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        binding.Registrarbtn.setOnClickListener(v -> {
            if (binding.EmailTxt.getText().toString().isEmpty() || binding.PasswordTxt.getText().toString().isEmpty() || binding.NameTxt.getText().toString().isEmpty() || binding.LastnameTxt.getText().toString().isEmpty()) {
                showAlertDialog("Error", "Por favor, Ingrese ambos campos de correo electrónico y contraseña.");
            } else {
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(binding.EmailTxt.getText().toString(),
                                binding.PasswordTxt.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                startActivity(new Intent(RegistrarActivity.this, AuthActivity.class));
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