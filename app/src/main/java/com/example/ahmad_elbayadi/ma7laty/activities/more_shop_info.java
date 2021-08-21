package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.More_user_info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class more_shop_info extends AppCompatActivity {
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/more_info/more_user_info.php";
    private RequestQueue requestQueue_more_info;
    private TextView toolbar,offer_owner,offer_owner_value,offer_email,offer_email_value,offer_address,offer_address_value;
    private TextView internet_text;
    private Button retry;
    private RelativeLayout internet_layout;
    private ScrollView data_layout;
    private ImageView person_image;
    private String telephone1,telephone2;
    private Button phone1_button,phone2_button;
    private More_user_info more_user_info;
    private RequestOptions option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_shop_info);

        //setting image option
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        //json call
        jsonRequest_user_info();

        telephone1="000 000 000";
        telephone2="000 000 000";
        Toolbar toolbar1 = (Toolbar)findViewById(R.id.offer_details_activity_toolbar1_more);
        setSupportActionBar(toolbar1);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
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

        //casting new android component
        toolbar=(TextView)findViewById(R.id.toolbar_title_more_user_info_more);
        offer_owner=(TextView)findViewById(R.id.offer_owner_name_details_pro2_more);
        offer_owner_value=(TextView)findViewById(R.id.offer_name_details2_more);
        offer_email=(TextView)findViewById(R.id.offer_owner_email_details_pro2_more);
        offer_email_value=(TextView)findViewById(R.id.offer_country_details2_more);
        offer_address=(TextView)findViewById(R.id.offer_owner_address_details_pro2_more);
        offer_address_value=(TextView)findViewById(R.id.offer_owner_address_details_more);
        internet_text=(TextView)findViewById(R.id.more_info_connection_text_main_private);
        retry=(Button) findViewById(R.id.more_info_retry_main_private);
        internet_layout=(RelativeLayout) findViewById(R.id.more_info_internet_connection_layout);
        data_layout=(ScrollView) findViewById(R.id.more_info_data_layout);
        person_image=(ImageView) findViewById(R.id.person_image_normal_info_more);
        phone1_button=(Button) findViewById(R.id.dial_number1_info);
        phone2_button=(Button) findViewById(R.id.dial_number2_info);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);





        //typeface the toolbar
        toolbar.setTypeface(tf);
        offer_owner.setTypeface(tf);
        offer_owner_value.setTypeface(tf);
        offer_email.setTypeface(tf);
        offer_email_value.setTypeface(tf);
        offer_address.setTypeface(tf);
        offer_address_value.setTypeface(tf);
        phone1_button.setTypeface(tf);
        phone2_button.setTypeface(tf);
        internet_text.setTypeface(tf);
        retry.setTypeface(tf);


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_user_info();
            }
        });


        phone1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telephone1));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"جهازك لا يدعم هذه الخاصية",Toast.LENGTH_SHORT).show();
                }

            }
        });
        phone2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent2 = new Intent(Intent.ACTION_DIAL);
                dialIntent2.setData(Uri.parse("tel:" + telephone2));
                if (dialIntent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent2);
                }else{
                    Toast.makeText(getApplicationContext(),"جهازك لا يدعم هذه الخاصية",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


        //get advertisements using volley


    private void jsonRequest_user_info() {

        //receive data
        final String User_id=getIntent().getExtras().getString("shop_user_id");
//        Log.i("networkJson",User_id+"");


        StringRequest request__more_info=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.length()!=0)
                    {
                        data_layout.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                    }else{

                        internet_layout.setVisibility(View.VISIBLE);
                    }

                    offer_owner_value.setText(jsonObject.getString("user_name"));
                    offer_email_value.setText(jsonObject.getString("user_email"));
                    phone1_button.setText(jsonObject.getString("user_telephone1"));
                    phone2_button.setText(jsonObject.getString("user_telephone2"));
                    offer_address_value.setText(jsonObject.getString("user_address"));
                    Glide.with(getApplicationContext()).load(jsonObject.getString("user_photo")).apply(option).into(person_image);
                    telephone1=jsonObject.getString("user_telephone1");
                    telephone2=jsonObject.getString("user_telephone2");
//                    Log.i("networkJson",jsonObject+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                data_layout.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_user_info");
                params.put("user_id", User_id);

                return params;
            }
        };



        requestQueue_more_info= Volley.newRequestQueue(this);
        requestQueue_more_info.add(request__more_info);
    }

}
