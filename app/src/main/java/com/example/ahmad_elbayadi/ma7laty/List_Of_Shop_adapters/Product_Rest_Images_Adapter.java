package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.content.Context;
import androidx.annotation.NonNull;
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
import com.example.ahmad_elbayadi.ma7laty.activities.Product_details;
/**
 * Created by Ahmad_Elbayadi on 01/05/2018.
 */

public class Product_Rest_Images_Adapter extends PagerAdapter {

    private Context mContext;
    private String List_Produst_Details;
    RequestOptions option;
    private LayoutInflater layoutInflater_product_details;
    private String [] product_photos_array;
    private Product_details pro_de;
    //default constractor
    public Product_Rest_Images_Adapter() {

         pro_de =new Product_details();

    }

    //customized constractor

    public Product_Rest_Images_Adapter(Context mContext, String list_Produst_Details) {
        this.mContext = mContext;
        List_Produst_Details = list_Produst_Details;
        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);

        try{
             this.product_photos_array=List_Produst_Details.split(",");
            //loading image from the internet and set it into imageview using Glide
            //Glide.with(mContext).load(product_photos_array[0]).apply(option).into();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public int getCount() {
        return product_photos_array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater_product_details = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater_product_details.inflate(R.layout.product_details_slider, container, false);



        ImageView SildeImageView=(ImageView) view.findViewById(R.id.product_details_silder_id);


        //setting android view components
        //loading image from the internet and set it into imageview using Glide
        Glide.with(mContext).load(product_photos_array[position]).apply(option).into(SildeImageView);

        Log.i("ahemdsalem",""+product_photos_array[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }



}
