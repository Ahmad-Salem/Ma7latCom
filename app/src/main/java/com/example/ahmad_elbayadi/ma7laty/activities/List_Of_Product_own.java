package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_Of_Product_Adapter_own;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_Product_property_own;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_Of_Product_own extends AppCompatActivity {

    private TextView toolbartitle;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/dispaly_product.php";
    private RequestQueue requestQueue_products;
    private RecyclerView recyclerView_list_of_products;
    private List<List_Of_Product_property_own> product_list;
    private String  Shop_id;
    private TextView data_text,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private RelativeLayout data_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__of__product_own);

        //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.product_list_activity_toolbar22);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION_OWN", Context.MODE_PRIVATE);

                final String shop_id=editor_shop_details.getString("shop_id", "");
                final String shop_name1=editor_shop_details.getString("shop_name1", "");
                final String shop_country1=editor_shop_details.getString("shop_country1", "");
                final String shop_address1=editor_shop_details.getString("shop_address1", "");
                final String shop_description1=editor_shop_details.getString("shop_description1", "");
                final String shop_user_id=editor_shop_details.getString("shop_user_id", "");
                final String shop_photo1=editor_shop_details.getString("shop_photo1", "");
                final String shop_activity1=editor_shop_details.getString("shop_activity1", "");
                final String shop_lat=editor_shop_details.getString("shop_lat", "");
                final String shop_log=editor_shop_details.getString("shop_log", "");
                final String shop_allowed_product=editor_shop_details.getString("shop_allowed_product", "");
                final String shop_allowed_offers=editor_shop_details.getString("shop_allowed_offers", "");
                final String shop_goverment=editor_shop_details.getString("shop_goverment", "");
                final String shop_city1=editor_shop_details.getString("shop_city1", "");

                Intent Main=new Intent(getApplicationContext(),Shop_details_own.class);

                Main.putExtra("shop_id",shop_id);
                Main.putExtra("shop_name",shop_name1);
                Main.putExtra("shop_country",shop_country1);
                Main.putExtra("shop_address",shop_address1);
                Main.putExtra("shop_description",shop_description1);
                Main.putExtra("shop_user_id",shop_user_id);
                Main.putExtra("shop_photo",shop_photo1);
                Main.putExtra("shop_activity",shop_activity1);
                Main.putExtra("shop_lat",shop_lat);
                Main.putExtra("shop_log",shop_log);
                Main.putExtra("shop_allowed_product",shop_allowed_product);
                Main.putExtra("shop_allowed_offers",shop_allowed_offers);
                Main.putExtra("shop_goverment",shop_goverment);
                Main.putExtra("shop_city",shop_city1);
                Main.putExtra("main_page","none");
                startActivity(Main);

            }
        });


        Shop_id=getIntent().getExtras().getString("Shop_id_products");
        recyclerView_list_of_products=findViewById(R.id.recycle_products22);
        product_list=new ArrayList<>();


        //casting android screen component
        toolbartitle=(TextView)findViewById(R.id.toolbar_title_product_list22);
        data_text=(TextView)findViewById(R.id.data_text_list_of_shop_pro12_own);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_list_of_shop_pro12_own);
        retry_btn=(Button)findViewById(R.id.retry_list_of_shop_pro12_own);
        internet_layout=(RelativeLayout) findViewById(R.id.list_of_shop_internet_connection_layout_pro12_own);
        data_layout=(RelativeLayout) findViewById(R.id.list_of_product_data_null_own);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar
        toolbartitle.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);


        jsonRequest_produsts_list();

        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_produsts_list();
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_add_product2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //add offer
                Intent add_product=new Intent(getApplicationContext(),Add_product_own.class);
                add_product.putExtra("shop_id",Shop_id);
                startActivity(add_product);
            }
        });


    }


    private void jsonRequest_produsts_list() {
        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");


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

                        List_Of_Product_property_own product_model=new List_Of_Product_property_own();

                        product_model.setProduct_id(jsonObject2.getString("product_id"));
                        product_model.setProduct_price(jsonObject2.getString("product_price"));
                        product_model.setProduct_name(jsonObject2.getString("product_name"));
                        product_model.setProduct_description(jsonObject2.getString("product_description"));
                        product_model.setShop_id(jsonObject2.getString("shop_id"));
                        product_model.setProduct_main_photo(jsonObject2.getString("product_main_photo"));




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
                params.put("do_action", "display_products");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", Shop_id);

                return params;
            }
        };



        requestQueue_products= Volley.newRequestQueue(this);
        requestQueue_products.add(request_products);

    }


    private void SettingUpRecycleView(List<List_Of_Product_property_own> product_list) {
        List_Of_Product_Adapter_own mRecycleViewAdapter=new List_Of_Product_Adapter_own(this,product_list);
        recyclerView_list_of_products.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_list_of_products.setAdapter(mRecycleViewAdapter);
    }
}
