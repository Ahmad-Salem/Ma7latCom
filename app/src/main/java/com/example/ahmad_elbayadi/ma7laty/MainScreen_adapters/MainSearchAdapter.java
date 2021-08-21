package com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.activities.Offer_Details;
import com.example.ahmad_elbayadi.ma7laty.activities.Product_details;
import com.example.ahmad_elbayadi.ma7laty.activities.Shop_details;
import com.example.ahmad_elbayadi.ma7laty.models.ShopSearch;

import java.util.List;

public class MainSearchAdapter extends  RecyclerView.Adapter<MainSearchAdapter.MyViewHolder> {

    private Context mContext;
    private List<ShopSearch> mShop_properties;
    RequestOptions option;


    public MainSearchAdapter(Context mContext,List<ShopSearch> mShop_properties)
    {
        this.mContext=mContext;
        this.mShop_properties=mShop_properties;

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);

    }




    @NonNull
    @Override
    public MainSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.shop_search_item, parent, false);
        final MainSearchAdapter.MyViewHolder viewHolder = new MainSearchAdapter.MyViewHolder(view);


            /*****************************************************************************************/
            /*****************************************************************************************/
            //on click  listener for the list
            viewHolder.View_shop_info_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mShop_properties.get(viewHolder.getAdapterPosition()).getType().equals("shop"))
                    {
                        Intent i=new Intent(mContext, Shop_details.class);
                        i.putExtra("shop_id",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_id());
                        i.putExtra("shop_name",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_name());
                        i.putExtra("shop_country",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_country());
                        i.putExtra("shop_address",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_address());
                        i.putExtra("shop_description",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_description());
                        i.putExtra("shop_user_id",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_user_id());
                        i.putExtra("shop_photo",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_photo());
                        i.putExtra("shop_activity",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_shop_activity());
                        i.putExtra("shop_lat",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_lat());
                        i.putExtra("shop_log",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_log());
                        i.putExtra("shop_allowed_product",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_allowed_product());
                        i.putExtra("shop_allowed_offers",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_allowed_offers());
                        i.putExtra("shop_goverment",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_government());
                        i.putExtra("shop_city",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_city());
                        i.putExtra("main_page","list_of_shops");
                        mContext.startActivity(i);

                    }else if(mShop_properties.get(viewHolder.getAdapterPosition()).getType().equals("offer"))
                    {
                        Intent i=new Intent(mContext, Offer_Details.class);
                        i.putExtra("offer_id",mShop_properties.get(viewHolder.getAdapterPosition()).getOffer_id());
                        i.putExtra("offer_name",mShop_properties.get(viewHolder.getAdapterPosition()).getOffer_name());
                        i.putExtra("offer_description",mShop_properties.get(viewHolder.getAdapterPosition()).getOffer_description());
                        i.putExtra("offer_photo",mShop_properties.get(viewHolder.getAdapterPosition()).getOffer_photo());
                        i.putExtra("shop_owner_user_name",mShop_properties.get(viewHolder.getAdapterPosition()).getUser_name());
                        i.putExtra("shop_owner_email",mShop_properties.get(viewHolder.getAdapterPosition()).getEmail());
                        i.putExtra("shop_owner_photo",mShop_properties.get(viewHolder.getAdapterPosition()).getPhoto());
                        i.putExtra("shop_owner_address",mShop_properties.get(viewHolder.getAdapterPosition()).getAddress());
                        i.putExtra("shop_owner_telephone1",mShop_properties.get(viewHolder.getAdapterPosition()).getTelephone1());
                        i.putExtra("shop_owner_telephone2",mShop_properties.get(viewHolder.getAdapterPosition()).getTelephone2());
                        i.putExtra("shop_id",mShop_properties.get(viewHolder.getAdapterPosition()).getShop_id());

                        mContext.startActivity(i);

                    }else if(mShop_properties.get(viewHolder.getAdapterPosition()).getType().equals("product"))
                    {
                        Intent i = new Intent(mContext, Product_details.class);
                        i.putExtra("product_id", mShop_properties.get(viewHolder.getAdapterPosition()).getProduct_id());
                        i.putExtra("product_price", mShop_properties.get(viewHolder.getAdapterPosition()).getProduct_price());
                        i.putExtra("product_name", mShop_properties.get(viewHolder.getAdapterPosition()).getProduct_name());
                        i.putExtra("product_description", mShop_properties.get(viewHolder.getAdapterPosition()).getProduct_description());
                        i.putExtra("user_name", mShop_properties.get(viewHolder.getAdapterPosition()).getUser_name());
                        i.putExtra("email", mShop_properties.get(viewHolder.getAdapterPosition()).getEmail());
                        i.putExtra("photo", mShop_properties.get(viewHolder.getAdapterPosition()).getPhoto());
                        i.putExtra("address", mShop_properties.get(viewHolder.getAdapterPosition()).getAddress());
                        i.putExtra("telephone1", mShop_properties.get(viewHolder.getAdapterPosition()).getTelephone1());
                        i.putExtra("telephone2", mShop_properties.get(viewHolder.getAdapterPosition()).getTelephone2());
                        i.putExtra("product_main_photo", mShop_properties.get(viewHolder.getAdapterPosition()).getProduct_photo());
                        i.putExtra("shop_id", mShop_properties.get(viewHolder.getAdapterPosition()).getShop_id());

                        mContext.startActivity(i);
                    }

                }
            });
            /*****************************************************************************************/
            /*****************************************************************************************/




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainSearchAdapter.MyViewHolder holder, int position) {

        if(mShop_properties.get(position).getType().equals("shop"))
        {
            holder.shop_name.setText(mShop_properties.get(position).getShop_name());
            holder.shop_description.setText(mShop_properties.get(position).getShop_description());
            //loading image from the internet and set it into imageview using Glide
            Glide.with(mContext).load(mShop_properties.get(position).getShop_photo()).apply(option).into(holder.shop_photo);
            holder.shop_photo_logo.setImageResource(R.drawable.shop);


        }else if(mShop_properties.get(position).getType().equals("offer"))
        {
            holder.shop_name.setText(mShop_properties.get(position).getOffer_name());
            holder.shop_description.setText(mShop_properties.get(position).getOffer_description());
            //loading image from the internet and set it into imageview using Glide
            Glide.with(mContext).load(mShop_properties.get(position).getOffer_photo()).apply(option).into(holder.shop_photo);
            holder.shop_photo_logo.setImageResource(R.drawable.offer);
        }else if(mShop_properties.get(position).getType().equals("product"))
        {
            holder.shop_name.setText(mShop_properties.get(position).getProduct_name());
            holder.shop_description.setText(mShop_properties.get(position).getProduct_description());
            //loading image from the internet and set it into imageview using Glide
            Glide.with(mContext).load(mShop_properties.get(position).getProduct_photo()).apply(option).into(holder.shop_photo);
            holder.shop_photo_logo.setImageResource(R.drawable.product);
        }

    }

    @Override
    public int getItemCount() {
//        Toast.makeText(mContext,""+mShop_properties.size(),Toast.LENGTH_LONG).show();
        return mShop_properties.size();
    }

    public void clear() {
        // TODO Auto-generated method stub
        mShop_properties.clear();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shop_name;
        TextView shop_description;
        ImageView shop_photo;
        ImageView shop_photo_logo;

        LinearLayout View_shop_info_container;


        public MyViewHolder(View itemView) {
            super(itemView);
            //casting android component in the adapter
            shop_name=itemView.findViewById(R.id.product_list_title1);
            shop_description=itemView.findViewById(R.id.product_list_description);
            shop_photo=itemView.findViewById(R.id.product_list_photo);
            shop_photo_logo=itemView.findViewById(R.id.photo_logo);
            View_shop_info_container=itemView.findViewById(R.id.product_item_container);


            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);


            shop_name.setTypeface(tf);
            shop_description.setTypeface(tf);





        }
    }
}
