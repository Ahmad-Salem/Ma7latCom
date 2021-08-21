package com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_Product_Text_property;

import java.util.List;

public class List_Of_Product_Text_Adapter extends  RecyclerView.Adapter<List_Of_Product_Text_Adapter.MyViewHolder>{

    private Context mContext;
    private List<List_Of_Product_Text_property> mList_of_product_properties;

    public List_Of_Product_Text_Adapter(Context mContext, List<List_Of_Product_Text_property> mList_of_product_properties) {
        this.mContext = mContext;
        this.mList_of_product_properties = mList_of_product_properties;
    }


    @NonNull
    @Override
    public List_Of_Product_Text_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.product_list_text_item, parent, false);
        List_Of_Product_Text_Adapter.MyViewHolder viewHolder = new List_Of_Product_Text_Adapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_name.setText(mList_of_product_properties.get(position).getProduct_name());
        holder.product_price.setText(mList_of_product_properties.get(position).getProduct_price());
    }

    @Override
    public int getItemCount() {
        return mList_of_product_properties.size();
    }

 class MyViewHolder extends RecyclerView.ViewHolder{
        TextView product_name;
        TextView product_price;
        TextView placeholder1;
        TextView placeholder2;
        RelativeLayout mRelativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_list_title2);
            product_price=itemView.findViewById(R.id.product_list_title3);
            placeholder1=itemView.findViewById(R.id.product_list_title1);
            placeholder2=itemView.findViewById(R.id.product_list_title4);
            mRelativeLayout=itemView.findViewById(R.id.product_item_container1254);

            // Font path
            String fontPath = "beinarnormal.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);
            product_name.setTypeface(tf);
            product_price.setTypeface(tf);
            placeholder1.setTypeface(tf);
            placeholder2.setTypeface(tf);


        }
    }
}
