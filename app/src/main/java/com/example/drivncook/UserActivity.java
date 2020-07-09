package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView welcome_user;
    private TextView emailView;
    private TextView point_view;
    private Button btn_promotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        welcome_user = this.findViewById(R.id.welcome_user);
        emailView = this.findViewById(R.id.email);
        point_view = this.findViewById(R.id.point_view);
        btn_promotions = this.findViewById(R.id.btn_promotions);

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

        btn_promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(UserActivity.this, PromotionsActivity.class);
                startActivity(it);
            }
        });
    }
}