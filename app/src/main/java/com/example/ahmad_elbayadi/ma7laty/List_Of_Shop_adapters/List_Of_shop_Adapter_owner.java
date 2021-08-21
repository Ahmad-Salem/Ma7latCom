package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.example.ahmad_elbayadi.ma7laty.activities.Shop_details_own;
import com.example.ahmad_elbayadi.ma7laty.models.Shop_property_own;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 04/06/2018.
 */

public class List_Of_shop_Adapter_owner extends  RecyclerView.Adapter<List_Of_shop_Adapter_owner.MyViewHolder>{
    private Context mContext;
    private List<Shop_property_own> mShop_properties;
    RequestOptions option;

    public List_Of_shop_Adapter_owner(Context mContext, List<Shop_property_own> mShop_properties) {
        this.mContext = mContext;
        this.mShop_properties = mShop_properties;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
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



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.shop_list_item_own, parent, false);
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


            View_shop_info_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(mContext, Shop_details_own.class);
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


                    mContext.startActivity(i);

                }
            });


        }
    }
}
