package com.nutstudio.message.freeeye.content;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nutstudio.message.freeeye.R;

public class ContentActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        imageView=(ImageView)findViewById(R.id.main_img);
        Glide.with(this).load(getIntent().getExtras().getString("img_url")).into(imageView);
    }
}
