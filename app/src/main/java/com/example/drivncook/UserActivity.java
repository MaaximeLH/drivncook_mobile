package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView welcome_user;
    private TextView emailView;
    private TextView point_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        welcome_user = this.findViewById(R.id.welcome_user);
        emailView = this.findViewById(R.id.email);
        point_view = this.findViewById(R.id.point_view);

        Intent it = getIntent();

        String firstname = "";
        String lastname = "";
        String email = "";
        String id = "";
        String point = "";

        if(it != null) {
            firstname = it.getStringExtra("firstname");
            lastname = it.getStringExtra("lastname");
            email = it.getStringExtra("email");
            id = it.getStringExtra("id");
            point = it.getStringExtra("point");
        }

        welcome_user.setText("Bienvenue " + lastname + " " + firstname);
        emailView.setText(email);
        point_view.setText(point);
    }
}