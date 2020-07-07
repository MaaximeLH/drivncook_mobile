package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email_login;
    private EditText password_login;
    private Button   btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.email_login     = this.findViewById(R.id.email_login);
        this.password_login  = this.findViewById(R.id.password_login);
        this.btn_login       = this.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidCredentials()) {
                    Intent it = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(it);
                } else {
                    password_login.setText("");
                    Toast.makeText(MainActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials() {
        String email = this.email_login.getText().toString();
        String password = this.password_login.getText().toString();

        Toast.makeText(MainActivity.this, email, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
        return true;
    }
}