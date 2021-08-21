package com.example.ahmad_elbayadi.ma7laty.Fragmaents;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_Of_Product_Text_Adapter;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_Product_Text_property;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product_list extends Fragment {
    private static final String TAG = "Tab2_Fragment";

    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/text_product_list.php";
    private RequestQueue requestQueue_products;
    private RecyclerView recyclerView_list_of_products;
    private List<List_Of_Product_Text_property> product_list;
    private TextView data_text,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private RelativeLayout data_layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.product_list,container,false);



        recyclerView_list_of_products=view.findViewById(R.id.recycle_products15);
        product_list=new ArrayList<>();

        data_text=(TextView)view.findViewById(R.id.data_text_list_of_shop_pro1215);

        internet_connection_text=(TextView)view.findViewById(R.id.connection_text_list_of_shop_pro1215);
        retry_btn=(Button)view.findViewById(R.id.retry_list_of_shop_pro1215);
        internet_layout=(RelativeLayout) view.findViewById(R.id.list_of_shop_internet_connection_layout_pro1215);
        data_layout=(RelativeLayout)view.findViewById(R.id.list_of_product_data_null15);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar

        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);


        //request json
        jsonRequest_produsts_list();


        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_produsts_list();
            }
        });


        return view;
    }


    private void jsonRequest_produsts_list() {
        //receive data
        final String Shop_id=getActivity().getIntent().getExtras().getString("Shop_id_products");
        Log.i("test_shop_id",Shop_id+"");

        StringRequest request_products=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                JSONObject jsonObject3 = null;
                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String products_photos[]=null;
                String string_photos;
                for (int i = 0; i < jsonObject.length(); i++)
                {
                    try{

                        jsonObject2=jsonObject.getJSONObject(i+"");

                        List_Of_Product_Text_property product_model=new List_Of_Product_Text_property();

                        product_model.setProduct_id(jsonObject2.getString("product_id"));
                        product_model.setShop_id(jsonObject2.getString("shop_id"));
                        product_model.setProduct_name(jsonObject2.getString("product_name"));
                        product_model.setProduct_price(jsonObject2.getString("product_price"));





                        product_list.add(product_model);
                    }catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }



                SettingUpRecycleView(product_list);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_product_list");
                params.put("shop_id", Shop_id);

                return params;
            }
        };



        requestQueue_products= Volley.newRequestQueue(getContext());
        requestQueue_products.add(request_products);

    }


    private void SettingUpRecycleView(List<List_Of_Product_Text_property> product_list) {
        List_Of_Product_Text_Adapter mRecycleViewAdapter=new List_Of_Product_Text_Adapter(getContext(),product_list);
        recyclerView_list_of_products.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_list_of_products.setAdapter(mRecycleViewAdapter);
    }






}
