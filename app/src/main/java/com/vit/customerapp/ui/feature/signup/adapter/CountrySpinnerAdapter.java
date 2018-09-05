package com.vit.customerapp.ui.feature.signup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vit.customerapp.R;

public class CountrySpinnerAdapter extends BaseAdapter {


    Context context;
    private String[] countryNames={"India","China","Australia","Portugle","America","Viet Nam"};
    private int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.vietnam};


    public  CountrySpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int position) {
        return "(84) ";
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, null);

        TextView mTextCountry = convertView.findViewById(R.id.text_country);
        ImageView mImageCountry = convertView.findViewById(R.id.image_country);

        mImageCountry.setImageResource(flags[position]);
        mTextCountry.setText(countryNames[position]);

        return convertView;
    }
}
