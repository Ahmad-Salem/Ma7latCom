package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmad_elbayadi.ma7laty.List_Of_Shop_adapters.List_Of_shops_Adapter;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.Shop_property;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_of_shops_activity extends AppCompatActivity {

    private List<String> cityList;
    private String government_text_value,city_text_value,shop_activity_text_value;

    private String shop_activiy_value;

    // Initializing a String Array
    private String[] governments_list = new String[]{
        "أختر المحافظة",
                "محافظة القاهرة",
                "محافظة الجِيزَة",
                "محافظة القليوبية",
                "محافظة الإسكندرية",
                "محافظة البحيرة",
                "محافظة مطروح",
                "محافظة دمياط",
                "محافظة الدقهلية",
                "محافظة كفر الشيخ",
                "محافظة الغربية",
                "محافظة المنوفية",
                "محافظة الشرقية",
                "محافظة بورسعيد",
                "محافظة الإسماعيلية",
                "محافظة السويس",
                "محافظة شمال سيناء",
                "محافظة جنوب سيناء",
                "محافظة بني سويف",
                "محافظة الفيوم",
                "محافظة المنيا",
                "محافظة أسيوط",
                "محافظة البحر الأحمر",
                "محافظة سوهاج",
                "محافظة قنا",
                "محافظة الأقصر",
                "محافظة أَسْوان"
    };


    //Giza city lists
    private String[] kalyobiya_cities_list=new String[]
            {
                    "اختر المدينة",
                    "بَنْها",
                    "قَلْيوب",
                    "شُبْرا الخيمة",
                    "القناطر الخيرية",
                    "الخْانْكَة",
                    "كفر شُكر",
                    "طُوخ",
                    "قَها",
                    "العبور",
                    "الخُصُوص",
                    "شِبِين القناطر"
            };
    //Giza city lists
    private String[] Alex_cities_list=new String[]
            {
                    "اختر المدينة",
                    "الإسكندرية",
                    "برج العرب",
                    "برج العرب الجديدة",

            };

    //Elbehera city lists
    private String[] Elbehera_cities_list=new String[]
            {
                    "اختر المدينة",
                    "دَمَنْهور",
                    "كفر الدَّوَّار",
                    "رَشيد",
                    "إدكو",
                    "أبو المطامير",
                    "أبو حُمُّص",
                    "الدِّلنْجات",
                    "المحموديّة",
                    "الرحمانيّة",
                    "إيتاي البارود",
                    "حُوش عيسى",
                    "شُبراخِيت",
                    "كوم حمادة",
                    "بدر",
                    "وادي النَطْرون",
                    "النُوبَاريّة الجديدة"
            };

    //Giza city lists
    private String[] matroh_cities_list3=new String[]
            {
                    "اختر المدينة",
                    "مَرْسَى مَطْرُوح",
                    "الحَمَّام",
                    "العَلَمِين",
                    "الضَّبْعَة",
                    "النِّجِيلَة",
                    "سِيِدي بَرَّانِي",
                    "السَّلُّوم",
                    "سِيوَة"
            };

    //Giza city lists
    private String[] domyat_cities_list4=new String[]
            {
                    "اختر المدينة",
                    "دمياط",
                    "دمياط الجديدة",
                    "رأس البر",
                    "فارسكور",
                    "كفر سعد",
                    "الزرقا",
                    "السرو",
                    "الروضة",
                    "كفر البطيخ",
                    "عزبة البرج",
                    "ميت أبو غالب"
            };

    //Giza city lists
    private String[] daqahliya_cities_list5=new String[]
            {
                    "اختر المدينة",
                    "المنصورة",
                    "طَلْخا",
                    "ميت غمر",
                    "دِكِرِنْس",
                    "أجا",
                    "منية النصر",
                    "السنبلاوين",
                    "الكردي",
                    "بني عبيد",
                    "المنزلة",
                    "تمي الأمديد",
                    "الجمالية",
                    "شربين",
                    "المطرية",
                    "بلقاس",
                    "ميت سلسيل",
                    "جمصة",
                    "محلة دمنة",
                    "نبروه"
            };
    //Giza city lists
    private String[] kafrelsheish_cities_list6=new String[]
            {
                    "اختر المدينة",
                    "كفر الشيخ",
                    "دِسوق",
                    "فُوّه",
                    "مِطوُبِس",
                    "بَلْطيم",
                    "مصيف بَلْطيم",
                    "الحامول",
                    "بِيَلا",
                    "الرياض",
                    "سيدي سالم",
                    "قَلّين",
                    "سيدي غازي",
                    "بُرج البُرلُّس"
            };
    //Giza city lists
    private String[] gharbiya_cities_list7=new String[]
            {
                    "اختر المدينة",
                    "طنْطَا",
                    "المحلة الكبرى",
                    "كفر الزَّيَّات",
                    "زِفْتى",
                    "السّنْطة",
                    "قُطور",
                    "بَسْيون",
                    "سَمَنُّود"
            };
    //Giza city lists
    private String[] monofiya_cities_list8=new String[]
            {
                    "اختر المدينة",
                    "شِبين الكوم",
                    "مدينة السادات",
                    "مِنُوف",
                    "سِرس الليَّان",
                    "أَشْمُون",
                    "ُوِيْسنا",
                    "الباجور",
                    "بركة السبع",
                    "تَلَا",
                    "الشهداء"
            };
    //Giza city lists
    private String[] Elsharkiya_cities_list9=new String[]
            {
                    "اختر المدينة",
                    "الزقازيق",
                    "العاشر من رمضان",
                    "منيا القمح",
                    "بِلْبيس",
                    "مشتول السوق",
                    "القنايات",
                    "أبو حمّاد",
                    "القُرين",
                    "هِهْيا",
                    "أبو كبير",
                    "فاقوس",
                    "الصالحية الجديدة",
                    "الإبراهيمية",
                    "ديرب نجم",
                    "كفر صقر",
                    "أولاد صقر",
                    "الحسينية",
                    "صان الحجر القبلية",
                    "منشأة أبو عمر"

            };
    //Giza city lists
    private String[] port_said_cities_list10=new String[]
            {
                    "اختر المدينة",
                    "بورسعيد",
                    "بورفؤاد"
            };

    //Giza city lists
    private String[] isamilai_cities_list11=new String[]
            {
                    "اختر المدينة",
                    "الإسماعيلية",
                    "فايد",
                    "القنطرة شرق",
                    "القنطرة غرب",
                    "التل الكبير",
                    "أبو صوير المحطة",
                    "القصاصين الجديدة"
            };
    //Giza city lists
    private String[] suze_cities_list12=new String[]
            {
                    "اختر المدينة",
                    "السويس"
            };
    //Giza city lists
    private String[] northofsinai_cities_list13=new String[]
            {
                    "اختر المدينة",
                    "العريش",
                    "الشّيخ زُوَيِّد",
                    "رَفَح",
                    "بئر العبد",
                    "الحَسَنَة",
                    "نَخِل"
            };
    //Giza city lists
    private String[] southofsinai_cities_list14=new String[]
            {
                    "اختر المدينة",
                    "الطور",
                    "شرم الشيخ",
                    "دهب",
                    "نويبع",
                    "طابا",
                    "سانت كاترين",
                    "أبو رديس",
                    "أبو زنيمة",
                    "رأس سدر"
            };
    //Giza city lists
    private String[] bani_swaf_cities_list15=new String[]
            {
                    "اختر المدينة",
                    "بني سويف الجديدة",
                    "الواسْطَى",
                    "ناصر",
                    "إهناسيا",
                    "بِبا",
                    "سمسطا",
                    "الفَشْن"
            };
    //Giza city lists
    private String[] fayuom_cities_list16=new String[]
            {
                    "اختر المدينة",
                    "الفُيُّوم",
                    "الفُيُّوم الجديدة",
                    "طَامِيِّة",
                    "سنورس",
                    "إطسا",
                    "يوسف الصديق"
            };
    //Giza city lists
    private String[] Elmenya_cities_list17=new String[]
            {
                    "اختر المدينة",
                    "المنيا",
                    "المنيا الجديدة",
                    "العدوة",
                    "مَغَاغَة",
                    "بني مزار",
                    "مَطَاي",
                    "سَمَالُوط",
                    "المدينة الفكرية",
                    "مَلّوي",
                    "دِير مَوَاس"
            };
    //Giza city lists
    private String[] asout_cities_list18=new String[]
            {
                    "اختر المدينة",
                    "الخَارْجَة",
                    "باريس",
                    "مُوط",
                    "الفرافرة",
                    "بلاط"
            };
    //Giza city lists
    private String[] Red_sea_cities_list19=new String[]
            {
                    "اختر المدينة",
                    "الغردقة",
                    "رأس غارب",
                    "سفاجا",
                    "القصير",
                    "مرسى علم",
                    "الشلاتين",
                    "حلايب"
            };
    //Giza city lists
    private String[] Sohag_cities_list20=new String[]
            {
                    "اختر المدينة",
                    "سُوهَاج",
                    "سوهاج الجديدة",
                    "أخميم",
                    "أخميم الجديدة",
                    "البلْيَنا",
                    "المراغة",
                    "المنشأة",
                    "دار السلام",
                    "جِرجا",
                    "جُهِينَة الغربيّة",
                    "ساقلته",
                    "طمَا",
                    "طَهُطَا"
            };

    //Giza city lists
    private String[] qena_cities_list21=new String[]
            {
                    "اختر المدينة",
                    "قِنَا",
                    "قنا الجديدة",
                    "أبُو تِشْت",
                    "نَجْع حَمَّادِي",
                    "دِشْنَا",
                    "الوَقْف",
                    "قِفْط",
                    "نَقَادَة",
                    "قُوص",
                    "فَرْشُوط"
            };

    //Giza city lists
    private String[] louxor_cities_list22=new String[]
            {
                    "اختر المدينة",
                    "الأقصر",
                    "الأقصر الجديدة",
                    "طِيبة الجديدة",
                    "الزينيّة",
                    "البَيَاضِيّة",
                    "القُرْنَة",
                    "أَرمَنْت",
                    "الطُّود",
                    "إسنا"
            };

    //Giza city lists
    private String[] aswan_cities_list23=new String[]
            {
                    "اختر المدينة",
                    "أَسْوان",
                    "أَسْوان الجديدة",
                    "دراو",
                    "نصر النوبة",
                    "كَلَابْشَة",
                    "إِدفو",
                    "الرِّدِيسيّة",
                    "البِصِيليَّة",
                    "السباعية",
                    "أبو سمبل السياحية",
            };
    //Giza city lists
    private String[] Giza_cities_list=new String[]
            {
                    "اختر المدينة",
                    "الجِيزَة",
                    "السادس من أكتوبر",
                    "الشيخ زايد",
                    "الحَوامْدِيّة",
                    "البَدْرْشِين",
                    "الصَّف",
                    "أطْفِيح",
                    "العَيَّاط",
                    "الباويطي",
                    "منشأة القناطر",
                    "أَوْسِيم",
                    "كِرْداسَة",
                    "أبو النُمْرُس",
                    "كفر غطاطي ومنشأة البكاري"

            };
    //cairo city lists
    private String[] cairo_cities_list=new String[]
            {
                    "اختر المدينة",
                    "حي الزيتون",
                    "حي الزاوية الحمراء",
                    "حي حدائق القبة",
                    "حي الشرابية",
                    "حي الساحل",
                    "حي شبرا",
                    "حي روض الفرج",
                    "حي الأميرية",
                    "حي السلام أول",
                    "حي السلام ثان",
                    "حي المرج",
                    "حي المطرية",
                    "حي عين شمس",
                    "حي النزهة",
                    "حي مصر الجديدة",
                    "حي شرق مدينة نصر",
                    "حي غرب مدينة نصر",
                    "حي الوايلي",
                    "حي منشأة ناصر",
                    "حي وسط",
                    "حي باب الشعرية",
                    "حي الأزبكية",
                    "حي بولاق",
                    "حي الموسكي",
                    "حي عابدين",
                    "حي غرب",
                    "حي المقطم",
                    "حي الخليفة",
                    "حي السيدة زينب",
                    "حي مصر القديمة",
                    "حي دار السلام",
                    "حي البساتين",
                    "حي المعادي",
                    "حي طره",
                    "حي المعصرة",
                    "حي 15 مايو",
                    "حي حلوان",
                    "حي التبين",
                    "حي شرق مدينة نصر",
                    "القاهرة الجديدة",
                    "بدر",
                    "الشروق"
            };

    //shop activity list
    private String[] shop_activity_list=new String[]
            {
                    "أختر نشاط المحل",
                    "ملابس رجالي",
                    "ملابس حريمي",
                    "أحذية رجالي",
                    "أحذية حريمي",
                    "الموبايلات",
                    "إلكترونيات وكمبيوترات",
                    "سوبر ماركت",
                    "أقمشة",
                    "مطاعم",
                    "أجهزة كهربية وإلكترونية",
                    "العاب أطفال",
                    "حلويات",
                    "خضروات وفاكهة",
                    "مستلزمات رجالي",
                    "مستلزمات حريمي",
                    "سجاد ومفروشات",
                    "سيراميك ومواد صحية",
                    "شركات السفر والسياحة",
                    "صيدليات",
                    "محلات عطور وبخور",
                    "مكاتب وأدوات مكتبية",
                    "لحوم حمراء وبيضاء",
                    "مغسلة",
                    "مقاهي",
                    "مواد تنظيف",
                    "دهب",
                    "معدات سيارات",
                    "كوافير حريمي",
                    "كوافير رجالي",
                    "أتيليه رجالي",
                    "أتيليه حريمي",
                    "العطار",
                    "أدوات كهربية",
                    "إستوديوهات وفوتوجرافيك"
            };



    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/list_shops_V4.php";
    private final String  JSON_URL_search="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/list_shops_search.php";
    private RequestQueue requestQueue_shops;

    private List<Shop_property> Shop_list;
    private TextView toolbarTitle,data_text,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private RelativeLayout data_layout;
    private Dialog Discover_shop_dialog1;
    private Spinner city,shop_activity,gevernments;
    private Typeface tf;


    //pagination settings
    private Boolean isScrolling=false;
    private int limit=10;
    private int total_displayed=5;
    private int page=1;
    private ConstraintLayout loading;
    private List_Of_shops_Adapter mRecycleViewAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView_list_of_shops;
    private int page_number=1;
    private int item_count=10;

    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previousTotal=0;
    private int view_threshold=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_shops);


        Toolbar toolbar = (Toolbar)findViewById(R.id.shop_list_activity_toolbar);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        final String government=getIntent().getExtras().getString("government");
        final String city=getIntent().getExtras().getString("city");
        final String shop_activity=getIntent().getExtras().getString("shop_activiy");

        shop_activiy_value=getIntent().getExtras().getString("shop_activiy_value");





        //casting shop list screen
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title_shop_list);
        recyclerView_list_of_shops=findViewById(R.id.recycle);
        Shop_list=new ArrayList<>();

        data_text=(TextView)findViewById(R.id.data_text_list_of_shop);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_list_of_shop);
        retry_btn=(Button)findViewById(R.id.retry_list_of_shop);
        internet_layout=(RelativeLayout) findViewById(R.id.list_of_shop_internet_connection_layout);
        data_layout=(RelativeLayout)findViewById(R.id.list_of_shop_data_null);
        loading=(ConstraintLayout)findViewById(R.id.loading);

        //pop up search
        Discover_shop_dialog1=new Dialog(this);
        //json request
//        jsonRequest_shops_list();

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
       tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //typeface the toolbar
        toolbarTitle.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);



        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_shops_list();
            }
        });





        ////recyclerview
        mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView_list_of_shops.setLayoutManager(mLinearLayoutManager);
        recyclerView_list_of_shops.removeAllViews();
        recyclerView_list_of_shops.removeAllViewsInLayout();
        recyclerView_list_of_shops.setHasFixedSize(true);


        //on scroll listener
        recyclerView_list_of_shops.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


        /**********************************************************************************************************/












        /*********** if i come to this activity from different ways ******/

        try{
            if(shop_activiy_value.equals("1"))
            {
                jsonRequest_shops_list_search(government,city,shop_activity);
            }else
            {
                jsonRequest_shops_list();
            }
        }catch(Exception ex)
        {
            jsonRequest_shops_list();
        }
        /***********End  if i come to this activity from different ways ******/






    }




    //get advertisements using volley




    private void jsonRequest_shops_list() {

        //receive data
        final String government=getIntent().getExtras().getString("government");
        final String city=getIntent().getExtras().getString("city");
        final String shop_activity=getIntent().getExtras().getString("shop_activiy");

        /*********************************************************************************************/


            StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject = null;
                    JSONObject jsonObject2 = null;
                    try {
                        jsonObject = new JSONObject(response);
                        if(jsonObject.length()!=0)
                        {
                            recyclerView_list_of_shops.setVisibility(View.VISIBLE);
                            internet_layout.setVisibility(View.GONE);
                            data_layout.setVisibility(View.GONE);

                        }else{
                            recyclerView_list_of_shops.setVisibility(View.GONE);
                            data_layout.setVisibility(View.VISIBLE);
                            internet_layout.setVisibility(View.GONE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("okay"))
                        {

                            for (int i = 0; i < jsonObject.length(); i++)
                            {

                                try{

                                    jsonObject2=jsonObject.getJSONObject(i+"");

                                    Shop_property shop_model=new Shop_property();

                                    shop_model.setShop_id(jsonObject2.getString("shop_id"));
                                    shop_model.setShop_name(jsonObject2.getString("shop_name"));
                                    shop_model.setShop_country(jsonObject2.getString("shop_country"));
                                    shop_model.setShop_address(jsonObject2.getString("shop_address"));
                                    shop_model.setShop_description(jsonObject2.getString("shop_description"));
                                    shop_model.setShop_user_id(jsonObject2.getString("shop_user_id"));
                                    shop_model.setShop_photo(jsonObject2.getString("shop_photo"));
                                    shop_model.setShop_activity(jsonObject2.getString("shop_shop_activity"));
                                    shop_model.setShop_lat(jsonObject2.getString("shop_lat"));
                                    shop_model.setShop_log(jsonObject2.getString("shop_log"));
                                    shop_model.setShop_allowed_product(jsonObject2.getString("shop_allowed_product"));
                                    shop_model.setShop_allowed_offers(jsonObject2.getString("shop_allowed_offers"));
                                    shop_model.setShop_goverment(jsonObject2.getString("shop_government"));
                                    shop_model.setShop_city(jsonObject2.getString("shop_city"));

                                    Log.i("gdasdasda54545",jsonObject2.getString("shop_photo")+",");
                                    Shop_list.add(shop_model);
                                    loading.setVisibility(View.GONE);
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                        }else if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("end")){

                            Shop_list.clear();
                            recyclerView_list_of_shops.setVisibility(View.VISIBLE);
                            internet_layout.setVisibility(View.GONE);
                            data_layout.setVisibility(View.GONE);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                    SettingUpRecycleView(Shop_list);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    recyclerView_list_of_shops.setVisibility(View.GONE);
                    data_layout.setVisibility(View.GONE);
                    internet_layout.setVisibility(View.VISIBLE);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("do_action", "get_shops_list");
                    params.put("country", "مصر");
                    params.put("government", government);
                    params.put("shop_activity", shop_activity);
                    params.put("city", city);
                    params.put("limit_records", String.valueOf(limit));
                    params.put("page", String.valueOf(page_number));


                    return params;
                }
            };



            requestQueue_shops= Volley.newRequestQueue(this);
            requestQueue_shops.add(request_shops);


        /********************************************************************************************/

    }



    private void SettingUpRecycleView(final List<Shop_property> Shop_list) {
        mRecycleViewAdapter=new List_Of_shops_Adapter(this,Shop_list);

        recyclerView_list_of_shops.setAdapter(mRecycleViewAdapter);
    }
    private void SettingUpRecycleViewAdding(List<Shop_property> Shop_list) {



//        mRecycleViewAdapter=new List_Of_shops_Adapter(this,Shop_list);
//        recyclerView_list_of_shops.setAdapter(mRecycleViewAdapter);
    }

/***
    public void Show_pop_up_search_setting() {



        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        TextView close,title;
        Button search_btn;
        Discover_shop_dialog1.setContentView(R.layout.search_setting);

        close = (TextView) Discover_shop_dialog1.findViewById(R.id.close_btn_advertisement_paid_offer123);
        title = (TextView) Discover_shop_dialog1.findViewById(R.id.adver_title_paid_offer456);

        city = (Spinner) Discover_shop_dialog1.findViewById(R.id.city_spine);
        shop_activity = (Spinner) Discover_shop_dialog1.findViewById(R.id.shop_activity_spine);
        search_btn = (Button) Discover_shop_dialog1.findViewById(R.id.visite_shop_paid_offer456);





        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog1.dismiss();
            }
        });
        title.setTypeface(tf);
        search_btn.setTypeface(tf);

        //show Dialog
        Discover_shop_dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog1.show();


//        Toast.makeText(getApplicationContext(),"ahmed salem",Toast.LENGTH_LONG).show();

    }

****/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_shops, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.action_search_shop:
//                Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();
//                Show_pop_up();
//                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    private void Show_pop_up() {
        TextView close,title;
        final TextView back_government;
        final TextView back_city;
        Button btn_follow;
        Discover_shop_dialog1.setContentView(R.layout.search_setting);
        back_government=(TextView) Discover_shop_dialog1.findViewById(R.id.back_government);
        back_city=(TextView) Discover_shop_dialog1.findViewById(R.id.back_city);
        close=(TextView) Discover_shop_dialog1.findViewById(R.id.close_btn);
        title=(TextView) Discover_shop_dialog1.findViewById(R.id.popup_title);
        btn_follow=(Button) Discover_shop_dialog1.findViewById(R.id.dis_shop_action);



        back_government.setTypeface(tf);
        back_city.setTypeface(tf);
        title.setTypeface(tf);

        //casting the governments spinner
        gevernments = (Spinner) Discover_shop_dialog1.findViewById(R.id.government_spine_pop_up);
        city = (Spinner) Discover_shop_dialog1.findViewById(R.id.city_spine);
        shop_activity = (Spinner) Discover_shop_dialog1.findViewById(R.id.shop_activity_spine);

        back_government.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gevernments.setVisibility(View.VISIBLE);
                city.setVisibility(View.GONE);
                back_government.setVisibility(View.GONE);

            }
        });

        back_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city.setVisibility(View.VISIBLE);
                back_government.setVisibility(View.VISIBLE);
                shop_activity.setVisibility(View.GONE);
                back_city.setVisibility(View.GONE);

            }
        });

        // Initializing an ArrayAdapter
        final List<String> Gov_list = new ArrayList<>(Arrays.asList(governments_list));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.govermment_spin_prototype,Gov_list){

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                ((TextView) v).setTypeface(tf);

                return v;
            }

            @Override
            public boolean isEnabled(int position){
                if(position==0||position==1||position==2||position==3||position==4||position==5||position==6||position==7||position==8||position==9||position==10||position==11||position==13||position==14||position==15||position==16||position==17||position==18||position==19||position==20||position==21||position==22||position==23||position==24||position==25||position==26)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(tf);
                if(position==0||position==1||position==2||position==3||position==4||position==5||position==6||position==7||position==8||position==9||position==10||position==11||position==13||position==14||position==15||position==16||position==17||position==18||position==19||position==20||position==21||position==22||position==23||position==24||position==25||position==26) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };




        spinnerArrayAdapter.setDropDownViewResource(R.layout.gove_down_spin_prototype);

        gevernments.setAdapter(spinnerArrayAdapter);


        //spinner on selected item
        gevernments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(!gevernments.getSelectedItem().toString().equalsIgnoreCase("أختر المحافظة")){
                    // Toast.makeText(ge    tApplicationContext(),"good selection",Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    //to get the text value of spinner
                    government_text_value = gevernments.getSelectedItem().toString();
//                    Log.i("govermentahmed",government_text_value);
                    if(government_text_value.equals("محافظة القاهرة"))
                    {
                        // Initializing an ArrayAdapter
                        cityList = new ArrayList<>(Arrays.asList(cairo_cities_list));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        // Initializing an ArrayAdapter
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }

                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);




                    }else if(government_text_value.equals("محافظة الجِيزَة"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Giza_cities_list));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }

                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);

                    }
                    else if(government_text_value.equals("محافظة القليوبية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(kalyobiya_cities_list));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الإسكندرية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Alex_cities_list));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة البحيرة"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Elbehera_cities_list));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة مطروح"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(matroh_cities_list3));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة دمياط"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(domyat_cities_list4));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الدقهلية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(daqahliya_cities_list5));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة كفر الشيخ"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(kafrelsheish_cities_list6));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الغربية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(gharbiya_cities_list7));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة المنوفية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(monofiya_cities_list8));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الشرقية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Elsharkiya_cities_list9));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position==0||position==1||position==2||position==3||position==4||position==5||position==6||position==7||position==8||position==9||position==10||position==11||position==13||position==14||position==12||position==16||position==17||position==18||position==19)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0||position==1||position==2||position==3||position==4||position==5||position==6||position==7||position==8||position==9||position==10||position==11||position==13||position==14||position==12||position==16||position==17||position==18||position==19) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة بورسعيد"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(port_said_cities_list10));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الإسماعيلية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(isamilai_cities_list11));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة السويس"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(suze_cities_list12));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة شمال سيناء"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(northofsinai_cities_list13));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة جنوب سيناء"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(southofsinai_cities_list14));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة بني سويف"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(bani_swaf_cities_list15));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الفيوم"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(fayuom_cities_list16));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة المنيا"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(qena_cities_list21));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة أسيوط"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(asout_cities_list18));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة البحر الأحمر"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Red_sea_cities_list19));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة سوهاج"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Sohag_cities_list20));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة قنا"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(qena_cities_list21));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){


                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة الأقصر"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(louxor_cities_list22));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }
                    else if(government_text_value.equals("محافظة أَسْوان"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(aswan_cities_list23));
                        gevernments.setVisibility(View.GONE);
                        city.setVisibility(View.VISIBLE);
                        back_government.setVisibility(View.VISIBLE);
                        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                ((TextView) v).setTypeface(tf);

                                return v;
                            }


                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                tv.setTypeface(tf);
                                if(position==0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                        city.setAdapter(spinnerArrayAdapter_city);
                    }




                }else{

                    Toast.makeText(getApplicationContext(),"من فضلك أختر المحافظة",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                city_text_value=city.getSelectedItem().toString();
                //save usersetting
                if(!city_text_value.equals("اختر المدينة"))
                {
                    city.setVisibility(View.GONE);
                    shop_activity.setVisibility(View.VISIBLE);
                    back_city.setVisibility(View.VISIBLE);
                    back_government.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter_shop_activity = new ArrayAdapter<String>(
                this,R.layout.govermment_spin_prototype,shop_activity_list){

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                ((TextView) v).setTypeface(tf);

                return v;
            }


            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(tf);
                if(position==0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };




        spinnerArrayAdapter_shop_activity.setDropDownViewResource(R.layout.gove_down_spin_prototype);

        shop_activity.setAdapter(spinnerArrayAdapter_shop_activity);

        shop_activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                shop_activity_text_value=shop_activity.getSelectedItem().toString();
                //save usersetting
                if(!shop_activity_text_value.equals("أختر نشاط المحل"))
                {
//                    Intent Main=new Intent(MainActivity.this,List_of_shops_activity.class);
//                    Main.putExtra("government",government_text_value);
//                    Main.putExtra("city",city_text_value);
//                    Main.putExtra("shop_activiy",shop_activity_text_value);
//                    startActivity(Main);
//                    Toast.makeText(getApplicationContext(),"gov is "+government_text_value,Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),"city is "+city_text_value,Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),"shop activity is "+shop_activity_text_value,Toast.LENGTH_LONG).show();

                    jsonRequest_shops_list_search(government_text_value,city_text_value,shop_activity_text_value);
                    Discover_shop_dialog1.dismiss();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





//        city.setAdapter(spinnerArrayAdapter);
//        shop_activity.setAdapter(spinnerArrayAdapter);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog1.dismiss();
            }
        });

        //show Dialog
        Discover_shop_dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog1.show();
    }

    private void jsonRequest_shops_list_search(final String government,final String city,final String shop_activity) {




        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL_search, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.length()!=0)
                    {
                        Shop_list.clear();
                        recyclerView_list_of_shops.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);

                    }else{
                        recyclerView_list_of_shops.setVisibility(View.GONE);
                        data_layout.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (int i = 1; i < jsonObject.length(); i++)
                {
                    try{

                        jsonObject2=jsonObject.getJSONObject(i+"");

                        Shop_property shop_model=new Shop_property();

                        shop_model.setShop_id(jsonObject2.getString("shop_id"));
                        shop_model.setShop_name(jsonObject2.getString("shop_name"));
                        shop_model.setShop_country(jsonObject2.getString("shop_country"));
                        shop_model.setShop_address(jsonObject2.getString("shop_address"));
                        shop_model.setShop_description(jsonObject2.getString("shop_description"));
                        shop_model.setShop_user_id(jsonObject2.getString("shop_user_id"));
                        shop_model.setShop_photo(jsonObject2.getString("shop_photo"));
                        shop_model.setShop_activity(jsonObject2.getString("shop_shop_activity"));
                        shop_model.setShop_lat(jsonObject2.getString("shop_lat"));
                        shop_model.setShop_log(jsonObject2.getString("shop_log"));
                        shop_model.setShop_allowed_product(jsonObject2.getString("shop_allowed_product"));
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


                SettingUpRecycleView(Shop_list);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                recyclerView_list_of_shops.setVisibility(View.GONE);
                data_layout.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops_list");
                params.put("country", "مصر");
                params.put("government", government);
                params.put("shop_activity", shop_activity);
                params.put("city", city);

                return params;
            }
        };



        requestQueue_shops= Volley.newRequestQueue(this);
        requestQueue_shops.add(request_shops);
    }

    private  void perform_pagination()
    {
        loading.setVisibility(View.VISIBLE);

        //receive data
        final String government=getIntent().getExtras().getString("government");
        final String city=getIntent().getExtras().getString("city");
        final String shop_activity=getIntent().getExtras().getString("shop_activiy");

        /*********************************************************************************************/


        StringRequest request_shops=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.length()!=0)
                    {
                        recyclerView_list_of_shops.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);

                    }else{
                        recyclerView_list_of_shops.setVisibility(View.GONE);
                        data_layout.setVisibility(View.VISIBLE);
                        internet_layout.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("okay"))
                    {

                        for (int i = 1; i < jsonObject.length(); i++)
                        {

                            try{

                                jsonObject2=jsonObject.getJSONObject(i+"");

                                Shop_property shop_model=new Shop_property();

                                shop_model.setShop_id(jsonObject2.getString("shop_id"));
                                shop_model.setShop_name(jsonObject2.getString("shop_name"));
                                shop_model.setShop_country(jsonObject2.getString("shop_country"));
                                shop_model.setShop_address(jsonObject2.getString("shop_address"));
                                shop_model.setShop_description(jsonObject2.getString("shop_description"));
                                shop_model.setShop_user_id(jsonObject2.getString("shop_user_id"));
                                shop_model.setShop_photo(jsonObject2.getString("shop_photo"));
                                shop_model.setShop_activity(jsonObject2.getString("shop_shop_activity"));
                                shop_model.setShop_lat(jsonObject2.getString("shop_lat"));
                                shop_model.setShop_log(jsonObject2.getString("shop_log"));
                                shop_model.setShop_allowed_product(jsonObject2.getString("shop_allowed_product"));
                                shop_model.setShop_allowed_offers(jsonObject2.getString("shop_allowed_offers"));
                                shop_model.setShop_goverment(jsonObject2.getString("shop_government"));
                                shop_model.setShop_city(jsonObject2.getString("shop_city"));

                                Log.i("gdasdasda54545",jsonObject2.getString("shop_photo")+",");
                                Shop_list.add(shop_model);
                                loading.setVisibility(View.GONE);
                            }catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }

                    }else if(jsonObject.getJSONObject(String.valueOf(0)).getString("status").equals("end")){

                        loading.setVisibility(View.GONE);

                    }

//                    mRecycleViewAdapter.add_shop_item(Shop_list);
                    Log.i("gdasdasda545451223",Shop_list.get(Shop_list.size()-1).getShop_id()+",");

                } catch (JSONException e) {
                    e.printStackTrace();
                }




//                SettingUpRecycleViewAdding(Shop_list);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerView_list_of_shops.setVisibility(View.GONE);
                data_layout.setVisibility(View.GONE);
                internet_layout.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops_list");
                params.put("country", "مصر");
                params.put("government", government);
                params.put("shop_activity", shop_activity);
                params.put("city", city);
                params.put("limit_records", String.valueOf(limit));
                params.put("page", String.valueOf(page_number));


                return params;
            }
        };



        requestQueue_shops= Volley.newRequestQueue(this);
        requestQueue_shops.add(request_shops);


    }
}
