package com.example.pratik.retrofit1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<Worldpopulation> countries;

    public CustomBaseAdapter(Context context, List<Worldpopulation> items) {
        this.context = context;
        this.countries = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView img_flag;
        TextView tv_rank,tv_population,tv_country;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tv_rank = (TextView) convertView.findViewById(R.id.tv_rank);
            holder.tv_population = (TextView) convertView.findViewById(R.id.tv_population);
            holder.tv_country = (TextView) convertView.findViewById(R.id.tv_country);
            holder.img_flag = (ImageView) convertView.findViewById(R.id.img_flag);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

//        Country country = (Country) getItem(position);
//        Worldpopulation worldpopulation = (Worldpopulation) getItem(position);

        holder.tv_rank.setText(countries.get(position).getRank().toString());
        holder.tv_country.setText(countries.get(position).getCountry());
        holder.tv_population.setText(countries.get(position).getPopulation());
        Picasso.with(context)
                .load(countries.get(position).getFlag())
                .resize(50, 50)
                .centerCrop()
                .into(holder.img_flag);



        return convertView;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countries.indexOf(getItem(position));
    }
}