package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_Of_shop_Adapter_owner;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.Shop_property_own;
import com.example.ahmad_elbayadi.ma7laty.splash_screen.splash_screen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_private extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/display_shops.php";
    private RequestQueue requestQueue_shops;
    private List<Shop_property_own> Shop_list;
    private TextView toolbarTitle,data_text,internet_connection_text;
    private Button retry_btn;
    private RecyclerView recyclerView_list_of_shops;
    private RelativeLayout internet_layout;
    private RequestOptions option;
    private ImageView person_image;
    private TextView person_name,person_Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_private);

        Toolbar toolbar = (Toolbar)findViewById(R.id.Main_private_activity_toolbar);
        setSupportActionBar(toolbar);

        //casting shop list screen
//        toolbarTitle=(TextView)findViewById(R.id.toolbar_title_Main_private);
        data_text=(TextView)findViewById(R.id.data_text_main_private_screen);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_main_private);
        retry_btn=(Button)findViewById(R.id.retry_main_private);
        internet_layout=(RelativeLayout) findViewById(R.id.main_private_internet_connection_layout);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        final Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar
//        toolbarTitle.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);
        recyclerView_list_of_shops=findViewById(R.id.recycle_shop1);
        Shop_list=new ArrayList<>();

        //json request
        jsonRequest_shops_list();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                jsonRequest_shops_list();
//                Snackbar.make(view, "تم تحديث القائمة", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //add shop
                Intent add_shop=new Intent(getApplicationContext(),Add_shop_own.class);
                startActivity(add_shop);
            }
        });

        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_shops_list();
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final Menu navMenu = navigationView.getMenu();
        navigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ArrayList<View> menuItems = new ArrayList<>(); // save Views in this array
                navigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this); // remove the global layout listener
                for (int i = 0; i < navMenu.size(); i++) {// loops over menu items  to get the text view from each menu item
                    final MenuItem item = navMenu.getItem(i);
                    navigationView.findViewsWithText(menuItems, item.getTitle(), View.FIND_VIEWS_WITH_TEXT);
                }
                for (final View menuItem : menuItems) {// loops over the saved views and sets the font
                    ((TextView) menuItem).setTypeface(tf);
                }
            }
        });


        View headerView = navigationView.getHeaderView(0);
        person_name=(TextView)headerView.findViewById(R.id.nav_user_name);
        person_Email=(TextView)headerView.findViewById(R.id.nav_email);
        person_image=(ImageView) headerView.findViewById(R.id.person_image);
        person_name.setTypeface(tf);
        person_Email.setTypeface(tf);
        //setting navigation drawable
        //request option for Gilde
        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String EMAIL=user_info.getString("user_email","");
        final String USER_NAME=user_info.getString("user_user_name","");
        final String USER_PHOTO=user_info.getString("user_photo","");

//        Toast.makeText(getApplicationContext(),""+USER_NAME+"/"+EMAIL+"/"+USER_PHOTO,Toast.LENGTH_LONG).show();
        person_name.setText(""+USER_NAME);
        person_Email.setText(""+EMAIL);

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading_gif).error(R.drawable.default_person);
        Glide.with(this).load(USER_PHOTO).apply(option).into(person_image);


    }



    //get advertisements using volley

    private void jsonRequest_shops_list() {

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");
        Log.i("ahedmsalemhhhh",ID+"/"+EMAIL+"/"+PASSWORD1);
        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);

                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_text.setVisibility(View.GONE);
                    }else{
                        data_text.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jsonObject.length(); i++)
                {
                    try{

                        jsonObject2=jsonObject.getJSONObject(i+"");

                        Shop_property_own shop_model=new Shop_property_own();
                        Log.i("ahmed_salessss",jsonObject2+"");
                        shop_model.setShop_id(jsonObject2.getString("shop_id"));
                        shop_model.setShop_name(jsonObject2.getString("shop_name"));
                        shop_model.setShop_country(jsonObject2.getString("shop_country"));
                        shop_model.setShop_address(jsonObject2.getString("shop_address"));
                        shop_model.setShop_description(jsonObject2.getString("shop_description"));
                        shop_model.setShop_user_id(jsonObject2.getString("user_id"));
                        shop_model.setShop_photo(jsonObject2.getString("shop_photo"));
                        shop_model.setShop_activity(jsonObject2.getString("shop_activity"));
                        shop_model.setShop_lat(jsonObject2.getString("shop_lat"));
                        shop_model.setShop_log(jsonObject2.getString("shop_log"));
                        shop_model.setShop_allowed_product(jsonObject2.getString("shop_allowed_products"));
                        shop_model.setShop_allowed_offers(jsonObject2.getString("shop_allowed_offers"));
                        shop_model.setShop_goverment(jsonObject2.getString("shop_government"));
                        shop_model.setShop_city(jsonObject2.getString("shop_city"));

                        Log.i("gdasdasda54545",jsonObject2.getString("shop_photo")+",");
                        Shop_list.add(shop_model);
                    }catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }



                recyclerView_list_of_shops.removeAllViews();
                SettingUpRecycleView(Shop_list);
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
                params.put("do_action", "display_shops");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);


                return params;
            }
        };



        requestQueue_shops= Volley.newRequestQueue(this);
        requestQueue_shops.add(request_shops);
    }

    private void SettingUpRecycleView(List<Shop_property_own> Shop_list) {
        List_Of_shop_Adapter_owner mRecycleViewAdapter=new List_Of_shop_Adapter_owner(this,Shop_list);
        recyclerView_list_of_shops.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_list_of_shops.setAdapter(mRecycleViewAdapter);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            SharedPreferences.Editor editor_login = getSharedPreferences("ACCOUNT_INFORMATION", MODE_PRIVATE).edit();
            editor_login.putString("login_flag", "c");
            editor_login.putString("user_id", "");
            editor_login.putString("user_user_name", "");
            editor_login.putString("user_type", "");
            editor_login.putString("user_email", "");
            editor_login.putString("user_password", "");
            editor_login.putString("user_photo", "");
            editor_login.putString("user_status", "");
            editor_login.putString("user_gender", "");
            editor_login.putString("user_telephone1", "");
            editor_login.putString("user_telephone2", "");
            editor_login.putString("user_address", "");
            editor_login.putString("user_activated", "");
            editor_login.putString("message", "");
            editor_login.apply();
            Intent Logout=new Intent(MainActivity_private.this,splash_screen.class);
            startActivity(Logout);
        } else if (id == R.id.nav_slideshow) {
            Intent edit_photo=new Intent(MainActivity_private.this,Edit_profile_photo.class);
            startActivity(edit_photo);
        } else if (id == R.id.nav_manage) {
            Intent edit_info=new Intent(MainActivity_private.this,Edit_profile_info.class);
            startActivity(edit_info);
        }else if(id== R.id.nav_mahalty){
            Intent mahalaty=new Intent(MainActivity_private.this,splash_screen.class);
            startActivity(mahalaty);
        } else if (id == R.id.nav_share) {
            Intent Share =new Intent(Intent.ACTION_SEND);
            Share.setType("text/plain");
            String sharebody="https://play.google.com/store/apps/details?id=com.MahalatCom.Mahalat.MahalatCom";
            String sharetitle="Download App Now!!";
            Share.putExtra(Intent.EXTRA_SUBJECT,sharetitle);
            Share.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(Share,"Share Using"));
        } else if (id == R.id.nav_client_service1) {
            Intent mahalaty=new Intent(MainActivity_private.this,Client_Service.class);
            startActivity(mahalaty);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
