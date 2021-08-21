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
import com.example.ahmad_elbayadi.ma7laty.activities.Product_details_own;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_Product_property_own;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 05/06/2018.
 */

public class List_Of_Product_Adapter_own extends  RecyclerView.Adapter<List_Of_Product_Adapter_own.MyViewHolder>{
    private Context mContext;
    private List<List_Of_Product_property_own> mList_of_product_properties;
    RequestOptions option;

    public List_Of_Product_Adapter_own(Context mContext, List<List_Of_Product_property_own> mList_of_product_properties) {
        this.mContext = mContext;
        this.mList_of_product_properties = mList_of_product_properties;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);

    }

    @NonNull
    @Override
    public List_Of_Product_Adapter_own.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.product_list_item_own, parent, false);
        List_Of_Product_Adapter_own.MyViewHolder viewHolder = new List_Of_Product_Adapter_own.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.product_name.setText(mList_of_product_properties.get(position).getProduct_name());
        holder.product_description.setText(mList_of_product_properties.get(position).getProduct_description());
        holder.product_price.setText(mList_of_product_properties.get(position).getProduct_price());
        String product_photos=mList_of_product_properties.get(position).getProduct_main_photo();
        try{
//            String [] product_photos_array=product_photos.split(",");
            //loading image from the internet and set it into imageview using Glide
            Glide.with(mContext).load(product_photos).apply(option).into(holder.product_photo);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mList_of_product_properties.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        TextView product_description;
        TextView product_price,product_list_price_coin;
        ImageView product_photo;
        LinearLayout mLinearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_list_title122);
            product_description=itemView.findViewById(R.id.product_list_description22);
            product_price=itemView.findViewById(R.id.product_list_price22);
            product_photo=itemView.findViewById(R.id.product_list_photo22);
            mLinearLayout=itemView.findViewById(R.id.product_item_container22);
            product_list_price_coin=itemView.findViewById(R.id.product_list_price_coin_own);
            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);

            product_name.setTypeface(tf);
            product_description.setTypeface(tf);
            product_price.setTypeface(tf);
            product_list_price_coin.setTypeface(tf);

            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, Product_details_own.class);
                    i.putExtra("product_id", mList_of_product_properties.get(getAdapterPosition()).getProduct_id());
                    i.putExtra("product_price", mList_of_product_properties.get(getAdapterPosition()).getProduct_price());
                    i.putExtra("product_name", mList_of_product_properties.get(getAdapterPosition()).getProduct_name());
                    i.putExtra("product_description", mList_of_product_properties.get(getAdapterPosition()).getProduct_description());
                    i.putExtra("product_main_photo", mList_of_product_properties.get(getAdapterPosition()).getProduct_main_photo());
                    i.putExtra("shop_id", mList_of_product_properties.get(getAdapterPosition()).getShop_id());

                    mContext.startActivity(i);
                }
            });



        }
    }

}
