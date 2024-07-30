package com.example.bhdi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.cardview.widget.CardView;

import android.content.Intent;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Card extends AppCompatActivity {

    CardView blog, map, classify, bmi, qna, about, weather, details;
    ImageView btn;
    boolean doublePress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //set the title

        blog = findViewById(R.id.blog);
        map = findViewById(R.id.map);
        classify = findViewById(R.id.classify);
        bmi = findViewById(R.id.bmi);
        qna = findViewById(R.id.qna);
        about = findViewById(R.id.about_me);
        btn = findViewById(R.id.lg);
        weather = findViewById(R.id.weatherAPI);
        details = findViewById(R.id.report);

        blog.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Post_Page.class)));
        map.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MapTextData.class)));
        classify.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MachineLearning.class)));
        bmi.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Youtube_Video_Embedded.class)));
        qna.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RatingApp.class)));
        weather.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Weather.class)));
        about.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UserProfile_Page.class)));
        details.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AboutUs.class)));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("isLoggedin");
                editor.commit();
                //Toast.makeText(UserProfile_Page.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Card.this, Login_Page.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doublePress) {
            super.onBackPressed();
            return;
        }
        this.doublePress = true;
        Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doublePress = false;
            }
        }, 2000);
    }
}