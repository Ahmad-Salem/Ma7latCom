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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
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

public class Offer_Details  extends AppCompatActivity {

    private TextView toolbar_offer_deatils,offer_name,offer_name_value,offer_description,offer_description_value,username,username_value,email,email_value,address,address_value;
    private Button telephone1,telephone2;
    private String offer_id;
    private String offer_name_v;
    private String offer_description_v;
    private String offer_photo;
    private String shop_owner_user_name;
    private String shop_owner_email;
    private String shop_owner_photo;
    private String shop_owner_address;
    private String shop_owner_telephone1;
    private String shop_owner_telephone2;
    private ImageView offer_image;
    private SubsamplingScaleImageView offer_zoomed_image;
    private RequestOptions option,option2;
    private Dialog Discover_shop_dialog;
    private String shop_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer__details);


        //toolbar
        Toolbar toolbar1 = (Toolbar)findViewById(R.id.offer_details_activity_toolbar1);
        setSupportActionBar(toolbar1);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);




        //casting android screen component
        toolbar_offer_deatils=(TextView)findViewById(R.id.offer_main_list_toolbar_title_302);
        offer_name=(TextView)findViewById(R.id.offer_name_details_pro);
        offer_name_value=(TextView)findViewById(R.id.offer_name_details);
        offer_description=(TextView)findViewById(R.id.offer_description_details_pro);
        offer_description_value=(TextView)findViewById(R.id.offer_country_details);
        username=(TextView)findViewById(R.id.offer_owner_name_details_pro2);
        username_value=(TextView)findViewById(R.id.offer_name_details2);
        email=(TextView)findViewById(R.id.offer_owner_email_details_pro2);
        email_value=(TextView)findViewById(R.id.offer_country_details2);
        address=(TextView)findViewById(R.id.offer_owner_address_details_pro2);
        address_value=(TextView)findViewById(R.id.offer_owner_address_details);
        telephone1=(Button)findViewById(R.id.dial_number1_info);

        telephone2=(Button)findViewById(R.id.dial_number2_info);

        offer_image=(ImageView)findViewById(R.id.offer_photo_details);
        Discover_shop_dialog=new Dialog(this);



//        person_image=(ImageView)findViewById(R.id.offer_photo_details2);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting the font face
        toolbar_offer_deatils.setTypeface(tf);
        offer_name.setTypeface(tf);
        offer_name_value.setTypeface(tf);
        offer_description.setTypeface(tf);
        offer_description_value.setTypeface(tf);
        username.setTypeface(tf);
        username_value.setTypeface(tf);
        email.setTypeface(tf);
        email_value.setTypeface(tf);
        address.setTypeface(tf);
        address_value.setTypeface(tf);
        telephone1.setTypeface(tf);

        telephone2.setTypeface(tf);


        //recieve data from intent
        offer_id=getIntent().getExtras().getString("offer_id");
        offer_name_v=getIntent().getExtras().getString("offer_name");
        offer_description_v=getIntent().getExtras().getString("offer_description");
        offer_photo=getIntent().getExtras().getString("offer_photo");
        shop_owner_user_name=getIntent().getExtras().getString("shop_owner_user_name");
        shop_owner_email=getIntent().getExtras().getString("shop_owner_email");
        shop_owner_photo=getIntent().getExtras().getString("shop_owner_photo");
        shop_owner_address=getIntent().getExtras().getString("shop_owner_address");
        shop_owner_telephone1=getIntent().getExtras().getString("shop_owner_telephone1");
        shop_owner_telephone2=getIntent().getExtras().getString("shop_owner_telephone2");
        shop_id=getIntent().getExtras().getString("shop_id");


        //back_button
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION", Context.MODE_PRIVATE);

                if(shop_id.equals(""))
                {
                    final String shop_id2=editor_shop_details.getString("shop_id", "");
                    Intent Main=new Intent(getApplicationContext(),List_Of_Offers.class);
                    Main.putExtra("Shop_id_offers",shop_id2);
                    startActivity(Main);
                }else{
                    Intent Main=new Intent(getApplicationContext(),List_Of_Products.class);
                    Main.putExtra("Shop_id_products",shop_id);
                    startActivity(Main);
                }


            }
        });



        //setting value to view component
        offer_name_value.setText(offer_name_v);
        offer_description_value.setText(offer_description_v);
        username_value.setText(shop_owner_user_name);
        email_value.setText(shop_owner_email);
        address_value.setText(shop_owner_address);
        telephone1.setText(shop_owner_telephone1);
        telephone2.setText(shop_owner_telephone2);

        //to make call direct
        telephone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + shop_owner_telephone1));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //to make call direct
        telephone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + shop_owner_telephone2));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        option2=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
//        Glide.with(this).load(shop_owner_photo).apply(option2).into(person_image);
        Glide.with(this).load(offer_photo).apply(option).into(offer_image);


        offer_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_pop_up();
            }
        });

        Log.i("image_test",shop_owner_photo);


    }


    private void Show_pop_up() {
        TextView close,shop_name;
        View back;
        Discover_shop_dialog.setContentView(R.layout.offer_details_popup_zooming);
        back = (View) Discover_shop_dialog.findViewById(R.id.close_btn_shop2_f);
        shop_name = (TextView) Discover_shop_dialog.findViewById(R.id.name_shop);
        offer_zoomed_image=(SubsamplingScaleImageView)Discover_shop_dialog.findViewById(R.id.offer_details_image_zooming);

        Log.i("Tag:null_offerzooming",offer_photo);
//        Glide.with(this).load(offer_photo).apply(option).into(offer_zoomed_image);
//        Picasso.get().load(offer_photo).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(offer_zoomed_image);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        shop_name.setTypeface(tf);
        shop_name.setText(offer_name_v);
        //loading the image
        loadImageView();

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
