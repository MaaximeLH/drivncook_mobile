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

import org.json.JSONException;
import org.json.JSONObject;

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
            }
        });
    }

    private void isValidCredentials() {
        final String email = this.email_login.getText().toString();
        final String password = this.password_login.getText().toString();

        final String URL = "https://drivncook.store/api/login";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    checkLoginReponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loginError", error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded"); // Il faut préciser ça pour pouvoir récupérer les valeurs en PHP simplement avec $_POST
                return params;
            }
        };

        queue.add(sr);
    }

    public void checkLoginReponse(String response) throws JSONException {
        final JSONObject responseLogin = new JSONObject(response);

        if(responseLogin.getString("error").equals("true")) {
            this.password_login.setText("");
            Toast.makeText(MainActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
        } else {
            Intent it = new Intent(MainActivity.this, UserActivity.class);
            it.putExtra("firstname", responseLogin.getString("firstname"));
            it.putExtra("lastname", responseLogin.getString("lastname"));
            it.putExtra("email", responseLogin.getString("email"));
            it.putExtra("id", responseLogin.getString("id"));
            it.putExtra("point", responseLogin.getString("point"));
            startActivity(it);
        }

    }
}