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

import com.bumptech.glide.Glide;
import com.example.proyecto_final.Domain.ItemDomian;
import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.ActivityTicketBinding;

public class TicketActivity extends BaseActivity{
    ActivityTicketBinding binding;
    private ItemDomian object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        Glide.with(TicketActivity.this)
                .load(object.getPic())
                .into(binding.pic);
        Glide.with(TicketActivity.this)
                .load(object.getTourGuidePic())
                .into(binding.profile);
        binding.backBtn.setOnClickListener(v -> finish());
        binding.titleTxt.setText(object.getTitle());
        binding.durationTxt.setText(object.getDuration());
        binding.tourGuideTxt.setText(object.getDateTour());
        binding.timeTxt.setText(object.getTimeTour());
        binding.tourGuideNameTxt.setText(object.getTourGuideName());
        binding.guestTxt.setText("S/."+object.getPrice());
        binding.callBtn.setOnClickListener(v -> {
            String phone=object.getTourGuidePhone();
            Intent intent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null));
            startActivity(intent);
        });

        binding.messageBtn.setOnClickListener(v -> {
            Intent sendIntent=new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:" + object.getTourGuidePhone()));
            sendIntent.putExtra("sms_body","Algo te podemos ayudar");
            startActivity(sendIntent);
        });
    }

    private void getIntentExtra() {
        object=(ItemDomian) getIntent().getSerializableExtra("object");
    }
}