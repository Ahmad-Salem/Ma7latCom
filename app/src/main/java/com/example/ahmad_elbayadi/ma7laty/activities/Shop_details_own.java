package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.ahmad_elbayadi.ma7laty.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Shop_details_own extends AppCompatActivity {

    private TextView toolbarTitle,shop_name,shop_country,shop_activity,shop_address,shop_description,shop_offers,shop_produsts;
    private TextView shop_name_ti,shop_country_ti,shop_activity_ti,shop_address_ti,shop_description_ti;
    private Button direction,moreinfo;
    private String shop_id;
    private String shop_name1;
    private String shop_country1;
    private String shop_address1;
    private String shop_description1;
    private String shop_user_id;
    private String shop_photo1;
    private String shop_activity1;
    private String shop_lat;
    private String shop_log;
    private String shop_allowed_product;
    private String shop_allowed_offers;
    private String shop_goverment;
    private String shop_city1;
    private RequestOptions option;
    private ImageView shop_photo;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/delete_shop.php";
    private RequestQueue requestQueue_shops;
    private Dialog myDialog;
    private Typeface tf;
    private Dialog Discover_shop_dialog;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView shop_zoomed_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details_own);

        Toolbar toolbar = (Toolbar)findViewById(R.id.shop_details_activity_toolbar122);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //casting android component screen
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title_shop_list_de22);
        shop_name=(TextView)findViewById(R.id.shop_name_details22);
        shop_country=(TextView)findViewById(R.id.shop_country_details22);
        shop_activity=(TextView)findViewById(R.id.shop_activity_details22);
        shop_address=(TextView)findViewById(R.id.shop_address_details22);
        shop_description=(TextView)findViewById(R.id.shop_desc_details22);
        shop_offers=(TextView)findViewById(R.id.shop_action_offers_details22);
        shop_produsts=(TextView)findViewById(R.id.shop_action_produts_details22);

        shop_name_ti=(TextView)findViewById(R.id.shop_name_details_pro22);
        shop_country_ti=(TextView)findViewById(R.id.shop_country_details_pro22);
        shop_activity_ti=(TextView)findViewById(R.id.shop_activity_details_pro22);
        shop_address_ti=(TextView)findViewById(R.id.shop_address_details_pro22);
        shop_description_ti=(TextView)findViewById(R.id.shop_desc_details_pro22);

        direction=(Button)findViewById(R.id.direction_details22);
        shop_photo=(ImageView)findViewById(R.id.shop_photo_details22);

        Discover_shop_dialog=new Dialog(this);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar
        toolbarTitle.setTypeface(tf);
        toolbarTitle.setTypeface(tf);
        shop_name.setTypeface(tf);
        shop_country.setTypeface(tf);
        shop_activity.setTypeface(tf);
        shop_address.setTypeface(tf);
        shop_description.setTypeface(tf);
        shop_offers.setTypeface(tf);
        shop_produsts.setTypeface(tf);
        direction.setTypeface(tf);
        shop_name_ti.setTypeface(tf);
        shop_country_ti.setTypeface(tf);
        shop_activity_ti.setTypeface(tf);
        shop_address_ti.setTypeface(tf);
        shop_description_ti.setTypeface(tf);

        //recieve data from intent
        shop_id=getIntent().getExtras().getString("shop_id");
        shop_name1=getIntent().getExtras().getString("shop_name");
        shop_country1=getIntent().getExtras().getString("shop_country");
        shop_address1=getIntent().getExtras().getString("shop_address");
        shop_description1=getIntent().getExtras().getString("shop_description");
        shop_user_id=getIntent().getExtras().getString("shop_user_id");
        shop_photo1=getIntent().getExtras().getString("shop_photo");
        shop_activity1=getIntent().getExtras().getString("shop_activity");
        shop_lat=getIntent().getExtras().getString("shop_lat");
        shop_log=getIntent().getExtras().getString("shop_log");
        shop_allowed_product=getIntent().getExtras().getString("shop_allowed_product");
        shop_allowed_offers=getIntent().getExtras().getString("shop_allowed_offers");
        shop_goverment=getIntent().getExtras().getString("shop_goverment");
        shop_city1=getIntent().getExtras().getString("shop_city");


        SharedPreferences.Editor editor_shop_details = getSharedPreferences("SHOP_INFORMATION_OWN", MODE_PRIVATE).edit();
        editor_shop_details.putString("shop_id", getIntent().getExtras().getString("shop_id"));
        editor_shop_details.putString("shop_name1", getIntent().getExtras().getString("shop_name"));
        editor_shop_details.putString("shop_country1", getIntent().getExtras().getString("shop_country"));
        editor_shop_details.putString("shop_address1", getIntent().getExtras().getString("shop_address"));
        editor_shop_details.putString("shop_description1", getIntent().getExtras().getString("shop_description"));
        editor_shop_details.putString("shop_user_id", getIntent().getExtras().getString("shop_user_id"));
        editor_shop_details.putString("shop_photo1", getIntent().getExtras().getString("shop_photo"));
        editor_shop_details.putString("shop_activity1", getIntent().getExtras().getString("shop_activity"));
        editor_shop_details.putString("shop_lat", getIntent().getExtras().getString("shop_lat"));
        editor_shop_details.putString("shop_log", getIntent().getExtras().getString("shop_log"));
        editor_shop_details.putString("shop_allowed_product", getIntent().getExtras().getString("shop_allowed_product"));
        editor_shop_details.putString("shop_allowed_offers", getIntent().getExtras().getString("shop_allowed_offers"));
        editor_shop_details.putString("shop_goverment", getIntent().getExtras().getString("shop_goverment"));
        editor_shop_details.putString("shop_city1", getIntent().getExtras().getString("shop_city"));
        editor_shop_details.apply();


        //setting data into view
        shop_name.setText(shop_name1);
        shop_country.setText(shop_country1);
        shop_activity.setText(shop_activity1);
        shop_address.setText(shop_address1);
        shop_description.setText(shop_description1);
//        shop_offers.setText(shop_allowed_offers);
//        shop_produsts.setText(shop_allowed_product);

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        Glide.with(this).load(shop_photo1).apply(option).into(shop_photo);

        shop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_pop_up();
            }
        });


        shop_offers.setOnClickListener(new View.OnClickListener() {
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


                Intent offers_intent=new Intent(getApplicationContext(),List_Of_offers_own.class);
                offers_intent.putExtra("Shop_id_offers",shop_id);
                offers_intent.putExtra("Shop_id_offers",shop_id);
                offers_intent.putExtra("shop_id",shop_id);
                offers_intent.putExtra("shop_name",shop_name1);
                offers_intent.putExtra("shop_country",shop_country1);
                offers_intent.putExtra("shop_address",shop_address1);
                offers_intent.putExtra("shop_description",shop_description1);
                offers_intent.putExtra("shop_user_id",shop_user_id);
                offers_intent.putExtra("shop_photo",shop_photo1);
                offers_intent.putExtra("shop_activity",shop_activity1);
                offers_intent.putExtra("shop_lat",shop_lat);
                offers_intent.putExtra("shop_log",shop_log);
                offers_intent.putExtra("shop_allowed_product",shop_allowed_product);
                offers_intent.putExtra("shop_allowed_offers",shop_allowed_offers);
                offers_intent.putExtra("shop_goverment",shop_goverment);
                offers_intent.putExtra("shop_city",shop_city1);
                startActivity(offers_intent);


            }
        });

        shop_produsts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent products_intent=new Intent(getApplicationContext(),List_Of_Product_own.class);
                products_intent.putExtra("Shop_id_products",shop_id);
                startActivity(products_intent);


            }
        });


        //setting dialog
        myDialog=new Dialog(this);
        //reset button
        FloatingActionButton fab = findViewById(R.id.fab_shop_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Show_pop_up_add_shop();



            }
        });
    }



    private void Show_pop_up_add_shop()
    {
        TextView close,title;
        Button btn_edit_shop,btn_delete_shop,btn_edit_shop_photo,exit_close;
        myDialog.setContentView(R.layout.add_shop_pop_up);
        close=(TextView) myDialog.findViewById(R.id.close_btn_shop);
        title=(TextView) myDialog.findViewById(R.id.pop_shop_title);
        btn_edit_shop=(Button) myDialog.findViewById(R.id.action_edit_shop_btn);
        btn_edit_shop_photo=(Button) myDialog.findViewById(R.id.action_edit_shop_btn_photo);
        btn_delete_shop=(Button) myDialog.findViewById(R.id.action_delete_shop_btn);
        exit_close=(Button) myDialog.findViewById(R.id.action_exit_shop_btn);

        btn_edit_shop.setTypeface(tf);
        btn_edit_shop_photo.setTypeface(tf);
        btn_delete_shop.setTypeface(tf);
        exit_close.setTypeface(tf);
        title.setTypeface(tf);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        exit_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btn_edit_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_shop_info=new Intent(getApplicationContext(),Edit_shop_info.class);
                edit_shop_info.putExtra("Shop_id",shop_id);
                edit_shop_info.putExtra("Shop_name",shop_name1);
                edit_shop_info.putExtra("Shop_address",shop_address1);
                edit_shop_info.putExtra("Shop_gov",shop_goverment);
                edit_shop_info.putExtra("Shop_city",shop_city1);
                edit_shop_info.putExtra("Shop_activity",shop_activity1);
                edit_shop_info.putExtra("Shop_descreption",shop_description1);
                edit_shop_info.putExtra("Shop_lat",shop_lat);
                edit_shop_info.putExtra("Shop_log",shop_log);
                startActivity(edit_shop_info);

            }
        });

        btn_edit_shop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent edit_shop_photo=new Intent(getApplicationContext(),Edit_shop_photo.class);
                edit_shop_photo.putExtra("Shop_id",shop_id);
                edit_shop_photo.putExtra("Shop_photo",shop_photo1);
                startActivity(edit_shop_photo);

            }
        });
        btn_delete_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_delete_shop();
            }
        });


        //show Dialog
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    /****************************************************************************************/
    //delete shop

    private void jsonRequest_delete_shop() {

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");

        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("delete_shop_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
                    }

                    if(jsonObject.getString("delete_shop_flag").equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),"تمت عملية الحذف بنجاح",Toast.LENGTH_LONG).show();
//                        intent to activation code activity
                        Intent Shop_deletion=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(Shop_deletion);
                    }else{
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"حاول مرة أخري",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"حدث مشكلة أثناء الأتصال بالانترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "delete_shops");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", shop_id);



                return params;
            }
        };



        requestQueue_shops= Volley.newRequestQueue(this);
        requestQueue_shops.add(request_shops);
    }


    /****************************************************************************************/



    private void Show_pop_up() {
        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.shop_details_zooming_popup_own);
//        close = (TextView) Discover_shop_dialog.findViewById(R.id.close_btn_shop_own);
        shop_name = (TextView) Discover_shop_dialog.findViewById(R.id.name_shop);
        back = (View) Discover_shop_dialog.findViewById(R.id.close_btn_shop2);

        shop_zoomed_image=(com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView)Discover_shop_dialog.findViewById(R.id.shop_details_image_zooming_own);
//        Glide.with(this).load(shop_photo1).apply(option).into(shop_zoomed_image);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });


        shop_name.setTypeface(tf);
        shop_name.setText(shop_name1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });
        //loading the image
        loadImageView();
        //show Dialog
        Discover_shop_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Discover_shop_dialog.show();


    }

    private void loadImageView() {
        final String url = shop_photo1;
        Glide.with(this).downloadOnly().load(url)
                /* todo replace error icon */
                .apply(option)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        shop_zoomed_image.setImage(ImageSource.uri(Uri.fromFile(resource)));
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });

    }
}
