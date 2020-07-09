package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PromotionsActivity extends AppCompatActivity {

    private ListView lv;
    private List<Promotion> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        getData();

        this.lv = this.findViewById(R.id.promotions);
    }

    public void test(List<Promotion> response) {
        PromotionAdapter adapter = new PromotionAdapter(PromotionsActivity.this, response);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int id = ((Promotion)adapterView.getAdapter().getItem(position)).getId();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drivncook.store/trucks/menu/" + id));
                startActivity(browserIntent);
            }
        });
    }

    public void getData() {
        String URL = "https://drivncook.store/api/promotions";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Promotion> results = new ArrayList<>();
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        int id = o.getInt("id");
                        String text = o.getString("text");
                        String name = o.getString("name");

                        results.add(new Promotion(text, name, id));
                    }

                    test(results);
                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getPromotions", error.toString());
            }
        });

        queue.add(sr);
    }
}