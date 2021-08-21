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
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.ahmad_elbayadi.ma7laty.R;


import java.io.File;

public class Product_details extends AppCompatActivity {

    private TextView product_toolbar,product_name,product_name_value,product_description,product_description_value,product_price,product_price_value,user_name,user_name_value,email,email_value,address,address_value;
    private ImageView product_photo,person_photo;
    private String product_id;
    private String product_price_p;
    private String product_name_p;
    private String product_description_p;
    private String shop_id;
    private String user_name_p;
    private String email_p;
    private String photo;
    private String address_p;
    private String telephone1_p;
    private String telephone2_p;
    private String product_main_photo;
    private String product_rest_photos;
    private RequestOptions option,option2;
    private ViewPager product_details_viewpager,product_details_viewpager_zooming;
    private Dialog Discover_shop_dialog;
    private SubsamplingScaleImageView product_zoomed_image;
    private ImageView product_details_ImageView;
    private String [] product_photos_array;
    private Button telephone1_number,telephone2_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.product_details_activity_toolbar1);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);



        Discover_shop_dialog=new Dialog(this);



        //casting android component
        product_details_ImageView=(ImageView) findViewById(R.id.product_photo_details);
//        product_details_viewpager=(ViewPager) findViewById(R.id.product_details_slider_id1);
//        product_details_viewpager_zooming=(ViewPager) findViewById(R.id.product_details_slider_id1);
        //caling the view pager listener
//        product_details_viewpager.addOnPageChangeListener(viewListener_product_details);
//        product_details_viewpager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
//                Show_pop_up_pro_de();
//            }
//        });
        //casting android screen component
        product_toolbar=(TextView)findViewById(R.id.toolbar_title_product_de);
        product_name=(TextView)findViewById(R.id.product_name_details_pro);
        product_name_value=(TextView)findViewById(R.id.product_name_details);
        product_description=(TextView)findViewById(R.id.product_price_details_pro);
        product_description_value=(TextView)findViewById(R.id.product_price_details);
        product_price=(TextView)findViewById(R.id.product_description_details_pro);
        product_price_value=(TextView)findViewById(R.id.product_country_details);
        user_name=(TextView)findViewById(R.id.product_owner_name_details_pro2);
        user_name_value=(TextView)findViewById(R.id.product_name_details2);
        email=(TextView)findViewById(R.id.product_owner_email_details_pro2);
        email_value=(TextView)findViewById(R.id.product_country_details2);
        address=(TextView)findViewById(R.id.product_owner_address_details_pro2);
        address_value=(TextView)findViewById(R.id.product_owner_address_details);

        telephone1_number=(Button)findViewById(R.id.dial_number1_info);
        telephone2_number=(Button)findViewById(R.id.dial_number2_info);
//        product_photo=(ImageView)findViewById(R.id.product_photo_details);
//        person_photo=(ImageView)findViewById(R.id.product_photo_details2);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        product_toolbar.setTypeface(tf);
        product_name.setTypeface(tf);
        product_name_value.setTypeface(tf);
        product_description.setTypeface(tf);
        product_description_value.setTypeface(tf);
        product_price.setTypeface(tf);
        product_price_value.setTypeface(tf);
        user_name.setTypeface(tf);
        user_name_value.setTypeface(tf);
        email.setTypeface(tf);
        email_value.setTypeface(tf);
        address.setTypeface(tf);
        address_value.setTypeface(tf);



        //recieve data from intent
        product_id=getIntent().getExtras().getString("product_id");
        product_price_p=getIntent().getExtras().getString("product_price");
        product_name_p=getIntent().getExtras().getString("product_name");
        product_description_p=getIntent().getExtras().getString("product_description");
        shop_id=getIntent().getExtras().getString("shop_id");
        user_name_p=getIntent().getExtras().getString("user_name");
        email_p=getIntent().getExtras().getString("email");
        photo=getIntent().getExtras().getString("photo");
        address_p=getIntent().getExtras().getString("address");
        telephone1_p=getIntent().getExtras().getString("telephone1");
        telephone2_p=getIntent().getExtras().getString("telephone2");
        product_main_photo=getIntent().getExtras().getString("product_main_photo");
//        product_rest_photos=getIntent().getExtras().getString("product_rest_photos");




        // back button
        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION", Context.MODE_PRIVATE);

                Intent Main=new Intent(getApplicationContext(),product_details_list.class);
                Main.putExtra("Shop_id_products",shop_id);
                startActivity(Main);

//                if(shop_id.equals(""))
//                {
//                    final String shop_id2=editor_shop_details.getString("shop_id", "");
//                    Intent Main=new Intent(getApplicationContext(),List_Of_Products.class);
//                    Main.putExtra("Shop_id_products",shop_id2);
//                    startActivity(Main);
//                }else{
//
//                    Intent Main=new Intent(getApplicationContext(),List_Of_Products.class);
//                    Main.putExtra("Shop_id_products",shop_id);
//                    startActivity(Main);
//                }


            }
        });




        //setting value to view component
        product_name_value.setText(product_name_p);
        product_description_value.setText(product_description_p);
        product_price_value.setText(product_price_p);
        user_name_value.setText(user_name_p);
        email_value.setText(email_p);
        address_value.setText(address_p);
        telephone1_number.setText(telephone1_p);
        telephone2_number.setText(telephone2_p);
        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        Glide.with(this).load(product_main_photo).apply(option).into(product_details_ImageView);

        product_details_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_pop_up_pro_de();
            }
        });
        telephone1_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telephone1_p));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"جهازك لا يدعم هذه الخاصية",Toast.LENGTH_SHORT).show();
                }

            }
        });
        telephone2_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + telephone2_p));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"جهازك لا يدعم هذه الخاصية",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        option2=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
//        Glide.with(this).load(photo).apply(option2).into(person_photo);
//        Glide.with(this).load(photo).apply(option).into(product_photo);

        //calling the adapter product rest photos
//        SettingUpProduct_details_adapter(product_rest_photos);

        //setting the timer
//        Timer timer =new Timer();
//        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
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
//                                Show_pop_up_pro_de(product_details_viewpager.getCurrentItem());
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



//    //timer class
//    public class MyTimerTask extends TimerTask
//    {
//
//        @Override
//        public void run() {
//            Product_details.this.runOnUiThread(new Runnable() {
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

    //setting the advertisement adapter
//    private void SettingUpProduct_details_adapter(String List_Products_details) {
//
//        //setting the advertisement adapter
//        Product_Rest_Images_Adapter pro_de_adapter=new Product_Rest_Images_Adapter(this,List_Products_details);
//        product_details_viewpager.setAdapter(pro_de_adapter);
//
//    }



    public void Show_pop_up_pro_de() {

        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.product_details_popup_zooming);
        product_zoomed_image=(SubsamplingScaleImageView)Discover_shop_dialog.findViewById(R.id.product_details_image_zooming);
//        Glide.with(this).load(product_main_photo).apply(option2).into(product_zoomed_image);
        //Picasso.get().load(product_photos_array[index]).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(product_zoomed_image);

        //loading the image
        loadImageView();
        close = (TextView) Discover_shop_dialog.findViewById(R.id.close_btn_product);
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
