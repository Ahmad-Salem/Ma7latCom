package com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.activities.Shop_details;
import com.example.ahmad_elbayadi.ma7laty.models.electronics_model;

import java.util.List;

/**
 * Created by Ahmad_Elbayadi on 15/04/2018.
 */

public class ElectronicsAdapter extends RecyclerView.Adapter<ElectronicsAdapter.MyViewHolder> {
    private Context context;

    private List<electronics_model> offers_data;
    RequestOptions option;


    public ElectronicsAdapter(Context context, List<electronics_model> offers_data) {
        this.context = context;
        this.offers_data = offers_data;
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.loading_gif);
    }



    private void pop_offer( int index)
    {
        Log.i("AllShopAdapter",offers_data.get(index).getOffer_name());

        Intent Main=new Intent(context,Shop_details.class);
        Main.putExtra("shop_id",offers_data.get(index).getOffer_shop_id());
        Main.putExtra("shop_name",offers_data.get(index).getOffer_shop_name());
        Main.putExtra("shop_country",offers_data.get(index).getOffer_shop_country());
        Main.putExtra("shop_address",offers_data.get(index).getOffer_shop_address());
        Main.putExtra("shop_description",offers_data.get(index).getOffer_description());
        Main.putExtra("shop_user_id",offers_data.get(index).getOffer_shop_user_id());
        Main.putExtra("shop_photo",offers_data.get(index).getOffer_shop_photo());
        Main.putExtra("shop_activity",offers_data.get(index).getOffer_shop_shop_activity());
        Main.putExtra("shop_lat",offers_data.get(index).getOffer_shop_lat());
        Main.putExtra("shop_log",offers_data.get(index).getOffer_shop_log());
        Main.putExtra("shop_allowed_product",offers_data.get(index).getOffer_shop_allowed_products());
        Main.putExtra("shop_allowed_offers",offers_data.get(index).getOffer_shop_allowed_offers());
        Main.putExtra("shop_goverment",offers_data.get(index).getOffer_shop_government());
        Main.putExtra("shop_city",offers_data.get(index).getOffer_shop_city());

        Main.putExtra("user_id",offers_data.get(index).getOffer_User_id());
        Main.putExtra("user_name",offers_data.get(index).getOffer_User_name());
        Main.putExtra("email",offers_data.get(index).getOffer_User_email());
        Main.putExtra("photo",offers_data.get(index).getOffer_User_photo());
        Main.putExtra("telephone1",offers_data.get(index).getOffer_User_telephone1());
        Main.putExtra("telephone2",offers_data.get(index).getOffer_User_telephone2());
        Main.putExtra("address",offers_data.get(index).getOffer_User_address());
        Main.putExtra("activity","main");


        context.startActivity(Main);


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_electronics,parent,false);
        return new ElectronicsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectronicsAdapter.MyViewHolder holder,  int position) {
        try{
            Glide.with(context).load(offers_data.get(position).getOffer_shop_photo()).apply(option).into(holder.SildeImageView);
            holder.title1.setText(offers_data.get(position).getOffer_shop_name());
            holder.des1.setText(offers_data.get(position).getOffer_description());
            //on click listener


        }catch (Exception ex){
            Log.i("AllShopAdapter","empty"+offers_data.get(position).getOffer_name());
        }
    }

    @Override
    public int getItemCount() {
        return offers_data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout leftSide;
        ImageView SildeImageView;
        TextView title1;
        TextView des1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Font path
            String fontPath = "beinarnormal.ttf";

            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);


            leftSide=(RelativeLayout) itemView.findViewById(R.id.leftSide);
//        RelativeLayout rightSide=(RelativeLayout) view.findViewById(R.id.rightSide);
            SildeImageView=(ImageView) itemView.findViewById(R.id.offers_silder_id);
//            ImageView SildeImageView2=(ImageView) itemView.findViewById(R.id.offers_silder_id2);
            title1=(TextView)itemView.findViewById(R.id.offername1);
//        TextView title2=(TextView)view.findViewById(R.id.offername2);
            des1=(TextView)itemView.findViewById(R.id.description1);
//        TextView des2=(TextView)view.findViewById(R.id.description2);

            title1.setTypeface(tf);
//        title2.setTypeface(tf);
            des1.setTypeface(tf);


            leftSide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"hello All shops"+getAdapterPosition(),Toast.LENGTH_LONG).show();


                    if(offers_data.get(getAdapterPosition()).getOffer_shop_id().equals(""))
                    {
                        Toast.makeText(context,"تاكد من إتصالك بالأنترنت",Toast.LENGTH_LONG).show();
                    }else{
//                        Toast.makeText(context,"shop id: "+offers_data.get(getAdapterPosition()).getOffer_shop_id(),Toast.LENGTH_SHORT).show();

                        Intent Main=new Intent(context,Shop_details.class);
                        Main.putExtra("shop_id",offers_data.get(getAdapterPosition()).getOffer_shop_id());
                        Main.putExtra("shop_name",offers_data.get(getAdapterPosition()).getOffer_shop_name());
                        Main.putExtra("shop_country",offers_data.get(getAdapterPosition()).getOffer_shop_country());
                        Main.putExtra("shop_address",offers_data.get(getAdapterPosition()).getOffer_shop_address());
                        Main.putExtra("shop_description",offers_data.get(getAdapterPosition()).getOffer_description());
                        Main.putExtra("shop_user_id",offers_data.get(getAdapterPosition()).getOffer_shop_user_id());
                        Main.putExtra("shop_photo",offers_data.get(getAdapterPosition()).getOffer_shop_photo());
                        Main.putExtra("shop_activity",offers_data.get(getAdapterPosition()).getOffer_shop_shop_activity());
                        Main.putExtra("shop_lat",offers_data.get(getAdapterPosition()).getOffer_shop_lat());
                        Main.putExtra("shop_log",offers_data.get(getAdapterPosition()).getOffer_shop_log());
                        Main.putExtra("shop_allowed_product",offers_data.get(getAdapterPosition()).getOffer_shop_allowed_products());
                        Main.putExtra("shop_allowed_offers",offers_data.get(getAdapterPosition()).getOffer_shop_allowed_offers());
                        Main.putExtra("shop_goverment",offers_data.get(getAdapterPosition()).getOffer_shop_government());
                        Main.putExtra("shop_city",offers_data.get(getAdapterPosition()).getOffer_shop_city());

                        Main.putExtra("user_id",offers_data.get(getAdapterPosition()).getOffer_User_id());
                        Main.putExtra("user_name",offers_data.get(getAdapterPosition()).getOffer_User_name());
                        Main.putExtra("email",offers_data.get(getAdapterPosition()).getOffer_User_email());
                        Main.putExtra("photo",offers_data.get(getAdapterPosition()).getOffer_User_photo());
                        Main.putExtra("telephone1",offers_data.get(getAdapterPosition()).getOffer_User_telephone1());
                        Main.putExtra("telephone2",offers_data.get(getAdapterPosition()).getOffer_User_telephone2());
                        Main.putExtra("address",offers_data.get(getAdapterPosition()).getOffer_User_address());
                        Main.putExtra("activity","main");


                        context.startActivity(Main);
                    }


                }
            });
        }
    }


}
