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
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.ahmad_elbayadi.ma7laty.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class offer_details_own extends AppCompatActivity {
    private TextView toolbar_offer_deatils,offer_name,offer_name_value,offer_description,offer_description_value;
    private String offer_id,shop_id;
    private String offer_name_v;
    private String offer_description_v;
    private String offer_photo;
    private ImageView offer_image;
    private RequestOptions option,option2;
    private Dialog myDialog;
    private Typeface tf;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/delete_offer.php";
    private RequestQueue requestQueue_offers;
    private Dialog Discover_shop_dialog;
    private SubsamplingScaleImageView offer_zoomed_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details_own);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.offer_details_activity_toolbar122);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION_OWN", Context.MODE_PRIVATE);

                final String shop_id=editor_shop_details.getString("shop_id", "");


                Intent Main=new Intent(getApplicationContext(),List_Of_offers_own.class);
                Main.putExtra("Shop_id_offers",shop_id);
                startActivity(Main);

            }
        });



        //casting android screen component
        toolbar_offer_deatils=(TextView)findViewById(R.id.toolbar_title_offer_de22);
        offer_name=(TextView)findViewById(R.id.offer_name_details_pro22);
        offer_name_value=(TextView)findViewById(R.id.offer_name_details22);
        offer_description=(TextView)findViewById(R.id.offer_description_details_pro22);
        offer_description_value=(TextView)findViewById(R.id.offer_country_details22);
        offer_image=(ImageView)findViewById(R.id.offer_photo_details22);

        Discover_shop_dialog=new Dialog(this);



        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting the font face
        toolbar_offer_deatils.setTypeface(tf);
        offer_name.setTypeface(tf);
        offer_name_value.setTypeface(tf);
        offer_description.setTypeface(tf);
        offer_description_value.setTypeface(tf);

        //recieve data from intent
        offer_id=getIntent().getExtras().getString("offer_id");
        offer_name_v=getIntent().getExtras().getString("offer_name");
        offer_description_v=getIntent().getExtras().getString("offer_description");
        offer_photo=getIntent().getExtras().getString("offer_photo");
        shop_id=getIntent().getExtras().getString("shop_id");


        SharedPreferences.Editor editor_shop_details = getSharedPreferences("OFFER_INFORMATION_OWN", MODE_PRIVATE).edit();
        editor_shop_details.putString("offer_id", getIntent().getExtras().getString("offer_id"));
        editor_shop_details.putString("offer_name_v", getIntent().getExtras().getString("offer_name"));
        editor_shop_details.putString("offer_description_v", getIntent().getExtras().getString("offer_description"));
        editor_shop_details.putString("offer_photo", getIntent().getExtras().getString("offer_photo"));
        editor_shop_details.putString("shop_id", getIntent().getExtras().getString("shop_id"));
        editor_shop_details.apply();

        //setting value to view component
        offer_name_value.setText(offer_name_v);
        offer_description_value.setText(offer_description_v);

        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        Glide.with(this).load(offer_photo).apply(option).into(offer_image);

        myDialog=new Dialog(this);
        //reset button
        FloatingActionButton fab = findViewById(R.id.fab_offer_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Show_pop_up_add_offer();


            }
        });



        offer_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_pop_up();
            }
        });



    }


    private void Show_pop_up_add_offer()
    {
        TextView close,title;
        Button btn_edit_shop,btn_delete_shop,btn_edit_shop_photo,exit_close;
        myDialog.setContentView(R.layout.add_offer_pop_up);
        close=(TextView) myDialog.findViewById(R.id.close_btn_offer);
        title=(TextView) myDialog.findViewById(R.id.pop_offer_title);
        btn_edit_shop=(Button) myDialog.findViewById(R.id.action_edit_offer_btn);
        btn_edit_shop_photo=(Button) myDialog.findViewById(R.id.action_edit_offer_btn_photo);
        btn_delete_shop=(Button) myDialog.findViewById(R.id.action_delete_offer_btn);
        exit_close=(Button) myDialog.findViewById(R.id.action_exit_offer_btn);

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
                Intent edit_offer_info=new Intent(getApplicationContext(), Edit_offer_info.class);
                edit_offer_info.putExtra("Shop_id",shop_id);
                edit_offer_info.putExtra("offer_id",offer_id);
                edit_offer_info.putExtra("offer_name",offer_name_v);
                edit_offer_info.putExtra("offer_description",offer_description_v);
                startActivity(edit_offer_info);
            }
        });

        btn_edit_shop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_offer_photo=new Intent(getApplicationContext(),Edit_offer_photo.class);
                edit_offer_photo.putExtra("Shop_id",shop_id);
                edit_offer_photo.putExtra("offer_id",offer_name_v);
                edit_offer_photo.putExtra("offer_photo",offer_photo);
                startActivity(edit_offer_photo);

            }
        });
        btn_delete_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_delete_offer();
            }
        });


        //show Dialog
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    /****************************************************************************************/
    //delete shop

    private void jsonRequest_delete_offer() {

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

                    if(jsonObject.getString("delete_offer_flag").equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),"تمت عملية الحذف بنجاح",Toast.LENGTH_LONG).show();
//                        intent to activation code activity
                        Intent offer_deletion=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(offer_deletion);
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
                params.put("do_action", "delete_offers");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", shop_id);
                params.put("offer_id", offer_id);



                return params;
            }
        };



        requestQueue_offers= Volley.newRequestQueue(this);
        requestQueue_offers.add(request_shops);
    }


    /****************************************************************************************/



    private void Show_pop_up() {
        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.offer_details_popup_zooming_own);
//        close = (TextView) Discover_shop_dialog.findViewById(R.id.close_btn_offer_own);
        back = (View) Discover_shop_dialog.findViewById(R.id.close_btn_shop2);
        shop_name = (TextView) Discover_shop_dialog.findViewById(R.id.name_shop);
        offer_zoomed_image=(SubsamplingScaleImageView)Discover_shop_dialog.findViewById(R.id.offer_details_image_zooming_own);
//        Glide.with(this).load(offer_photo).apply(option).into(offer_zoomed_image);
//        Picasso.get().load(offer_photo).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(offer_zoomed_image);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });

        shop_name.setTypeface(tf);
        shop_name.setText(offer_name_v);

        //loading the image
        loadImageView();

        //show Dialog
        Discover_shop_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Discover_shop_dialog.show();


    }

    private void loadImageView() {
        final String url = offer_photo;
        Glide.with(this).downloadOnly().load(url)
                /* todo replace error icon */
                .apply(option)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        offer_zoomed_image.setImage(ImageSource.uri(Uri.fromFile(resource)));
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });

    }

}
