package com.example.ahmad_elbayadi.ma7laty.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_of_All_Offers_Adapter;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.List_of_All_offers_property;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_Offers_Main_List extends AppCompatActivity {

    private TextView toolbar;
    private RecyclerView recyclerView_list_of_offers;
    private List<List_of_All_offers_property> Offer_list;

    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/important_offersV4.php";
    private RequestQueue requestQueue_offers;

    private TextView data_text,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private RelativeLayout data_layout;


    //pagination settings
    private Boolean isScrolling=false;
    private int limit=10;
    private int total_displayed=5;
    private int page=1;
    private ConstraintLayout loading;
    private List_of_All_Offers_Adapter mRecycleViewAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int page_number=1;
    private int item_count=10;

    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previousTotal=0;
    private int view_threshold=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__offers__main__list);
//        Toast.makeText(getApplicationContext(),"any",Toast.LENGTH_LONG).show();

        recyclerView_list_of_offers=findViewById(R.id.recycle_all_offers);
        Offer_list=new ArrayList<>();

        //calling json
        jsonRequest_all_offers_list();

        Toolbar toolbar1 = (Toolbar)findViewById(R.id.offer_main_list_activity_toolbar1_more);
        setSupportActionBar(toolbar1);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);



        //casting new android component
        toolbar=(TextView)findViewById(R.id.offer_main_list_toolbar_title);
        data_text=(TextView)findViewById(R.id.data_text_list_of_shop_offer12_all_offers);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_list_of_shop_offer12_all_offers);
        retry_btn=(Button)findViewById(R.id.retry_list_of_shop_offer12_all_offers);
        internet_layout=(RelativeLayout) findViewById(R.id.list_of_shop_internet_connection_layout_offer12_all_offers);
        data_layout=(RelativeLayout) findViewById(R.id.list_of_offer_data_null_all_offers);
        loading=(ConstraintLayout)findViewById(R.id.loading);



        //setting font face
        toolbar.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);


        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_all_offers_list();
            }
        });


        ////recyclerview
        mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView_list_of_offers.setLayoutManager(mLinearLayoutManager);
        recyclerView_list_of_offers.removeAllViews();
        recyclerView_list_of_offers.removeAllViewsInLayout();
        recyclerView_list_of_offers.setHasFixedSize(true);


        //on scroll listener
        recyclerView_list_of_offers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);




            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);





                visibleItemCount=mLinearLayoutManager.getChildCount();
                totalItemCount=mLinearLayoutManager.getItemCount();
                pastVisibleItems=mLinearLayoutManager.findFirstVisibleItemPosition();


                if(dy>0)
                {

                    if(isLoading)
                    {
                        if(totalItemCount>previousTotal)
                        {
                            isLoading=false;
                            previousTotal=totalItemCount;
                            loading.setVisibility(View.GONE);

                        }
                    }

                    if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItems+view_threshold))
                    {
                        page_number++;
                        perform_pagination();
                        isLoading= true;
//                        Toast.makeText(getApplicationContext(),"visibleItemCount"+visibleItemCount,Toast.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(),"totalItemCount"+totalItemCount,Toast.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(),"pastVisibleItems"+pastVisibleItems,Toast.LENGTH_LONG).show();

                    }
                }

            }
        });



    }




    //get advertisements using volley

    private void jsonRequest_all_offers_list() {

        //receive data
        final String goverment=getIntent().getExtras().getString("all_government");
        final String city=getIntent().getExtras().getString("all_city");


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

                try{
                    if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("okay"))
                    {
                        for (int i = 1; i < jsonObject.length(); i++)
                        {

                            jsonObject2=jsonObject.getJSONObject(i+"");

                            List_of_All_offers_property offer_model=new List_of_All_offers_property();

                            offer_model.setOffer_id(jsonObject2.getString("offer_id"));
                            offer_model.setOffer_name(jsonObject2.getString("offer_name"));
                            offer_model.setOffer_description(jsonObject2.getString("offer_description"));
                            offer_model.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                            offer_model.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                            offer_model.setOffer_shop_gov(jsonObject2.getString("offer_shop_gov"));
                            offer_model.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                            offer_model.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));
                            offer_model.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                            offer_model.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                            offer_model.setOffer_shop_description(jsonObject2.getString("offer_shop_description"));
                            offer_model.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                            offer_model.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                            offer_model.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                            offer_model.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                            offer_model.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                            offer_model.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));
                            offer_model.setOffer_image_url(jsonObject2.getString("offer_image_url"));
                            offer_model.setUser_id(jsonObject2.getString("user_id"));
                            offer_model.setUser_user_name(jsonObject2.getString("user_user_name"));
                            offer_model.setUser_email(jsonObject2.getString("user_email"));
                            offer_model.setUser_photo(jsonObject2.getString("user_photo"));
                            offer_model.setUser_telephone1(jsonObject2.getString("user_telephone1"));
                            offer_model.setUser_telephone2(jsonObject2.getString("user_telephone2"));
                            offer_model.setUser_address(jsonObject2.getString("user_address"));
                            offer_model.setOffer_main_page(jsonObject2.getString("main_page"));

                            Offer_list.add(offer_model);
                        }

                    }else if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("end"))
                    {

                        Offer_list.clear();
                        recyclerView_list_of_offers.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);

                    }


                }catch (JSONException e)
                {
                    e.printStackTrace();
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
                params.put("do_action", "get_important_shop");
                params.put("country", "مصر");
                params.put("government", goverment);
                params.put("city", city);
                params.put("limit_records", String.valueOf(limit));
                params.put("page", String.valueOf(page_number));

                return params;
            }
        };



        requestQueue_offers= Volley.newRequestQueue(this);
        requestQueue_offers.add(request_offers);
    }




/*****************************************************************************************************************/
private void SettingUpRecycleView(List<List_of_All_offers_property> Offer_list) {
    mRecycleViewAdapter=new List_of_All_Offers_Adapter(this,Offer_list);

    recyclerView_list_of_offers.setAdapter(mRecycleViewAdapter);
}
/*****************************************************************************************************************/
/*****************************************************************************************************************/
private void perform_pagination() {
    loading.setVisibility(View.VISIBLE);
    //receive data
    final String goverment=getIntent().getExtras().getString("all_government");
    final String city=getIntent().getExtras().getString("all_city");
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

            try{
                if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("okay"))
                {
                    for (int i = 1; i < jsonObject.length(); i++)
                    {

                        try {

                            jsonObject2=jsonObject.getJSONObject(i+"");

                            List_of_All_offers_property offer_model=new List_of_All_offers_property();

                            offer_model.setOffer_id(jsonObject2.getString("offer_id"));
                            offer_model.setOffer_name(jsonObject2.getString("offer_name"));
                            offer_model.setOffer_description(jsonObject2.getString("offer_description"));
                            offer_model.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                            offer_model.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                            offer_model.setOffer_shop_gov(jsonObject2.getString("offer_shop_gov"));
                            offer_model.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                            offer_model.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));
                            offer_model.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                            offer_model.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                            offer_model.setOffer_shop_description(jsonObject2.getString("offer_shop_description"));
                            offer_model.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                            offer_model.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                            offer_model.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                            offer_model.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                            offer_model.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                            offer_model.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));
                            offer_model.setOffer_image_url(jsonObject2.getString("offer_image_url"));
                            offer_model.setUser_id(jsonObject2.getString("user_id"));
                            offer_model.setUser_user_name(jsonObject2.getString("user_user_name"));
                            offer_model.setUser_email(jsonObject2.getString("user_email"));
                            offer_model.setUser_photo(jsonObject2.getString("user_photo"));
                            offer_model.setUser_telephone1(jsonObject2.getString("user_telephone1"));
                            offer_model.setUser_telephone2(jsonObject2.getString("user_telephone2"));
                            offer_model.setUser_address(jsonObject2.getString("user_address"));
                            offer_model.setOffer_main_page(jsonObject2.getString("main_page"));

                            Offer_list.add(offer_model);
                            loading.setVisibility(View.GONE);

                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                }else if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("end"))
                {

                    loading.setVisibility(View.GONE);

                }


            }catch (JSONException e)
            {
                e.printStackTrace();
            }


//            SettingUpRecycleView(Offer_list);
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
            params.put("do_action", "get_important_shop");
            params.put("country", "مصر");
            params.put("government", goverment);
            params.put("city", city);
            params.put("limit_records", String.valueOf(limit));
            params.put("page", String.valueOf(page_number));

            return params;
        }
    };



    requestQueue_offers= Volley.newRequestQueue(this);
    requestQueue_offers.add(request_offers);

}

/*****************************************************************************************************************/

}
