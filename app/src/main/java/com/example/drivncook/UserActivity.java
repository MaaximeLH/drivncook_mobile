package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView point_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        this.point_show = findViewById(R.id.point_show);

        Intent it = getIntent();
        String point = "0";
        if(it != null) {
            point = it.getStringExtra("point");
        }

        point_show.setText("Vous avez actuellement " + point + " point(s) de fidélité sur votre compte Driv'N'Cook !");
    }
}