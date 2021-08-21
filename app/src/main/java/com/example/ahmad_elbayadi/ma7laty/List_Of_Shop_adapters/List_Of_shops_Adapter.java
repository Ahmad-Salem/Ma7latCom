package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.activities.List_Of_Offers;
import com.example.ahmad_elbayadi.ma7laty.activities.Shop_details;
import com.example.ahmad_elbayadi.ma7laty.activities.product_details_list;
import com.example.ahmad_elbayadi.ma7laty.models.Shop_property;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 25/04/2018.
 */

public class List_Of_shops_Adapter extends  RecyclerView.Adapter<List_Of_shops_Adapter.MyViewHolder>{

    private Context mContext;
    private List<Shop_property> mShop_properties;
    RequestOptions option;

    public List_Of_shops_Adapter(Context mContext, List<Shop_property> mShop_properties) {
        this.mContext = mContext;
        this.mShop_properties = mShop_properties;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.shop_name.setText(mShop_properties.get(position).getShop_name());
        holder.shop_activity.setText(mShop_properties.get(position).getShop_activity());
        holder.shop_goverment.setText(mShop_properties.get(position).getShop_goverment());
        holder.shop_city.setText(mShop_properties.get(position).getShop_city());
        holder.shop_country.setText(mShop_properties.get(position).getShop_country());


        //loading image from the internet and set it into imageview using Glide
        Glide.with(mContext).load(mShop_properties.get(position).getShop_photo()).apply(option).into(holder.shop_photo);

    }


    @Override
    public int getItemCount() {
        return  mShop_properties.size();
    }


    public void add_shop_item(List<Shop_property> Shop_list)
    {
        for(Shop_property shop_property : Shop_list)
        {
            mShop_properties.add(shop_property);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.shop_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);




        return viewHolder;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shop_name;
        TextView shop_activity;
        TextView shop_country;
        TextView shop_goverment;
        TextView shop_city;
        ImageView shop_photo;
        ImageView shop_location;
        ImageView shop_offers;
        ImageView shop_products;
        LinearLayout View_shop_info_container;


        public MyViewHolder(View itemView) {
            super(itemView);
            //casting android component in the adapter
            shop_name=itemView.findViewById(R.id.shop_name12);
            shop_activity=itemView.findViewById(R.id.shop_activity1);
            shop_country=itemView.findViewById(R.id.shop_country1);
            shop_goverment=itemView.findViewById(R.id.shop_government1);
            shop_city=itemView.findViewById(R.id.shop_city1);
            shop_photo=itemView.findViewById(R.id.shop_photo1);
            shop_location=itemView.findViewById(R.id.direction1);
            shop_offers=itemView.findViewById(R.id.offers_icon1);
            shop_products=itemView.findViewById(R.id.products_icon1);
            View_shop_info_container=itemView.findViewById(R.id.shop_container1);


            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);


            shop_name.setTypeface(tf);
            shop_activity.setTypeface(tf);
            shop_country.setTypeface(tf);
            shop_goverment.setTypeface(tf);
            shop_city.setTypeface(tf);


            //on location press
            shop_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "http://maps.google.com/maps?(" + "Home Sweet Home" + ")&daddr=" + mShop_properties.get(getAdapterPosition()).getShop_lat() + "," + mShop_properties.get(getAdapterPosition()).getShop_log() + "&z=16 (" + "Where the party is at" + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setPackage("com.google.android.apps.maps");
                    mContext.startActivity(intent);
                }
            });
            //on offer press
            shop_offers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final String shop_id=mShop_properties.get(getAdapterPosition()).getShop_id();
                    final String shop_name1=mShop_properties.get(getAdapterPosition()).getShop_name();
                    final String shop_country1=mShop_properties.get(getAdapterPosition()).getShop_country();
                    final String shop_address1=mShop_properties.get(getAdapterPosition()).getShop_address();
                    final String shop_description1=mShop_properties.get(getAdapterPosition()).getShop_description();
                    final String shop_user_id=mShop_properties.get(getAdapterPosition()).getShop_user_id();
                    final String shop_photo1=mShop_properties.get(getAdapterPosition()).getShop_photo();
                    final String shop_activity1=mShop_properties.get(getAdapterPosition()).getShop_activity();
                    final String shop_lat=mShop_properties.get(getAdapterPosition()).getShop_lat();
                    final String shop_log=mShop_properties.get(getAdapterPosition()).getShop_log();
                    final String shop_allowed_product=mShop_properties.get(getAdapterPosition()).getShop_allowed_product();
                    final String shop_allowed_offers=mShop_properties.get(getAdapterPosition()).getShop_allowed_offers();
                    final String shop_goverment=mShop_properties.get(getAdapterPosition()).getShop_goverment();
                    final String shop_city1=mShop_properties.get(getAdapterPosition()).getShop_city();
                    final String activity="list_of_shops";




                    Intent offers_intent=new Intent(mContext, List_Of_Offers.class);
                    offers_intent.putExtra("Shop_id_offers",shop_id);
                    offers_intent.putExtra("shop_id",shop_id);
                    offers_intent.putExtra("shop_name",shop_name1);
                    offers_intent.putExtra("shop_country",shop_country1);
                    offers_intent.putExtra("shop_address",shop_address1);
                    offers_intent.putExtra("shop_description",shop_description1);
                    offers_intent.putExtra("shop_user_id",shop_user_id);
                    offers_intent.putExtra("shop_photo",shop_photo1);
                    offers_intent.putExtra("shop_activity",shop_activity1);
                    offers_intent.putExtra("shop_lat",shop_lat);
                    offers_intent.putExtra("shop_log",shop_log);
                    offers_intent.putExtra("shop_allowed_product",shop_allowed_product);
                    offers_intent.putExtra("shop_allowed_offers",shop_allowed_offers);
                    offers_intent.putExtra("shop_goverment",shop_goverment);
                    offers_intent.putExtra("shop_city",shop_city1);
                    offers_intent.putExtra("main_page",activity);
                    mContext.startActivity(offers_intent);
                }
            });
            //on products press
            shop_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent products_intent=new Intent(mContext, product_details_list.class);
                    products_intent.putExtra("Shop_id_products",mShop_properties.get(getAdapterPosition()).getShop_id());
                    mContext.startActivity(products_intent);
                }
            });
            //on click  listener for the list
            View_shop_info_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(mContext, Shop_details.class);
                    i.putExtra("shop_id",mShop_properties.get(getAdapterPosition()).getShop_id());
                    i.putExtra("shop_name",mShop_properties.get(getAdapterPosition()).getShop_name());
                    i.putExtra("shop_country",mShop_properties.get(getAdapterPosition()).getShop_country());
                    i.putExtra("shop_address",mShop_properties.get(getAdapterPosition()).getShop_address());
                    i.putExtra("shop_description",mShop_properties.get(getAdapterPosition()).getShop_description());
                    i.putExtra("shop_user_id",mShop_properties.get(getAdapterPosition()).getShop_user_id());
                    i.putExtra("shop_photo",mShop_properties.get(getAdapterPosition()).getShop_photo());
                    i.putExtra("shop_activity",mShop_properties.get(getAdapterPosition()).getShop_activity());
                    i.putExtra("shop_lat",mShop_properties.get(getAdapterPosition()).getShop_lat());
                    i.putExtra("shop_log",mShop_properties.get(getAdapterPosition()).getShop_log());
                    i.putExtra("shop_allowed_product",mShop_properties.get(getAdapterPosition()).getShop_allowed_product());
                    i.putExtra("shop_allowed_offers",mShop_properties.get(getAdapterPosition()).getShop_allowed_offers());
                    i.putExtra("shop_goverment",mShop_properties.get(getAdapterPosition()).getShop_goverment());
                    i.putExtra("shop_city",mShop_properties.get(getAdapterPosition()).getShop_city());
                    i.putExtra("main_page","list_of_shops");


                    mContext.startActivity(i);
                }
            });


        }
    }
}
