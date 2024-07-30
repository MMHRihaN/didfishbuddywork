package com.example.bhdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Home_Page extends AppCompatActivity {



    Button profile , rating , video , Post, ML  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        profile = findViewById(R.id.Profile) ;
        rating = findViewById(R.id.Rating)  ;
        video = findViewById(R.id.Video) ;
        Post = findViewById(R.id.post) ;
        ML = findViewById(R.id.ML);

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, RatingApp.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, UserProfile_Page.class));
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, Youtube_Video_Embedded.class));
            }
        });

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, Post_Page.class));
            }
        });
        ML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, MachineLearning.class));
            }
        });
    }
}