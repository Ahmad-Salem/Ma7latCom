package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_Of_Offers_Adapter;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.List_Of_offer_property;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_Of_Offers extends AppCompatActivity {
    private TextView toolbartitle;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/offer_list.php";
    private RequestQueue requestQueue_offers;
    private RecyclerView recyclerView_list_of_offers;
    private List<List_Of_offer_property> Offer_list;
    private TextView data_text,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private RelativeLayout data_layout;
    private static String shop_id, shop_name,shop_country,shop_address,shop_description,shop_user_id,shop_photo,shop_activity;
    private static String shop_lat,shop_log,shop_allowed_product,shop_allowed_offers,shop_goverment,shop_city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__of__offers);

        //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.offer_list_activity_toolbar);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION", Context.MODE_PRIVATE);

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

                Intent Main=new Intent(getApplicationContext(),Shop_details.class);

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

        recyclerView_list_of_offers=findViewById(R.id.recycle_offers);
        Offer_list=new ArrayList<>();



        //casting android screen component
        toolbartitle=(TextView)findViewById(R.id.toolbar_title_offer_list);
        data_text=(TextView)findViewById(R.id.data_text_list_of_shop_offer12);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_list_of_shop_offer12);
        retry_btn=(Button)findViewById(R.id.retry_list_of_shop_offer12);
        internet_layout=(RelativeLayout) findViewById(R.id.list_of_shop_internet_connection_layout_offer12);
        data_layout=(RelativeLayout) findViewById(R.id.list_of_offer_data_null);

        //setting values
        shop_id = getIntent().getExtras().getString("shop_id");
        shop_name=getIntent().getExtras().getString("shop_id");
        shop_country=getIntent().getExtras().getString("shop_id");
        shop_address=getIntent().getExtras().getString("shop_id");
        shop_description=getIntent().getExtras().getString("shop_id");
        shop_user_id=getIntent().getExtras().getString("shop_id");
        shop_photo=getIntent().getExtras().getString("shop_id");
        shop_activity=getIntent().getExtras().getString("shop_id");
        shop_lat=getIntent().getExtras().getString("shop_id");
        shop_log=getIntent().getExtras().getString("shop_id");
        shop_allowed_product=getIntent().getExtras().getString("shop_id");
        shop_allowed_offers=getIntent().getExtras().getString("shop_id");
        shop_goverment=getIntent().getExtras().getString("shop_id");
        shop_city=getIntent().getExtras().getString("shop_id");




        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar
        toolbartitle.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);



        //request json
        jsonRequest_shops_list();

        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_shops_list();
            }
        });

    }


    //get advertisements using volley

    private void jsonRequest_shops_list() {

        //receive data
        final String Shop_id=getIntent().getExtras().getString("Shop_id_offers");


        StringRequest request_offers=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
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


                for (int i = 0; i < jsonObject.length(); i++)
                {
                    try{

                        jsonObject2=jsonObject.getJSONObject(i+"");

                        List_Of_offer_property offer_model=new List_Of_offer_property();

                        offer_model.setOffer_id(jsonObject2.getString("offer_id"));
                        offer_model.setOffer_name(jsonObject2.getString("offer_name"));
                        offer_model.setOffer_description(jsonObject2.getString("offer_description"));
                        offer_model.setOffer_photo(jsonObject2.getString("offer_photo"));
                        offer_model.setShop_owner_user_name(jsonObject2.getString("user_name"));
                        offer_model.setShop_owner_email(jsonObject2.getString("email"));
                        offer_model.setShop_owner_photo(jsonObject2.getString("photo"));
                        offer_model.setShop_owner_address(jsonObject2.getString("address"));
                        offer_model.setShop_owner_telephone1(jsonObject2.getString("telephone1"));
                        offer_model.setShop_owner_telephone2(jsonObject2.getString("telephone2"));
                        offer_model.setShop_id(Shop_id);

                        Log.i("gdasdasda54545",jsonObject2.getString("offer_photo")+",");
                        Offer_list.add(offer_model);
                    }catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }



                SettingUpRecycleView(Offer_list);
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
                params.put("do_action", "get_offers_list");
                params.put("shop_id", Shop_id);

                return params;
            }
        };



        requestQueue_offers= Volley.newRequestQueue(this);
        requestQueue_offers.add(request_offers);
    }



    private void SettingUpRecycleView(List<List_Of_offer_property> Offer_list) {
        List_Of_Offers_Adapter mRecycleViewAdapter=new List_Of_Offers_Adapter(this,Offer_list);
        recyclerView_list_of_offers.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_list_of_offers.setAdapter(mRecycleViewAdapter);
    }


}
