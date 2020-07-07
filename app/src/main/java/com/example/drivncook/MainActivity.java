package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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
                isValidCredentials();
                /*if(isValidCredentials()) {
                    Intent it = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(it);
                } else {
                    password_login.setText("");
                    Toast.makeText(MainActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    private void isValidCredentials() {
        final String email = this.email_login.getText().toString();
        final String password = this.password_login.getText().toString();

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        String url = "https://drivncook.store/api/login?email=" + email + "&password=" + password;
        Log.d("hello", url);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = response.trim();
                if(response.equals("false")) {
                    Toast.makeText(MainActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent it = new Intent(MainActivity.this, UserActivity.class);
                    it.putExtra("point", response + "");
                    startActivity(it);
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
}