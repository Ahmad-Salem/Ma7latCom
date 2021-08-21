package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmad_elbayadi.ma7laty.Fragmaents.Product_list;
import com.example.ahmad_elbayadi.ma7laty.Fragmaents.Product_with_photo;
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.ProductMainTabSectionPageAdapter;
import com.example.ahmad_elbayadi.ma7laty.R;

public class product_details_list extends AppCompatActivity {
    private TextView product_toolbar;
    private ProductMainTabSectionPageAdapter mSectionPageAdapter ;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_list);


        //toolbar
        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.product_details_activity_toolbar114);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
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
                Main.putExtra("Shop_id_products",shop_id);
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

        //casting android screen component
        product_toolbar=(TextView)findViewById(R.id.toolbar_title_product_de14);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        product_toolbar.setTypeface(tf);



        //tabled layout
        mSectionPageAdapter=new ProductMainTabSectionPageAdapter(getSupportFragmentManager());
        //setup  the ViewPager with the section Adapter
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        //create tabed layout
        final TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.side_nav_bar));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(mViewPager);


        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
//                if(tab.getPosition()==1)
//                {
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }else if(tab.getPosition()==2){
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }else if(tab.getPosition()==3){
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }


    private void setupViewPager(ViewPager viewPager)
    {
        ProductMainTabSectionPageAdapter adapter=new ProductMainTabSectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Product_with_photo(),"منتجات مصوره");
        adapter.addFragment(new Product_list(),"قائمة المنتجات");

        viewPager.setAdapter(adapter);
    }
}
