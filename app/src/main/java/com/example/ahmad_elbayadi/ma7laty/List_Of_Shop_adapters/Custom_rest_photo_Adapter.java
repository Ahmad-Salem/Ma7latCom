package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.Spacecraft;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmad_Elbayadi on 06/06/2018.
 */

public class Custom_rest_photo_Adapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public Custom_rest_photo_Adapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //INFLATE CUSTOM LAYOUT
            view = LayoutInflater.from(c).inflate(R.layout.rest_model, viewGroup, false);
        }

        final Spacecraft s = (Spacecraft) this.getItem(i);

        TextView nameTxt = (TextView) view.findViewById(R.id.nameTxt);
        ImageView img = (ImageView) view.findViewById(R.id.spacecraftImg);

        //BIND DATA
        nameTxt.setText(s.getName());
        Picasso.get().load(s.getUri()).placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1).into(img);

        //VIEW ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, s.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
