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
import com.example.ahmad_elbayadi.ma7laty.activities.Offer_Details;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_offer_property;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 27/04/2018.
 */

public class List_Of_Offers_Adapter extends  RecyclerView.Adapter<List_Of_Offers_Adapter.MyViewHolder>{

    private Context mContext;
    private List<List_Of_offer_property> mList_of_offer_properties;
    RequestOptions option;

    public List_Of_Offers_Adapter(Context mContext, List<List_Of_offer_property> mList_of_offer_properties) {
        this.mContext = mContext;
        this.mList_of_offer_properties = mList_of_offer_properties;
        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.offer_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.offer_name.setText(mList_of_offer_properties.get(position).getOffer_name());
        holder.offer_description.setText(mList_of_offer_properties.get(position).getOffer_description());


        //loading image from the internet and set it into imageview using Glide
        Glide.with(mContext).load(mList_of_offer_properties.get(position).getOffer_photo()).apply(option).into(holder.offer_photo);


    }

    @Override
    public int getItemCount() {
        return mList_of_offer_properties.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView offer_name;
        TextView offer_description;
        ImageView offer_photo;
        LinearLayout containLinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            offer_name=itemView.findViewById(R.id.offer_list_title);
            offer_description=itemView.findViewById(R.id.offer_list_description);
            offer_photo=itemView.findViewById(R.id.offer_list_photo);
            containLinearLayout=itemView.findViewById(R.id.offer_item_container);

            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);

            offer_name.setTypeface(tf);
            offer_description.setTypeface(tf);

            containLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(mContext, Offer_Details.class);
                    i.putExtra("offer_id",mList_of_offer_properties.get(getAdapterPosition()).getOffer_id());
                    i.putExtra("offer_name",mList_of_offer_properties.get(getAdapterPosition()).getOffer_name());
                    i.putExtra("offer_description",mList_of_offer_properties.get(getAdapterPosition()).getOffer_description());
                    i.putExtra("offer_photo",mList_of_offer_properties.get(getAdapterPosition()).getOffer_photo());
                    i.putExtra("shop_owner_email",mList_of_offer_properties.get(getAdapterPosition()).getShop_owner_email());
                    i.putExtra("shop_owner_photo",mList_of_offer_properties.get(getAdapterPosition()).getShop_owner_photo());
                    i.putExtra("shop_owner_address",mList_of_offer_properties.get(getAdapterPosition()).getShop_owner_address());
                    i.putExtra("shop_owner_telephone1",mList_of_offer_properties.get(getAdapterPosition()).getShop_owner_telephone1());
                    i.putExtra("shop_owner_telephone2",mList_of_offer_properties.get(getAdapterPosition()).getShop_owner_telephone2());
                    i.putExtra("shop_id",mList_of_offer_properties.get(getAdapterPosition()).getShop_id());

                    mContext.startActivity(i);
                }
            });



        }
    }
}
