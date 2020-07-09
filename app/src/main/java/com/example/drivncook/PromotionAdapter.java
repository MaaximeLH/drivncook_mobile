package com.example.drivncook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PromotionAdapter extends BaseAdapter {

    private Context context;
    private List<Promotion> promos;

    public PromotionAdapter(Context context, List<Promotion> promos) {
        this.context = context;
        this.promos = promos;
    }

    @Override
    public int getCount() {
        return this.promos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.promos.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.row,null);
        }

        TextView promotion_text = convertView.findViewById(R.id.promotion_text);
        TextView promotion_name = convertView.findViewById(R.id.promotion_name);
        Promotion e = (Promotion) getItem(position);
        promotion_text.setText(e.getText());
        promotion_name.setText(e.getName());
        return convertView;
    }

}
