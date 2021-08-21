package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.activities.Shop_details;
import com.example.ahmad_elbayadi.ma7laty.models.List_of_All_offers_property;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 02/09/2018.
 */

public class List_of_All_Offers_Adapter extends  RecyclerView.Adapter<List_of_All_Offers_Adapter.MyViewHolder>{


    private Context mContext;
    private List<List_of_All_offers_property> mList_of_offer_properties;
    RequestOptions option;
    private Dialog Discover_shop_dialog2;
    private ImageView offer_photo_paid;


    public List_of_All_Offers_Adapter(Context mContext, List<List_of_All_offers_property> mList_of_offer_properties) {
        this.mContext = mContext;
        this.mList_of_offer_properties = mList_of_offer_properties;
        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.photo_startup1);
    }



    public void pop_offer(int index)
    {

        TextView close;
        TextView visit_shop_tv;
        Button visit_shop;
        Discover_shop_dialog2.setContentView(R.layout.paid_offer_pop_up);
//      Picasso.get().load(IMG_URL).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(advertisement_image_zoom);
        // Font path
        String fontPath = "beinarnormal.ttf";

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(mContext.getResources().getAssets(), fontPath);



        close = (TextView) Discover_shop_dialog2.findViewById(R.id.close_btn_advertisement_paid_offer);
        visit_shop = (Button) Discover_shop_dialog2.findViewById(R.id.visite_shop_paid_offer);
        visit_shop_tv = (TextView) Discover_shop_dialog2.findViewById(R.id.adver_title_paid_offer);

        visit_shop.setTypeface(tf);
        visit_shop_tv.setTypeface(tf);
        
        if(mList_of_offer_properties.get(index).getOffer_shop_id().equals(0))
        {
            offer_photo_paid.setVisibility(View.GONE);
        }
        visit_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog2.dismiss();
            }
        });

        //show Dialog
        Discover_shop_dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog2.show();




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.all_offer_item_container, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.offer_name.setText(mList_of_offer_properties.get(position).getOffer_name());
        holder.offer_description.setText(mList_of_offer_properties.get(position).getOffer_description());


        //loading image from the internet and set it into imageview using Glide
        Glide.with(mContext).load(mList_of_offer_properties.get(position).getOffer_image_url()).apply(option).into(holder.offer_photo);

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
            offer_name=itemView.findViewById(R.id.offer_list_title_all);
            offer_description=itemView.findViewById(R.id.offer_list_description_all);
            offer_photo=itemView.findViewById(R.id.offer_list_photo_all);
            offer_photo_paid=itemView.findViewById(R.id.paid_offer_list);
            containLinearLayout=itemView.findViewById(R.id.offer_item_container_all);

            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);

            offer_name.setTypeface(tf);
            offer_description.setTypeface(tf);

            //on click  listener for the list
            containLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Discover_shop_dialog2=new Dialog(mContext);

//                    pop_offer(getAdapterPosition());
                    Intent Main=new Intent(mContext,Shop_details.class);
                    Main.putExtra("shop_id",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_id());
                    Main.putExtra("shop_name",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_name());
                    Main.putExtra("shop_country",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_country());
                    Main.putExtra("shop_address",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_address());
                    Main.putExtra("shop_description",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_description());
                    Main.putExtra("shop_user_id",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_user_id());
                    Main.putExtra("shop_photo",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_photo());
                    Main.putExtra("shop_activity",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_shop_activity());
                    Main.putExtra("shop_lat",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_lat());
                    Main.putExtra("shop_log",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_log());
                    Main.putExtra("shop_allowed_product",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_allowed_products());
                    Main.putExtra("shop_allowed_offers",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_allowed_offers());
                    Main.putExtra("shop_goverment",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_gov());
                    Main.putExtra("shop_city",mList_of_offer_properties.get(getAdapterPosition()).getOffer_shop_city());
                    Main.putExtra("main_page","all_offers");
                    Discover_shop_dialog2.dismiss();
                    mContext.startActivity(Main);

                }
            });



        }
    }
}
