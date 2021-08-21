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
import android.util.Log;
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

public class Shop_details extends AppCompatActivity {

    private TextView toolbarTitle,shop_name,shop_country,shop_activity,shop_address,shop_description,shop_offers,shop_produsts,shop_location;
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
    private Dialog Discover_shop_dialog,Discover_shop_dialog_call;
    private Dialog Discover_shop_dialog2;
    private SubsamplingScaleImageView shop_zoomed_image;
    private Typeface tf;
    private TextView views_dummy,ViewsValue;
    private final String JSON_URL_SHOP_TELEPHONES="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/shop_owner_phone.php";
    private final String JSON_URL_SHOP_VIEWS="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/more_info/get_shop_views.php";
    private final String JSON_URL_SHOP_INCREMENT="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/more_info/increment_view.php";
    private String telephone1="000-000-000";
    private String telephone2="000-000-000";
    private String shop_views="0";
    private TextView shop_views_tv;
    private RequestQueue requestQueue_shop_numbers,requestQueue_get_shop_views,requestQueue_increment_shop_views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        FloatingActionButton fab = findViewById(R.id.fab_discover_call);

        Toolbar toolbar = (Toolbar)findViewById(R.id.shop_details_activity_toolbar1);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //get shop phone numbers
        jsonRequest_get_shop_phone();
        //get shop views
        jsonRequest_get_views();
        //increment shop views
        jsonRequest_increment_shop_views();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Show_pop_up2();
                Intent Main=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(Main);
            }
        });

        // call shop now
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_pop_up_call();
            }
        });

        //casting android component screen
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title_shop_list_de);
        shop_views_tv=(TextView)findViewById(R.id.viewTV12);
        shop_name=(TextView)findViewById(R.id.shop_name_details);
        shop_country=(TextView)findViewById(R.id.shop_country_details23);
        shop_activity=(TextView)findViewById(R.id.shop_activity_details);
        shop_address=(TextView)findViewById(R.id.shop_address_details);
        shop_description=(TextView)findViewById(R.id.shop_desc_details);
        shop_offers=(TextView)findViewById(R.id.shop_action_offers_details);
        shop_produsts=(TextView)findViewById(R.id.shop_action_produts_details);
        shop_location=(TextView)findViewById(R.id.shop_action_produts_details_location);
        views_dummy=(TextView)findViewById(R.id.shop_country_details_pro);
        ViewsValue=(TextView)findViewById(R.id.shop_country_details);

        shop_name_ti=(TextView)findViewById(R.id.shop_name_details_pro);
        shop_country_ti=(TextView)findViewById(R.id.shop_country_details_pro23);
        shop_activity_ti=(TextView)findViewById(R.id.shop_activity_details_pro);
        shop_address_ti=(TextView)findViewById(R.id.shop_address_details_pro);
        shop_description_ti=(TextView)findViewById(R.id.shop_desc_details_pro);

        direction=(Button)findViewById(R.id.direction_details);
        moreinfo=(Button)findViewById(R.id.more_info_details);
        shop_photo=(ImageView)findViewById(R.id.shop_photo_details);

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
        shop_location.setTypeface(tf);
        direction.setTypeface(tf);
        moreinfo.setTypeface(tf);
        shop_name_ti.setTypeface(tf);
        shop_country_ti.setTypeface(tf);
        shop_activity_ti.setTypeface(tf);
        shop_address_ti.setTypeface(tf);
        shop_description_ti.setTypeface(tf);
        shop_views_tv.setTypeface(tf);
        views_dummy.setTypeface(tf);
        ViewsValue.setTypeface(tf);

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
//        activity=getIntent().getExtras().getString("main_page");

        SharedPreferences.Editor editor_shop_details = getSharedPreferences("SHOP_INFORMATION", MODE_PRIVATE).edit();
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
        editor_shop_details.putString("activity", getIntent().getExtras().getString("main_page"));
        editor_shop_details.apply();

        //setting data into view
        shop_name.setText(shop_name1);
        shop_country.setText(shop_country1);
        shop_activity.setText(shop_activity1);
        shop_address.setText(shop_address1);
        shop_description.setText(shop_description1);

//        shop_offers.setText(shop_allowed_offers);
//        shop_produsts.setText(shop_allowed_product);

        Discover_shop_dialog=new Dialog(this);
        Discover_shop_dialog2=new Dialog(this);
        Discover_shop_dialog_call=new Dialog(this);

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
                final String activity=editor_shop_details.getString("activity", "");

                Intent offers_intent=new Intent(getApplicationContext(),List_Of_Offers.class);
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
                offers_intent.putExtra("main_page",activity);
                startActivity(offers_intent);



            }
        });

        shop_produsts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent products_intent=new Intent(getApplicationContext(),product_details_list.class);
                products_intent.putExtra("Shop_id_products",shop_id);
                startActivity(products_intent);


            }
        });
        shop_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.com/maps?(" + "Home Sweet Home" + ")&daddr=" + shop_lat + "," + shop_log + "&z=16 (" + "Where the party is at" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent more_user_info=new Intent(getApplicationContext(),more_shop_info.class);
                more_user_info.putExtra("shop_user_id",shop_user_id);
                startActivity(more_user_info);

//                Toast.makeText(getApplicationContext(),"Need APi",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Show_pop_up_call()
    {
//        call_shop_pop_up
        TextView close,popup_title;
        Button main_activity,all_offers,shop_list,end;
        Discover_shop_dialog_call.setContentView(R.layout.call_shop_pop_up);
        close = (TextView) Discover_shop_dialog_call.findViewById(R.id.close_btn_shop_nav);
        popup_title = (TextView) Discover_shop_dialog_call.findViewById(R.id.pop_shop_title_nav);
        all_offers = (Button) Discover_shop_dialog_call.findViewById(R.id.action_edit_shop_btn_photo_nav);
        shop_list = (Button) Discover_shop_dialog_call.findViewById(R.id.action_delete_shop_btn_nav);
        end = (Button) Discover_shop_dialog_call.findViewById(R.id.action_exit_shop_btn_nav);

        all_offers.setText(telephone1);
        shop_list.setText(telephone2);
        popup_title.setTypeface(tf);
        all_offers.setTypeface(tf);
        shop_list.setTypeface(tf);
        end.setTypeface(tf);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog_call.dismiss();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog_call.dismiss();
            }
        });


        all_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telephone1));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shop_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telephone2));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //show Dialog
        Discover_shop_dialog_call.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog_call.show();
    }
    private void Show_pop_up() {
        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.shop_details_zooming_popup);
//        close = (TextView) Discover_shop_dialog.findViewById(R.id.close_btn_shop);
        back = (View) Discover_shop_dialog.findViewById(R.id.close_btn_shop2);
        shop_name = (TextView) Discover_shop_dialog.findViewById(R.id.name_shop);
        shop_zoomed_image=(SubsamplingScaleImageView) Discover_shop_dialog.findViewById(R.id.shop_details_image_zooming);

        shop_name.setTypeface(tf);
        shop_name.setText(shop_name1);
        Log.i("Tag:null_offerzooming",shop_photo1);

//        shop_zoomed_image.setImage(ImageSource.uri(shop_photo1));
        loadImageView();
//        Glide.with(this).load(shop_photo1).apply(option).into(shop_zoomed_image);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Discover_shop_dialog.dismiss();
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });


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

    private void Show_pop_up2() {
        TextView close,popup_title;
        Button main_activity,all_offers,shop_list,end;
        Discover_shop_dialog2.setContentView(R.layout.shop_details_navigation);
        close = (TextView) Discover_shop_dialog2.findViewById(R.id.close_btn_shop_nav);
        popup_title = (TextView) Discover_shop_dialog2.findViewById(R.id.pop_shop_title_nav);
        main_activity = (Button) Discover_shop_dialog2.findViewById(R.id.action_edit_shop_btn_nav);
        all_offers = (Button) Discover_shop_dialog2.findViewById(R.id.action_edit_shop_btn_photo_nav);
        shop_list = (Button) Discover_shop_dialog2.findViewById(R.id.action_delete_shop_btn_nav);
        end = (Button) Discover_shop_dialog2.findViewById(R.id.action_exit_shop_btn_nav);


        popup_title.setTypeface(tf);
        main_activity.setTypeface(tf);
        all_offers.setTypeface(tf);
        shop_list.setTypeface(tf);
        end.setTypeface(tf);


        Log.i("Tag:null_offerzooming",shop_photo1);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog2.dismiss();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog2.dismiss();
            }
        });

        main_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(Main);
            }
        });
//        if(activity.equals("list_of_shops"))
//        {
//            all_offers.setVisibility(View.GONE);
//        }
//        if(activity.equals("all_offers"))
//        {
//            shop_list.setVisibility(View.GONE);
//        }

        all_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main=new Intent(getApplicationContext(),All_Offers_Main_List.class);
                Main.putExtra("all_government",shop_goverment);
                Main.putExtra("all_city",shop_city1);
                startActivity(Main);
            }
        });
        shop_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main=new Intent(getApplicationContext(),List_of_shops_activity.class);
                Main.putExtra("government",shop_goverment);
                Main.putExtra("city",shop_city1);
                Main.putExtra("shop_activiy","all");
                startActivity(Main);
            }
        });

        //show Dialog
        Discover_shop_dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog2.show();


    }


    private void jsonRequest_get_shop_phone() {

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");

        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL_SHOP_TELEPHONES, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("delete_product_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
                    }

                    if(jsonObject.getString("telephone1")!=null)
                    {

                        telephone1=jsonObject.getString("telephone1");
                        telephone2=jsonObject.getString("telephone2");

                    }else{

                        Toast.makeText(getApplicationContext(),"تأكد من إتصالك بالأنترنت... ثم حاول مره أخري...",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"حدث مشكلة أثناء الأتصال بالانترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shop_owner_phones");
                params.put("shop_id", shop_id);


                return params;
            }
        };



        requestQueue_shop_numbers= Volley.newRequestQueue(this);
        requestQueue_shop_numbers.add(request_shops);
    }
    private void jsonRequest_get_views() {



        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL_SHOP_VIEWS, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("delete_product_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
                    }

                    if(jsonObject.getString("shop_Views")!=null)
                    {

                        shop_views=jsonObject.getString("shop_Views");
                        shop_views_tv.setText(shop_views);
                        ViewsValue.setText(shop_views);

                    }else{

//                        Toast.makeText(getApplicationContext(),"تأكد من إتصالك بالأنترنت... ثم حاول مره أخري...",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"حدث مشكلة أثناء الأتصال بالانترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shop_views");
                params.put("shop_id", shop_id);


                return params;
            }
        };



        requestQueue_get_shop_views= Volley.newRequestQueue(this);
        requestQueue_get_shop_views.add(request_shops);
    }
    private void jsonRequest_increment_shop_views() {



        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL_SHOP_INCREMENT, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("delete_product_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
                    }

                    if(jsonObject.getString("shop_Views")!=null)
                    {

                        shop_views=jsonObject.getString("shop_Views");
                        shop_views_tv.setText(shop_views);
                        ViewsValue.setText(shop_views);

                    }else{

//                        Toast.makeText(getApplicationContext(),"تأكد من إتصالك بالأنترنت... ثم حاول مره أخري...",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"حدث مشكلة أثناء الأتصال بالانترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "increment_shop_views");
                params.put("shop_id", shop_id);


                return params;
            }
        };



        requestQueue_increment_shop_views= Volley.newRequestQueue(this);
        requestQueue_increment_shop_views.add(request_shops);
    }

}
