package com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.Advertisement_model;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 15/04/2018.
 */

public class advertisementAdapter extends PagerAdapter {

    private Context context;
    private List<Advertisement_model> Advertisment_data;

    RequestOptions option;

    //customized constractor
    public advertisementAdapter(Context context, List<Advertisement_model> Advertisment_data) {
        this.context = context;
        this.Advertisment_data=Advertisment_data;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.loading_gif);

    }


    private LayoutInflater layoutInflater_advertisement;

    private Integer[] images={R.drawable.photo_logo_design,R.drawable.photo_logo_design,R.drawable.photo_logo_design};

    public advertisementAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Advertisment_data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater_advertisement=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater_advertisement.inflate(R.layout.advertisement_slider,container,false);

        // Font path
        String fontPath = "beinarnormal.ttf";

        //casting silder component


        ImageView SildeImageView=(ImageView) view.findViewById(R.id.advertisement_silder_id);


        //setting android view components
        //loading image from the internet and set it into imageview using Glide
        Glide.with(context).load(Advertisment_data.get(position).getAdvertisement_image_url()).apply(option).into(SildeImageView);
        Log.i("adv_img_url",Advertisment_data.get(position).getAdvertisement_image_url());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }



}
