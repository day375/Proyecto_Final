package com.example.proyecto_final.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> finish());
        binding.callBtn.setOnClickListener(v -> {
            String phone="999567892";
            Intent intent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null));
            startActivity(intent);
        });
        binding.redesBtn.setOnClickListener(v -> {
            String facebookURL="https://www.facebook.com/DespegarPeru/?brand_redir=95188452883&locale=es_LA";
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(facebookURL));
            startActivity(intent);
        });
    }
}