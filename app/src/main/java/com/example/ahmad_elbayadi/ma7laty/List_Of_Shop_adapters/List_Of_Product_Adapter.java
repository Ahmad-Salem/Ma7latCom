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
import com.example.ahmad_elbayadi.ma7laty.activities.Product_details;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_Product_property;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 27/04/2018.
 */

public class List_Of_Product_Adapter extends  RecyclerView.Adapter<List_Of_Product_Adapter.MyViewHolder>{

    private Context mContext;
    private List<List_Of_Product_property> mList_of_product_properties;
    RequestOptions option;

    public List_Of_Product_Adapter(Context mContext, List<List_Of_Product_property> mList_of_product_properties) {
        this.mContext = mContext;
        this.mList_of_product_properties = mList_of_product_properties;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.product_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

                return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.product_name.setText(mList_of_product_properties.get(position).getProduct_name());
        holder.product_description.setText(mList_of_product_properties.get(position).getProduct_description());
        holder.product_price.setText(mList_of_product_properties.get(position).getProduct_price());
//        String product_photos=mList_of_product_properties.get(position).getProduct_rest_photos();
        try{
//            String [] product_photos_array=product_photos.split(",");
            //loading image from the internet and set it into imageview using Glide
            Glide.with(mContext).load(mList_of_product_properties.get(position).getProduct_main_photo()).apply(option).into(holder.product_photo);

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
            product_name=itemView.findViewById(R.id.product_list_title1);
            product_description=itemView.findViewById(R.id.product_list_description);
            product_price=itemView.findViewById(R.id.product_list_price);
            product_photo=itemView.findViewById(R.id.product_list_photo);
            mLinearLayout=itemView.findViewById(R.id.product_item_container);
            product_list_price_coin=itemView.findViewById(R.id.product_list_price_coin);
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
                    Intent i = new Intent(mContext, Product_details.class);
                    i.putExtra("product_id", mList_of_product_properties.get(getAdapterPosition()).getProduct_id());
                    i.putExtra("shop_id", mList_of_product_properties.get(getAdapterPosition()).getShop_id());
                    i.putExtra("product_price", mList_of_product_properties.get(getAdapterPosition()).getProduct_price());
                    i.putExtra("product_name", mList_of_product_properties.get(getAdapterPosition()).getProduct_name());
                    i.putExtra("product_description", mList_of_product_properties.get(getAdapterPosition()).getProduct_description());
                    i.putExtra("user_name", mList_of_product_properties.get(getAdapterPosition()).getUser_name());
                    i.putExtra("email", mList_of_product_properties.get(getAdapterPosition()).getEmail());
                    i.putExtra("photo", mList_of_product_properties.get(getAdapterPosition()).getPhoto());
                    i.putExtra("address", mList_of_product_properties.get(getAdapterPosition()).getAddress());
                    i.putExtra("telephone1", mList_of_product_properties.get(getAdapterPosition()).getTelephone1());
                    i.putExtra("telephone2", mList_of_product_properties.get(getAdapterPosition()).getTelephone2());
                    i.putExtra("product_main_photo", mList_of_product_properties.get(getAdapterPosition()).getProduct_main_photo());

                    mContext.startActivity(i);

                }
            });


        }
    }
}

