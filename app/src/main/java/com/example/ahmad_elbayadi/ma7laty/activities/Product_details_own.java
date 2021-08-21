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
import androidx.viewpager.widget.ViewPager;
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

public class Product_details_own extends AppCompatActivity {
    private TextView product_toolbar,product_name,product_name_value,product_description,product_description_value,product_price,product_price_value;
    private ImageView product_photo,person_photo;
    private String product_id;
    private String product_price_p;
    private String product_name_p;
    private String product_description_p;
    private String shop_id;
    private String product_rest_photos;
    private RequestOptions option,option2;
    private ViewPager product_details_viewpager;
    private Dialog myDialog;
    private Typeface tf;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/delete_product.php";
    private RequestQueue requestQueue_product;
    private String [] product_images;
    private Dialog Discover_shop_dialog;
    private SubsamplingScaleImageView product_zoomed_image;
    private String [] product_photos_array;
    private ImageView product_details_ImageView;
    private String product_main_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_own);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.product_details_activity_toolbar122);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION_OWN", Context.MODE_PRIVATE);

                final String shop_id=editor_shop_details.getString("shop_id", "");


                Intent Main=new Intent(getApplicationContext(),List_Of_Product_own.class);
                Main.putExtra("Shop_id_products",shop_id);
                startActivity(Main);

            }
        });

        //casting android component
        product_details_ImageView=(ImageView) findViewById(R.id.product_photo_details22);
//        product_details_viewpager=(ViewPager) findViewById(R.id.product_details_slider_id122);
        //caling the view pager listener
//        product_details_viewpager.addOnPageChangeListener(viewListener_product_details);

        //casting android screen component
        product_toolbar=(TextView)findViewById(R.id.toolbar_title_product_de22);
        product_name=(TextView)findViewById(R.id.product_name_details_pro22);
        product_name_value=(TextView)findViewById(R.id.product_name_details22);
        product_description=(TextView)findViewById(R.id.product_price_details_pro22);
        product_description_value=(TextView)findViewById(R.id.product_price_details22);
        product_price=(TextView)findViewById(R.id.product_description_details_pro22);
        product_price_value=(TextView)findViewById(R.id.product_country_details22);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        product_toolbar.setTypeface(tf);
        product_name.setTypeface(tf);
        product_name_value.setTypeface(tf);
        product_description.setTypeface(tf);
        product_description_value.setTypeface(tf);
        product_price.setTypeface(tf);
        product_price_value.setTypeface(tf);

        //recieve data from intent
        product_id=getIntent().getExtras().getString("product_id");
        product_price_p=getIntent().getExtras().getString("product_price");
        product_name_p=getIntent().getExtras().getString("product_name");
        product_description_p=getIntent().getExtras().getString("product_description");
        shop_id=getIntent().getExtras().getString("shop_id");
        product_main_photo=getIntent().getExtras().getString("product_main_photo");
//        product_rest_photos=getIntent().getExtras().getString("product_rest_photos");
//        Glide.with(this).load(product_main_photo).apply(option).into(product_details_ImageView);

        SharedPreferences.Editor editor_shop_details = getSharedPreferences("PRODUCT_INFORMATION_OWN", MODE_PRIVATE).edit();
        editor_shop_details.putString("product_id", getIntent().getExtras().getString("product_id"));
        editor_shop_details.putString("product_price_p", getIntent().getExtras().getString("product_price"));
        editor_shop_details.putString("product_name_p", getIntent().getExtras().getString("product_name"));
        editor_shop_details.putString("product_description_p", getIntent().getExtras().getString("product_description"));
        editor_shop_details.putString("shop_id", getIntent().getExtras().getString("shop_id"));
        editor_shop_details.putString("product_rest_photos", getIntent().getExtras().getString("product_rest_photos"));
        editor_shop_details.apply();



        Discover_shop_dialog=new Dialog(this);



//        product_images=product_rest_photos.split(",");
//        Toast.makeText(getApplicationContext(),""+shop_id,Toast.LENGTH_LONG).show();
        //setting value to view component
        product_name_value.setText(product_name_p);
        product_description_value.setText(product_description_p);
        product_price_value.setText(product_price_p);

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        option2=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        Glide.with(this).load(product_main_photo).apply(option).into(product_details_ImageView);


        product_details_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_pop_up_pro_de();
            }
        });

        //calling the adapter product rest photos
//        SettingUpProduct_details_adapter(product_rest_photos);

//        //setting the timer
//        Timer timer =new Timer();
//        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        myDialog=new Dialog(this);


        //reset button
        FloatingActionButton fab = findViewById(R.id.fab_product_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Show_pop_up_add_product();


            }
        });


//
//
//        product_details_viewpager.setOnTouchListener(new View.OnTouchListener() {
//            float oldX = 0, newX = 0, sens = 5;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        oldX = event.getX();
////                        Toast.makeText(getApplicationContext(),"clicked"+"/"+oldX,Toast.LENGTH_LONG).show();
//                        if(product_rest_photos!=null)
//                        {
//                            try{
//                                product_photos_array=product_rest_photos.split(",");
//                                //loading image from the internet and set it into imageview using Glide
//                                //Glide.with(mContext).load(product_photos_array[0]).apply(option).into();
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//                            Show_pop_up_pro_de(product_details_viewpager.getCurrentItem());
//
//                        }
//
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        newX = event.getX();
//                        if (Math.abs(oldX - newX) < sens) {
////                            Toast.makeText(getApplicationContext(),"clicked"+"/"+newX,Toast.LENGTH_LONG).show();
////                            Show_pop_up_pro_de();
//                            return true;
//                        }
//                        oldX = 0;
//                        newX = 0;
//                        break;
//                }
//
//                return false;
//            }
//        });



    }


//
//    //timer class
//    public class MyTimerTask extends TimerTask
//    {
//
//        @Override
//        public void run() {
//            Product_details_own.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if(product_details_viewpager.getCurrentItem()==0){
//                        product_details_viewpager.setCurrentItem(1);
//                    }else if(product_details_viewpager.getCurrentItem()==1)
//                    {
//                        product_details_viewpager.setCurrentItem(2);
//                    }else{
//                        product_details_viewpager.setCurrentItem(0);
//                    }
//
//
//                }
//            });
//        }
//    }


    // start calling the viewlistener of adapter of product_details_slider
    //view pager handler
    ViewPager.OnPageChangeListener viewListener_product_details = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_advertisement(position);
//            currentPage_advertisement=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // end calling the viewlistener of adapter of advertisement

//    //setting the advertisement adapter
//    private void SettingUpProduct_details_adapter(String List_Products_details) {
//
//        //setting the advertisement adapter
//        Product_Rest_Images_Adapter_own pro_de_adapter=new Product_Rest_Images_Adapter_own(this,List_Products_details);
//        product_details_viewpager.setAdapter(pro_de_adapter);
//
//    }


    private void Show_pop_up_add_product()
    {
        TextView close,title;
        Button btn_edit_shop,btn_delete_shop,btn_edit_shop_photo,rest_images;
        myDialog.setContentView(R.layout.add_product_pop_up);
        close=(TextView) myDialog.findViewById(R.id.close_btn_product);
        title=(TextView) myDialog.findViewById(R.id.pop_product_title);
        btn_edit_shop=(Button) myDialog.findViewById(R.id.action_edit_product_btn);
        btn_edit_shop_photo=(Button) myDialog.findViewById(R.id.action_edit_product_btn_photo);
        btn_delete_shop=(Button) myDialog.findViewById(R.id.action_delete_product_btn);
        rest_images=(Button) myDialog.findViewById(R.id.action_edit_product_btn_rest_photo);

        btn_edit_shop.setTypeface(tf);
        btn_edit_shop_photo.setTypeface(tf);
        btn_delete_shop.setTypeface(tf);
        rest_images.setTypeface(tf);
        title.setTypeface(tf);

        //hide these images
        rest_images.setVisibility(View.GONE);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        rest_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_product_rest=new Intent(getApplicationContext(),Edit_product_rest_photo.class);
                edit_product_rest.putExtra("Shop_id",shop_id);
                edit_product_rest.putExtra("product_id",product_id);
                startActivity(edit_product_rest);

            }
        });

        btn_edit_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_product_info=new Intent(getApplicationContext(),Edit_product_info.class);
                edit_product_info.putExtra("Shop_id",shop_id);
                edit_product_info.putExtra("product_id",product_id);
                edit_product_info.putExtra("product_name",product_name_p);
                edit_product_info.putExtra("product_price",product_price_p);
                edit_product_info.putExtra("product_description",product_description_p);
                startActivity(edit_product_info);



            }
        });

        btn_edit_shop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_product_photo=new Intent(getApplicationContext(),Edit_product_photo.class);
                edit_product_photo.putExtra("Shop_id",shop_id);
                edit_product_photo.putExtra("product_id",product_id);
                edit_product_photo.putExtra("product_photo",product_main_photo);
                startActivity(edit_product_photo);
            }
        });
        btn_delete_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_delete_product();
            }
        });


        //show Dialog
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    /****************************************************************************************/
    //delete shop

    private void jsonRequest_delete_product() {

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
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("delete_product_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
                    }

                    if(jsonObject.getString("delete_product_flag").equals("1"))
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
                params.put("do_action", "delete_products");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", shop_id);
                params.put("product_id", product_id);

                Log.i("",""+shop_id+"/"+product_id);

                return params;
            }
        };



        requestQueue_product= Volley.newRequestQueue(this);
        requestQueue_product.add(request_shops);
    }


    /****************************************************************************************/



    public void Show_pop_up_pro_de() {

        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.product_details_popup_zooming);
        product_zoomed_image=(SubsamplingScaleImageView)Discover_shop_dialog.findViewById(R.id.product_details_image_zooming);
//        Glide.with(this).load(product_main_photo).apply(option2).into(product_zoomed_image);
        //Picasso.get().load(product_photos_array[index]).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(product_zoomed_image);

        //loading the image
        loadImageView();
        shop_name = (TextView) Discover_shop_dialog.findViewById(R.id.name_shop);
        back = (View) Discover_shop_dialog.findViewById(R.id.close_btn_shop2_p);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        shop_name.setTypeface(tf);
        shop_name.setText(product_name_p);


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
        final String url = product_main_photo;
        Glide.with(this).downloadOnly().load(url)
                /* todo replace error icon */
                .apply(option)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        product_zoomed_image.setImage(ImageSource.uri(Uri.fromFile(resource)));
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });

    }
}
