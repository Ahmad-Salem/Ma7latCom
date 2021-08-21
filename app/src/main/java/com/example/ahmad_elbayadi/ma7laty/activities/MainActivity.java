package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.Activity;
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

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.AllShopAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.CarpetsAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.ClothesAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.ElectronicsAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.MainSearchAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.ResturantAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.advertisementAdapter;
import com.example.ahmad_elbayadi.ma7laty.MainScreen_adapters.offersAdapter;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.models.Advertisement_model;
import com.example.ahmad_elbayadi.ma7laty.models.Offer_model;
import com.example.ahmad_elbayadi.ma7laty.models.ShopSearch;
import com.example.ahmad_elbayadi.ma7laty.models.all_shop_model;
import com.example.ahmad_elbayadi.ma7laty.models.carpet_model;
import com.example.ahmad_elbayadi.ma7laty.models.clothes_model;
import com.example.ahmad_elbayadi.ma7laty.models.electronics_model;
import com.example.ahmad_elbayadi.ma7laty.models.resturant_model;
import com.example.ahmad_elbayadi.ma7laty.splash_screen.splash_screen;
import com.example.ahmad_elbayadi.ma7laty.user_general_setting.general_setting;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{




    private final String JSON_URL_MAIN_SEARCH="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/listOfShops/list_shops_search_V3.php";

    // Define 2 global class variables
    private static long LAST_CLICK_TIME = 0;
    private final int mDoubleClickInterval = 400; // Milliseconds

    int flag=0;
    private MainSearchAdapter mRecycleViewAdapter;
    private TextWatcher text=null;
    private Spinner gevernments,city,shop_activity;

    private ViewPager AdvertisementSliderViewPager;
    private LinearLayout mDotLayout_advertisement;
    private advertisementAdapter advertisment_adapter;
    private TextView[] mDots_advertisement;
    private RecyclerView offerSliderViewPager;
    private  RecyclerView all_shop_offerSliderViewPager;
    private RecyclerView resturant_offerSliderViewPager;
    private RecyclerView carpet_offerSliderViewPager;
    private RecyclerView electronics_offerSliderViewPager;
    private RecyclerView clothes_offerSliderViewPager;
    private RecyclerView Search_Main;
    private LinearLayout mDotLayout_offer;
    private offersAdapter offer_adapter;
    private TextView[] mDots_offer;
    private String[] governments_list;
    private int currentPage_advertisement;
    private int currentPage_offer;
    private LinearLayout pop_up_container_dis_shop;
    private Typeface tf;
    private Button Add_shop,Discover_shop;
    private Dialog Discover_shop_dialog1;
    private TextView tv_spinner,tv_spinner_popup,toolbar_title,offer_slider_title,offer_slider_title2,advertisement_slider_title;
    private ImageView person_image;
    private TextView person_name,person_Email,internet_connection_text;
    private Button retry_btn;
    private RelativeLayout internet_layout;
    private NestedScrollView scroll_container;
    private TextView all_shop_tv,all_shop_tv_city,resturant_tv,resturant_tv_city,electronics_tv,electronics_tv_city,carpet_tv,carpet_tv_city,clothes_tv,clothes_tv_city;
    /* request to servers parameters*/
    //advertisements
    private final String JSON_URL_ADVERTISEMET="http://www.ma7latcom.com//59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/advertisement.php";
    private RequestQueue requestQueue_advertisement;
    private List<Advertisement_model> List_advertisement;
    //offers
    private final String JSON_URL_OFFERS="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/important_offers.php";
    private final String JSON_URL_OFFERS_ALL_SHOPS="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/shop.php";
    private final String JSON_URL_OFFERS_RESTURANT="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/shop_resturant.php";
    private final String JSON_URL_OFFERS_CARPET="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/shop_Carpets.php";
    private final String JSON_URL_OFFERS_ELECTRONICS="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/shop_electronics.php";
    private final String JSON_URL_OFFERS_CLOTHES="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/mainScreen/shop_clothes.php";

    private RequestQueue requestQueue_offer;
    private RequestQueue requestQueue_offer_all_shops;
    private RequestQueue requestQueue_offer_resturant;
    private RequestQueue requestQueue_offer_carpet;
    private RequestQueue requestQueue_offer_clothes;
    private RequestQueue requestQueue_offer_electronics;

    private List<ShopSearch> List_shops;
    private List<Offer_model> List_Offers;
    private List<all_shop_model> List_Offers_all_shops;
    private List<carpet_model> List_Offers_carpet;
    private List<clothes_model> List_Offers_clothes;
    private List<electronics_model> List_Offers_electronics;
    private List<resturant_model> List_Offers_resturant;






    private List<String> cityList;
    private String government_text_value,city_text_value,shop_activity_text_value;
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

    private RequestOptions option;
    private Dialog Discover_shop_dialog;
    private ImageView advertisement_zoomed_image;
    private String advertisement_photos;
    private String advertisement_title;
    private String advertisement_description;
    private SubsamplingScaleImageView advertisement_image_zoom;
    private TextView advertisement_title_zoom;
    private TextView advertisement_description_zoom,data_text;
    private RelativeLayout data_layout;
    private NestedScrollView data_server;
    private EditText search_bar_value;
    private View back_search_icon,right_search_icon,left_search_icon;
    private RelativeLayout layout_search_result;
    private RelativeLayout layout_main;
    private RelativeLayout loading_container;
    private RotateLoading rotateLoading;
    private Boolean loadingFlag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**************************************************************************************************/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FloatingActionButton fab = findViewById(R.id.fab_discover);


        //setting the timer
        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),3000,4000);



        loading_container=(RelativeLayout)findViewById(R.id.loading_container_main);
        rotateLoading = findViewById(R.id.rotateloading_main);
        rotateLoading.start();

        //casting text views

        offer_slider_title=(TextView)findViewById(R.id.offer_slider_title);
        offer_slider_title2=(TextView)findViewById(R.id.offer_slider_title2);
        advertisement_slider_title=(TextView)findViewById(R.id.advertisement_slider_title);
        data_text=(TextView)findViewById(R.id.main_data_text_list_of_shop);

        data_layout=(RelativeLayout)findViewById(R.id.main_list_of_shop_data_null);
        data_server=(NestedScrollView) findViewById(R.id.scroll_main_activity);
        all_shop_tv=(TextView)findViewById(R.id.All_Shop_offer_slider_title22);
        all_shop_tv_city=(TextView)findViewById(R.id.All_Shop_offer_slider_title222);
        resturant_tv=(TextView)findViewById(R.id.resturant_offer_slider_title22);
        resturant_tv_city=(TextView)findViewById(R.id.resturant_offer_slider_title222);
        electronics_tv=(TextView)findViewById(R.id.electronics_offer_slider_title22);
        electronics_tv_city=(TextView)findViewById(R.id.electronics_offer_slider_title222);
        carpet_tv=(TextView)findViewById(R.id.carpet_offer_slider_title22);
        carpet_tv_city=(TextView)findViewById(R.id.carpet_offer_slider_title222);
        clothes_tv=(TextView)findViewById(R.id.clothes_offer_slider_title22);
        clothes_tv_city=(TextView)findViewById(R.id.clothes_offer_slider_title222);
        search_bar_value=(EditText)findViewById(R.id.search_bar_value);
        back_search_icon=(View) findViewById(R.id.back_search_icon);
        right_search_icon=(View)findViewById(R.id.right_search_icon);
        left_search_icon=(View)findViewById(R.id.left_search_icon);
        layout_search_result=(RelativeLayout) findViewById(R.id.layout_search_result);
        layout_main=(RelativeLayout) findViewById(R.id.layout_main);



        /****************************on click listeners *********************************************/
        all_shop_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"true6",Toast.LENGTH_LONG).show();
                // go to all shop activity
                Intent all_shop_intent = new Intent(getApplicationContext(),List_of_shops_activity.class);
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");
                all_shop_intent.putExtra("government",govenment_sh);
                all_shop_intent.putExtra("city",city_sh);
                all_shop_intent.putExtra("shop_activiy","all");
                startActivity(all_shop_intent);
            }

        });
        resturant_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"true1",Toast.LENGTH_LONG).show();
                // go to all shop activity
                Intent all_shop_intent = new Intent(getApplicationContext(),List_of_shops_activity.class);
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");
                all_shop_intent.putExtra("government",govenment_sh);
                all_shop_intent.putExtra("city",city_sh);
                all_shop_intent.putExtra("shop_activiy","returants");
                startActivity(all_shop_intent);
            }
        });
        electronics_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"true2",Toast.LENGTH_LONG).show();
                // go to all shop activity
                Intent all_shop_intent = new Intent(getApplicationContext(),List_of_shops_activity.class);
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");
                all_shop_intent.putExtra("government",govenment_sh);
                all_shop_intent.putExtra("city",city_sh);
                all_shop_intent.putExtra("shop_activiy","electronics");
                startActivity(all_shop_intent);
            }
        });
        carpet_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"true3",Toast.LENGTH_LONG).show();
                // go to all shop activity
                Intent all_shop_intent = new Intent(getApplicationContext(),List_of_shops_activity.class);
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");
                all_shop_intent.putExtra("government",govenment_sh);
                all_shop_intent.putExtra("city",city_sh);
                all_shop_intent.putExtra("shop_activiy","carpets");
                startActivity(all_shop_intent);
            }
        });
        clothes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"true4",Toast.LENGTH_LONG).show();
                // go to all shop activity
                Intent all_shop_intent = new Intent(getApplicationContext(),List_of_shops_activity.class);
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");
                all_shop_intent.putExtra("government",govenment_sh);
                all_shop_intent.putExtra("city",city_sh);
                all_shop_intent.putExtra("shop_activiy","clothes");
                startActivity(all_shop_intent);
            }
        });
        /*********************************************************************************************/






        offer_slider_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                final String govenment_sh=user_setting.getString("government_user_set","");
                final String city_sh=user_setting.getString("city_user_set","");

                Intent All_offer=new Intent(getApplicationContext(),All_Offers_Main_List.class);
                All_offer.putExtra("all_government",govenment_sh);
                All_offer.putExtra("all_city",city_sh);
                startActivity(All_offer);
            }
        });



        Discover_shop_dialog1=new Dialog(this);

        AdvertisementSliderViewPager=(ViewPager) findViewById(R.id.sliderviewpage_advertisement);

        AdvertisementSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            float oldX = 0, newX = 0, sens = 5;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = event.getX();
//                        Toast.makeText(getApplicationContext(),"clicked"+"/"+oldX,Toast.LENGTH_LONG).show();
                        if(List_advertisement.size()!=0)
                        {
                            try{
                                advertisement_photos=List_advertisement.get(AdvertisementSliderViewPager.getCurrentItem()).getAdvertisement_image_url();
                                advertisement_title=List_advertisement.get(AdvertisementSliderViewPager.getCurrentItem()).getAdvertisement_title();
                                advertisement_description=List_advertisement.get(AdvertisementSliderViewPager.getCurrentItem()).getAdvertisement_description();
                                //loading image from the internet and set it into imageview using Glide
                                //Glide.with(mContext).load(product_photos_array[0]).apply(option).into();

                                if(flag == 0)
                                {
                                    Toast.makeText(getApplicationContext(),"أضغط مرتين لمشاهدة تفاصيل الأعلان.",Toast.LENGTH_SHORT).show();
                                    flag=1;
                                }
                                // Then, inside onClick method of Button
                                long doubleClickCurrentTime = System.currentTimeMillis();
                                long currentClickTime = System.currentTimeMillis();
                                if (currentClickTime - LAST_CLICK_TIME <= mDoubleClickInterval)
                                {
                                    

                                        Log.i("error_null123",advertisement_photos+"/"+advertisement_title+"/"+advertisement_description);
                                        Show_pop_up_pro_de(advertisement_photos,advertisement_title,advertisement_description);


                                }
                                else
                                {
                                    LAST_CLICK_TIME = System.currentTimeMillis();
                                    // !Warning, Single click action problem
                                }


                            }catch (Exception e){
                                Log.i("error_nulle3",advertisement_photos+"/"+advertisement_title+"/"+advertisement_description);
                                Toast.makeText(getApplicationContext(),"حدث خطأ أثناء تحميل الصورة",Toast.LENGTH_LONG).show();

                                e.printStackTrace();
                            }
                        }

                        break;

                    case MotionEvent.ACTION_UP:
                        newX = event.getX();
                        if (Math.abs(oldX - newX) < sens) {
//                            Toast.makeText(getApplicationContext(),"clicked"+"/"+newX,Toast.LENGTH_LONG).show();
//                            Show_pop_up_pro_de();
                            return true;
                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }

                return false;
            }
        });


        Log.e("ahmedErrorMain","I'm Here");

        //setting main search lists
        List_shops=new ArrayList<>();

        //setting the array list of advertisment
        List_advertisement=new ArrayList<>();
        List_Offers=new ArrayList<>();
        List_Offers_all_shops=new ArrayList<>();
        List_Offers_carpet=new ArrayList<>();
        List_Offers_clothes=new ArrayList<>();
        List_Offers_electronics=new ArrayList<>();
        List_Offers_resturant=new ArrayList<>();

        Log.e("ahmedErrorMain","I'm Here2");
        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //cating add shop button
        Add_shop=(Button)findViewById(R.id.add_shop);
        Discover_shop=(Button)findViewById(R.id.shop_discover);
//        toolbar_title=(TextView) findViewById(R.id.toolbar_title);
        internet_connection_text=(TextView)findViewById(R.id.connection_text_main);
        retry_btn=(Button)findViewById(R.id.retry_main);
        internet_layout=(RelativeLayout) findViewById(R.id.main_internet_connection_layout);
        scroll_container=(NestedScrollView)findViewById(R.id.scroll_main_activity);

        Log.e("ahmedErrorMain","I'm Here3");
        Discover_shop_dialog=new Dialog(this);

        Log.e("ahmedErrorMain","I'm Here4");
        Add_shop.setTypeface(tf);
        data_text.setTypeface(tf);
        internet_connection_text.setTypeface(tf);
        retry_btn.setTypeface(tf);
        Discover_shop.setTypeface(tf);
//        toolbar_title.setTypeface(tf);
        advertisement_slider_title.setTypeface(tf);
        offer_slider_title.setTypeface(tf);
        offer_slider_title2.setTypeface(tf);
        Log.e("ahmedErrorMain","I'm Here5");
        //silders titles
        all_shop_tv.setTypeface(tf);
        all_shop_tv_city.setTypeface(tf);
        resturant_tv.setTypeface(tf);
        resturant_tv_city.setTypeface(tf);
        electronics_tv.setTypeface(tf);
        electronics_tv_city.setTypeface(tf);
        carpet_tv.setTypeface(tf);
        carpet_tv_city.setTypeface(tf);
        clothes_tv.setTypeface(tf);
        clothes_tv_city.setTypeface(tf);
        search_bar_value.setTypeface(tf);

        Log.e("ahmedErrorMain","I'm Here6");


        //listner to search edit text
        search_bar_value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                back_search_icon.setVisibility(View.VISIBLE);
                right_search_icon.setVisibility(View.GONE);
                left_search_icon.setVisibility(View.VISIBLE);
                layout_main.setVisibility(View.GONE);
                layout_search_result.setVisibility(View.VISIBLE);

                return false;
            }
        });

        Log.e("ahmedErrorMain","I'm Here7");
        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    Search_Main.getAdapter().notifyDataSetChanged();
                    mRecycleViewAdapter.clear();
                    Search_Main.setAdapter(null);
                }catch (Exception ex)
                {

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(!search_bar_value.getText().toString().equals(""))
                    {

                        jsonRequest_Main_Search(search_bar_value.getText().toString());
                    }else{
                        mRecycleViewAdapter.clear();
                        Search_Main.getAdapter().notifyDataSetChanged();
                        Search_Main.setAdapter(null);
//                        Toast.makeText(getApplicationContext(),"i'm empty",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex)
                {

                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        search_bar_value.addTextChangedListener(text);

        Log.e("ahmedErrorMain","I'm Here8");
        //back search button
        back_search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_search_icon.setVisibility(View.GONE);
                right_search_icon.setVisibility(View.VISIBLE);
                left_search_icon.setVisibility(View.GONE);
                layout_main.setVisibility(View.VISIBLE);
                layout_search_result.setVisibility(View.GONE);
                hideKeyboardFrom(getApplicationContext(),v);
                search_bar_value.setFocusable(false);
                search_bar_value.setFocusableInTouchMode(false);
                search_bar_value.setText("");

            }
        });

        Log.e("ahmedErrorMain","I'm Here9");
        //left search button
        left_search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_search_icon.setVisibility(View.VISIBLE);
                right_search_icon.setVisibility(View.GONE);
                left_search_icon.setVisibility(View.VISIBLE);
                layout_main.setVisibility(View.GONE);
                layout_search_result.setVisibility(View.VISIBLE);
                jsonRequest_Main_Search(search_bar_value.getText().toString());
            }
        });

        //retry button

        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request json
                jsonRequest_offers();
                jsonRequest_advertisement();
                jsonRequest_offers_all_shops();
                jsonRequest_offers_carpets();
                jsonRequest_offers_clothes();
                jsonRequest_offers_resturant();
                jsonRequest_offers_electronics();
                internet_layout.setVisibility(View.GONE);
                scroll_container.setVisibility(View.VISIBLE);
            }
        });

        Log.e("ahmedErrorMain","I'm Here10");
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Show_pop_up();
//            }
//        });

        //onclick lister of discover shop
//        Discover_shop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Show_pop_up();
//            }
//        });

        // Initializing a String Array
        governments_list = new String[]{
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




        //casting spinner tvs
//        tv_spinner=(TextView)findViewById(R.id.spinner_gov);
//        tv_spinner_popup=(TextView)findViewById(R.id.spinner_gov_pop);
//
//        tv_spinner_popup.setTypeface(tf);
//        tv_spinner.setTypeface(tf);
        //to get the text value of spinner
/**        String text = gevernments.getSelectedItem().toString();
**/

        /***********************************************************************************************/
        //casting the advertisement component slider component
        //casting android component

//        mDotLayout_advertisement=(LinearLayout)findViewById(R.id.dotslayout_advertisement);

//        //setting the advertisement adapter
//        advertisment_adapter=new advertisementAdapter(this);
//        AdvertisementSliderViewPager.setAdapter(advertisment_adapter);

        //add indicators of advertisement slider
//        addDotsIndicators_advertisement(0);

        //caling the view pager listener
        AdvertisementSliderViewPager.addOnPageChangeListener(viewListener_advertisement);

        /****************************************************************************************************/
        /****************************************************************************************************/
        //casting the offer component slider component
        //casting android component
        offerSliderViewPager=(RecyclerView) findViewById(R.id.sliderviewpage_offers);
        all_shop_offerSliderViewPager=(RecyclerView) findViewById(R.id.sliderviewpage_offers_all_shop);
        resturant_offerSliderViewPager=(RecyclerView) findViewById(R.id.resturant_sliderviewpage_offers2);
        carpet_offerSliderViewPager=(RecyclerView) findViewById(R.id.carpet_sliderviewpage_offers2);
        electronics_offerSliderViewPager=(RecyclerView) findViewById(R.id.electronics_sliderviewpage_offers2);
        clothes_offerSliderViewPager=(RecyclerView) findViewById(R.id.clothes_sliderviewpage_offers2);
        //main search rescycler view
        Search_Main=(RecyclerView) findViewById(R.id.search_recyclerView);
        Log.e("ahmedErrorMain","I'm Here11");
        //        mDotLayout_offer=(LinearLayout)findViewById(R.id.dotslayout_offers);

        //setting the offer adapter
        // offer_adapter=new offersAdapter(this);
        // offerSliderViewPager.setAdapter(offer_adapter);

        //add indicators of advertisement slider
//        addDotsIndicators_offers(0);

        //caling the view pager listener
//        offerSliderViewPager.addOnPageChangeListener(viewListener_offer);
//        all_shop_offerSliderViewPager.addOnPageChangeListener(viewListener_offer_all_shops);
//        resturant_offerSliderViewPager.addOnPageChangeListener(viewListener_offer_resturant);
//        carpet_offerSliderViewPager.addOnPageChangeListener(viewListener_offer_carpet);
//        electronics_offerSliderViewPager.addOnPageChangeListener(viewListener_offer_electronics);
//        clothes_offerSliderViewPager.addOnPageChangeListener(viewListener_offer_clothes);

        Log.e("ahmedErrorMain","I'm Here12");
        /*******************************************************************************************************/





        Toolbar toolbar = (Toolbar)findViewById(R.id.wedding_activity_toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
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
        person_name=(TextView)headerView.findViewById(R.id.nav_user_name_normal);
        person_Email=(TextView)headerView.findViewById(R.id.nav_email_normal);
        person_image=(ImageView) headerView.findViewById(R.id.person_image_normal);
        person_name.setTypeface(tf);
        person_Email.setTypeface(tf);


        //setting navigation drawable
        //request option for Gilde
        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String EMAIL=user_info.getString("user_email","");
        final String USER_NAME=user_info.getString("user_user_name","");
        final String USER_PHOTO=user_info.getString("user_photo","");

        if(EMAIL!=""&&USER_NAME!=""&&USER_PHOTO!="")
        {
            //        Toast.makeText(getApplicationContext(),""+USER_NAME+"/"+EMAIL+"/"+USER_PHOTO,Toast.LENGTH_LONG).show();
            person_name.setText(""+USER_NAME);
            person_Email.setText(""+EMAIL);

            option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.default_person);
            Glide.with(this).load(USER_PHOTO).apply(option).into(person_image);
            navigationView.getMenu().findItem(R.id.nav_camera12).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logout12).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login12).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_new_user12).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_user_setting).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_client_service).setVisible(true);
        }else{

            navigationView.getMenu().findItem(R.id.nav_camera12).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout12).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_login12).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_new_user12).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_user_setting).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_client_service).setVisible(true);
            person_name.setText("أسم المستخدم");


        }


        //onclick lister of add shop
        Add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EMAIL!=""&&USER_NAME!=""&&USER_PHOTO!="") {
                    //get login activity check logged in or not
                    Intent Login=new Intent(MainActivity.this,MainActivity_private.class);
                    startActivity(Login);
                }else{
                    //get login activity check logged in or not
                    Intent go=new Intent(MainActivity.this,guide_screen.class);
                    startActivity(go);
                }


            }
        });


        /*************************************************/
        //calling volley request
        jsonRequest_advertisement();
        jsonRequest_offers();
        jsonRequest_offers_all_shops();
        jsonRequest_offers_carpets();
        jsonRequest_offers_clothes();
        jsonRequest_offers_resturant();
        jsonRequest_offers_electronics();


        Log.e("ahmedErrorMain","I'm Here13");

        data_layout.setVisibility(View.GONE);
        internet_layout.setVisibility(View.GONE);
        /*************************************************/

        /**************************************************************************************************************************************/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add_shop:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
                final String EMAIL=user_info.getString("user_email","");
                final String USER_NAME=user_info.getString("user_user_name","");
                final String USER_PHOTO=user_info.getString("user_photo","");

                if(EMAIL!=""&&USER_NAME!=""&&USER_PHOTO!="") {
                    //get login activity check logged in or not
                    Intent Login=new Intent(MainActivity.this,MainActivity_private.class);
                    startActivity(Login);
                }else{
                    //get login activity check logged in or not
                    Intent go=new Intent(MainActivity.this,guide_screen.class);
                    startActivity(go);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera12) {
            Intent my_account=new Intent(MainActivity.this,MainActivity_private.class);
            startActivity(my_account);
        }  else if (id == R.id.nav_login12) {
            Intent login=new Intent(MainActivity.this,Login_activity.class);
            startActivity(login);
        }else if (id == R.id.nav_logout12 ) {
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

            Intent logout=new Intent(MainActivity.this,splash_screen.class);
            startActivity(logout);
        }else if (id == R.id.nav_new_user12 ) {
            Intent new_user=new Intent(MainActivity.this,newUserResgister.class);
            startActivity(new_user);
        }else if (id == R.id.nav_user_setting ) {
            //user setting
            Intent user_setting=new Intent(MainActivity.this,general_setting.class);
            startActivity(user_setting);
        }else if (id == R.id.nav_client_service)
        {
            Intent nav_client_service=new Intent(MainActivity.this,Client_Service.class);
            startActivity(nav_client_service);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/***
    private void Show_pop_up() {
        TextView close,title;
        final TextView back_government;
        final TextView back_city;
        Button btn_follow;
        Discover_shop_dialog.setContentView(R.layout.shop_discover_popup);
        back_government=(TextView) Discover_shop_dialog.findViewById(R.id.back_government);
        back_city=(TextView) Discover_shop_dialog.findViewById(R.id.back_city);
        close=(TextView) Discover_shop_dialog.findViewById(R.id.close_btn);
        title=(TextView) Discover_shop_dialog.findViewById(R.id.popup_title);
        btn_follow=(Button) Discover_shop_dialog.findViewById(R.id.dis_shop_action);



        back_government.setTypeface(tf);
        back_city.setTypeface(tf);
        title.setTypeface(tf);

        //casting the governments spinner
        gevernments = (Spinner) Discover_shop_dialog.findViewById(R.id.government_spine_pop_up);
        city = (Spinner) Discover_shop_dialog.findViewById(R.id.city_spine);
        shop_activity = (Spinner) Discover_shop_dialog.findViewById(R.id.shop_activity_spine);

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
                    Intent Main=new Intent(MainActivity.this,List_of_shops_activity.class);
                    Main.putExtra("government",government_text_value);
                    Main.putExtra("city",city_text_value);
                    Main.putExtra("shop_activiy",shop_activity_text_value);
                    Main.putExtra("shop_activiy_value","1");

                    startActivity(Main);
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
                Discover_shop_dialog.dismiss();
            }
        });

        //show Dialog
        Discover_shop_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog.show();
    }
***/
    //start add dosts of offer slider
    public void addDotsIndicators_offers(int pos) {
        mDots_offer=new TextView[3];
        mDotLayout_offer.removeAllViews();
        for(int i=0; i<mDots_offer.length; i++)
        {
            mDots_offer[i]=new TextView(this);
            mDots_offer[i].setText(Html.fromHtml("&#8226;"));
            mDots_offer[i].setTextSize(35);
            mDots_offer[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mDotLayout_offer.addView(mDots_offer[i]);
        }

        if(mDots_offer.length > 0 )
        {
            mDots_offer[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    //start add dosts of offer slider


    //start add dosts of advertisement slider
//    public void addDotsIndicators_advertisement(int pos)
//    {
//        mDots_advertisement=new TextView[3];
//        mDotLayout_advertisement.removeAllViews();
//        for(int i=0; i<mDots_advertisement.length; i++)
//        {
//            mDots_advertisement[i]=new TextView(this);
//            mDots_advertisement[i].setText(Html.fromHtml("&#8226;"));
//            mDots_advertisement[i].setTextSize(35);
//            mDots_advertisement[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//
//            mDotLayout_advertisement.addView(mDots_advertisement[i]);
//        }
//
//        if(mDots_advertisement.length > 0 )
//        {
//            mDots_advertisement[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
//        }
//    }
    //end add dosts of advertisement slider
    // start calling the viewlistener of adapter of offer
    //view pager handler
    ViewPager.OnPageChangeListener viewListener_offer = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    ViewPager.OnPageChangeListener viewListener_offer_all_shops = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    ViewPager.OnPageChangeListener viewListener_offer_resturant = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    ViewPager.OnPageChangeListener viewListener_offer_carpet = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    ViewPager.OnPageChangeListener viewListener_offer_electronics = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    ViewPager.OnPageChangeListener viewListener_offer_clothes = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_offers(position);
            currentPage_offer=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // end calling the viewlistener of adapter of offer
    // start calling the viewlistener of adapter of advertisement
    //view pager handler
    ViewPager.OnPageChangeListener viewListener_advertisement = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
//            addDotsIndicators_advertisement(position);
            currentPage_advertisement=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // end calling the viewlistener of adapter of advertisement

    //get advertisements using volley

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(1,s.length()));
    }

    private void jsonRequest_advertisement() {
        Log.e("ahmedErrorMain","I'm Here Inside  jsonRequest_advertisement");

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");

        StringRequest request_advertisement1=new StringRequest(Request.Method.POST,JSON_URL_ADVERTISEMET, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);

//                    Log.e("ahmedErrorMain","Data is : "+response);
//                    Log.e("ahmedErrorMain","link is : "+JSON_URL_ADVERTISEMET);
                    if(jsonObject.length()!=0)
                    {
//                        Log.e("ahmedErrorMain","I'm Here Inside   Data Recevied");
                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
//                        Log.e("ahmedErrorMain","I'm Here Inside  No Data Recevied");
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {

                            jsonObject2=jsonObject.getJSONObject(i+"");

                            Advertisement_model advertisement_model=new Advertisement_model();

                            advertisement_model.setAdvertisement_id(jsonObject2.getString("adver_id"));
                            advertisement_model.setAdvertisement_title(jsonObject2.getString("adver_title"));
                            advertisement_model.setAdvertisement_description(jsonObject2.getString("adver_description"));
                            advertisement_model.setAdvertisement_government(jsonObject2.getString("adver_government"));
                            advertisement_model.setAdvertisement_city(jsonObject2.getString("adver_city"));
                            advertisement_model.setAdvertisement_image_url(jsonObject2.getString("adver_image_url"));
                            advertisement_model.setAdvertisement_activated(jsonObject2.getString("adv_activated"));
                            advertisement_model.setAdvertisement_from_date(jsonObject2.getString("adv_from_date"));
                            advertisement_model.setAdvertisement_to_date(jsonObject2.getString("adv_to_date"));
                            Log.i("gdasdasda545456",govenment_sh+",");
                            Log.i("gdasdasda54545",jsonObject2.getString("adver_id")+",");
                            List_advertisement.add(advertisement_model);
                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();

                }



                SettingUpAdvertisement_adapter(List_advertisement);
//                loading_container.setVisibility(View.GONE);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);
//                Log.e("ahmedErrorMain","I'm Here No data");
            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                    params.put("do_action", "get_ads");
                    params.put("government", govenment_sh);
                    params.put("city", city_sh);

                return params;
            }
        };



        requestQueue_advertisement= Volley.newRequestQueue(getApplicationContext());
        requestQueue_advertisement.add(request_advertisement1);
    }



    //setting the advertisement adapter
    private void SettingUpAdvertisement_adapter(List<Advertisement_model> List_advertisement) {
        //setting the advertisement adapter
        advertisementAdapter adver_adapter=new advertisementAdapter(this,List_advertisement);
        AdvertisementSliderViewPager.setAdapter(adver_adapter);

    }

    //offer json request
    private void jsonRequest_offers()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);


        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_offer_json",jsonObject+"");
                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");


                        Offer_model offer_model1=new Offer_model();

                        offer_model1.setOffer_id(jsonObject2.getString("offer_id"));
                        offer_model1.setOffer_name(jsonObject2.getString("offer_name"));
                        offer_model1.setOffer_description(jsonObject2.getString("offer_description"));
                        offer_model1.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model1.setOffer_image_url(jsonObject2.getString("offer_image_url"));
                        offer_model1.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model1.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model1.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model1.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model1.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model1.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model1.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model1.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model1.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model1.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model1.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model1.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model1.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model1.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model1.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model1.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model1.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model1.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model1.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model1.setOffer_User_address(jsonObject2.getString("user_address"));
                        offer_model1.setOffer_main_page(jsonObject2.getString("main_page"));


                        Log.i("ahemd_daled",jsonObject2.getString("offer_image_url")+",");
                        List_Offers.add(offer_model1);

                }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                SettingUpOffers_adapter(List_Offers);
//                loading_container.setVisibility(View.GONE);


//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_important_shop");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };



        requestQueue_offer= Volley.newRequestQueue(this);
        requestQueue_offer.add(request_offer1);

    }

    //offer json request
    private void jsonRequest_offers_all_shops()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);

        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS_ALL_SHOPS, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_123",jsonObject+"");

                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");

                        all_shop_model offer_model_all_shop=new all_shop_model();

                        offer_model_all_shop.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model_all_shop.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model_all_shop.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model_all_shop.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model_all_shop.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model_all_shop.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model_all_shop.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model_all_shop.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model_all_shop.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model_all_shop.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model_all_shop.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model_all_shop.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model_all_shop.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model_all_shop.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model_all_shop.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model_all_shop.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model_all_shop.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model_all_shop.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model_all_shop.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model_all_shop.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model_all_shop.setOffer_User_address(jsonObject2.getString("user_address"));
//                        offer_model.setOffer_main_page(jsonObject2.getString("main_page"));


//                        Log.i("gdasdasda54666545",jsonObject2.getString("offer_image_url")+",");
                        List_Offers_all_shops.add(offer_model_all_shop);

                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                SettingUpOffers_adapter_all_shops(List_Offers_all_shops);
//                loading_container.setVisibility(View.GONE);
//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };


        requestQueue_offer_all_shops= Volley.newRequestQueue(this);
        requestQueue_offer_all_shops.add(request_offer1);

    }

    //offer json request
    private void jsonRequest_offers_resturant()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);

        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS_RESTURANT, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_res",jsonObject+"");
                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");

                        resturant_model offer_model_res=new resturant_model();

                        offer_model_res.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model_res.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model_res.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model_res.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model_res.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model_res.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model_res.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model_res.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model_res.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model_res.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model_res.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model_res.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model_res.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model_res.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model_res.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model_res.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model_res.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model_res.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model_res.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model_res.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model_res.setOffer_User_address(jsonObject2.getString("user_address"));
//                        offer_model.setOffer_main_page(jsonObject2.getString("main_page"));


//                        Log.i("gdasdasda54666545",jsonObject2.getString("offer_image_url")+",");
                        List_Offers_resturant.add(offer_model_res);

                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                SettingUpOffers_adapter_resturant(List_Offers_resturant);
//                loading_container.setVisibility(View.GONE);
//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };


        requestQueue_offer_resturant= Volley.newRequestQueue(this);
        requestQueue_offer_resturant.add(request_offer1);

    }

    //offer json request
    private void jsonRequest_offers_carpets()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);

        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS_CARPET, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_offer_json",jsonObject+"");

                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");

                        carpet_model offer_model_carpet=new carpet_model();

                        offer_model_carpet.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model_carpet.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model_carpet.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model_carpet.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model_carpet.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model_carpet.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model_carpet.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model_carpet.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model_carpet.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model_carpet.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model_carpet.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model_carpet.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model_carpet.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model_carpet.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model_carpet.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model_carpet.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model_carpet.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model_carpet.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model_carpet.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model_carpet.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model_carpet.setOffer_User_address(jsonObject2.getString("user_address"));
//                        offer_model.setOffer_main_page(jsonObject2.getString("main_page"));


//                        Log.i("gdasdasda54666545",jsonObject2.getString("offer_image_url")+",");
                        List_Offers_carpet.add(offer_model_carpet);

                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                SettingUpOffers_adapter_carpet(List_Offers_carpet);
//                loading_container.setVisibility(View.GONE);
//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };



        requestQueue_offer_carpet= Volley.newRequestQueue(this);
        requestQueue_offer_carpet.add(request_offer1);

    }

    //offer json request
    private void jsonRequest_offers_electronics()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);

        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS_ELECTRONICS, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_offer_json",jsonObject+"");

                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");

                        electronics_model offer_model_electronic=new electronics_model();

                        offer_model_electronic.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model_electronic.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model_electronic.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model_electronic.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model_electronic.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model_electronic.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model_electronic.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model_electronic.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model_electronic.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model_electronic.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model_electronic.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model_electronic.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model_electronic.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model_electronic.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model_electronic.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model_electronic.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model_electronic.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model_electronic.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model_electronic.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model_electronic.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model_electronic.setOffer_User_address(jsonObject2.getString("user_address"));
//                        offer_model.setOffer_main_page(jsonObject2.getString("main_page"));


//                        Log.i("gdasdasda54666545",jsonObject2.getString("offer_image_url")+",");
                        List_Offers_electronics.add(offer_model_electronic);

                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

                SettingUpOffers_adapter_electronics(List_Offers_electronics);
//                loading_container.setVisibility(View.GONE);
//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };




        requestQueue_offer_clothes= Volley.newRequestQueue(this);
        requestQueue_offer_clothes.add(request_offer1);

    }

    //offer json request
    private void jsonRequest_offers_clothes()
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);

        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_OFFERS_CLOTHES, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_offer_json",jsonObject+"");


                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
                        data_layout.setVisibility(View.VISIBLE);
                        data_server.setVisibility(View.GONE);
                        scroll_container.setVisibility(View.GONE);
                    }


                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");

                        clothes_model offer_model_clothes=new clothes_model();

                        offer_model_clothes.setOffer_shop_id(jsonObject2.getString("offer_shop_id"));
                        offer_model_clothes.setOffer_shop_country(jsonObject2.getString("offer_shop_country"));
                        offer_model_clothes.setOffer_shop_government(jsonObject2.getString("offer_shop_gov"));
                        offer_model_clothes.setOffer_shop_city(jsonObject2.getString("offer_shop_city"));
                        offer_model_clothes.setOffer_shop_shop_activity(jsonObject2.getString("offer_shop_shop_activity"));

                        offer_model_clothes.setOffer_shop_name(jsonObject2.getString("offer_shop_name"));
                        offer_model_clothes.setOffer_shop_address(jsonObject2.getString("offer_shop_address"));
                        offer_model_clothes.setOffer_description(jsonObject2.getString("offer_shop_description"));
                        offer_model_clothes.setOffer_shop_user_id(jsonObject2.getString("offer_shop_user_id"));
                        offer_model_clothes.setOffer_shop_photo(jsonObject2.getString("offer_shop_photo"));
                        offer_model_clothes.setOffer_shop_lat(jsonObject2.getString("offer_shop_lat"));
                        offer_model_clothes.setOffer_shop_log(jsonObject2.getString("offer_shop_log"));
                        offer_model_clothes.setOffer_shop_allowed_products(jsonObject2.getString("offer_shop_allowed_products"));
                        offer_model_clothes.setOffer_shop_allowed_offers(jsonObject2.getString("offer_shop_allowed_offers"));

                        offer_model_clothes.setOffer_User_id(jsonObject2.getString("user_id"));
                        offer_model_clothes.setOffer_User_name(jsonObject2.getString("user_user_name"));
                        offer_model_clothes.setOffer_User_email(jsonObject2.getString("user_email"));
                        offer_model_clothes.setOffer_User_photo(jsonObject2.getString("user_photo"));
                        offer_model_clothes.setOffer_User_telephone1(jsonObject2.getString("user_telephone1"));
                        offer_model_clothes.setOffer_User_telephone2(jsonObject2.getString("user_telephone2"));
                        offer_model_clothes.setOffer_User_address(jsonObject2.getString("user_address"));
//                        offer_model.setOffer_main_page(jsonObject2.getString("main_page"));


//                        Log.i("gdasdasda54666545",jsonObject2.getString("offer_image_url")+",");
                        List_Offers_clothes.add(offer_model_clothes);

                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                SettingUpOffers_adapter_clothes(List_Offers_clothes);
//                loading_container.setVisibility(View.GONE);
//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_shops");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);

                return params;
            }
        };


        requestQueue_offer_electronics= Volley.newRequestQueue(this);
        requestQueue_offer_electronics.add(request_offer1);

    }

    //setting the offer adapter
    private void SettingUpOffers_adapter(List<Offer_model> List_offer) {
        //setting the offer adapter
        offersAdapter off_adapter=new offersAdapter(this,List_offer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        offerSliderViewPager.setLayoutManager(layoutManager);
        offerSliderViewPager.setAdapter(off_adapter);


    }

    //setting the all shop adapter
    private void SettingUpOffers_adapter_all_shops(List<all_shop_model> List_offer_all_shop) {
        //setting the advertisement adapter
        AllShopAdapter off_adapter_shops=new AllShopAdapter(this,List_offer_all_shop);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        all_shop_offerSliderViewPager.setLayoutManager(layoutManager);
        all_shop_offerSliderViewPager.setAdapter(off_adapter_shops);


    }


    //setting the resturant adapter
    private void SettingUpOffers_adapter_resturant(List<resturant_model> List_offer_res) {
        //setting the advertisement adapter
        ResturantAdapter off_adapter_shops=new ResturantAdapter(this,List_offer_res);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        resturant_offerSliderViewPager.setLayoutManager(layoutManager);
        resturant_offerSliderViewPager.setAdapter(off_adapter_shops);


    }

    //setting the carpet adapter
    private void SettingUpOffers_adapter_carpet(List<carpet_model> List_offer_carpet) {
        //setting the advertisement adapter
        CarpetsAdapter off_adapter_shops=new CarpetsAdapter(this,List_offer_carpet);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        carpet_offerSliderViewPager.setLayoutManager(layoutManager);
        carpet_offerSliderViewPager.setAdapter(off_adapter_shops);


    }

    //setting the electronics adapter
    private void SettingUpOffers_adapter_electronics(List<electronics_model> List_offer_electronics) {
        //setting the advertisement adapter
        ElectronicsAdapter off_adapter_shops=new ElectronicsAdapter(this,List_offer_electronics);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        electronics_offerSliderViewPager.setLayoutManager(layoutManager);
        electronics_offerSliderViewPager.setAdapter(off_adapter_shops);


    }


    //setting the clothes adapter
    private void SettingUpOffers_adapter_clothes(List<clothes_model> List_offer_clothes) {
        //setting the advertisement adapter
        ClothesAdapter off_adapter_shops=new ClothesAdapter(this,List_offer_clothes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        clothes_offerSliderViewPager.setLayoutManager(layoutManager);
        clothes_offerSliderViewPager.setAdapter(off_adapter_shops);


    }



    //timer class
    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    if(offerSliderViewPager.getCurrentItem()==0){
//                        offerSliderViewPager.setCurrentItem(1);
//                    }else if(offerSliderViewPager.getCurrentItem()==1)
//                    {
//                        offerSliderViewPager.setCurrentItem(2);
//                    }else{
//                        offerSliderViewPager.setCurrentItem(0);
//                    }
//1
//                    Log.i("sdasdqwqwq",""+AdvertisementSliderViewPager.getAdapter().getCount());
                    try{
                        loading_container.setVisibility(View.GONE);
                        if(AdvertisementSliderViewPager.getCurrentItem()==0){
                            AdvertisementSliderViewPager.setCurrentItem(1);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==1)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==1)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(2);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==2)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==2)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(3);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==3)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==3)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(4);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==4)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==4)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(5);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==5)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==5)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(6);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==6)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==6)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(7);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==7)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==7)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(8);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==8)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==8)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(9);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==9)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==9)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(10);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==10)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==10)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(11);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==11)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==11)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(12);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==12)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==12)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(13);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==13)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==13)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(14);
                            if(AdvertisementSliderViewPager.getAdapter().getCount()==14)
                            {
                                AdvertisementSliderViewPager.setCurrentItem(0);
                            }
                        }else if(AdvertisementSliderViewPager.getCurrentItem()==14)
                        {
                            AdvertisementSliderViewPager.setCurrentItem(0);
                        }else
                        {
                            AdvertisementSliderViewPager.setCurrentItem(0);
                        }

                    }catch (Exception ex)
                    {
                        AdvertisementSliderViewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }


    /********************************************************************************/
    public void Show_pop_up_pro_de(String IMG_URL,String ADVER_TITLE,String ADVER_DESC) {

        Log.i("error_null_ahmed_salem",IMG_URL+"/"+ADVER_TITLE+"/"+ADVER_DESC);
        RequestOptions option_adv;
        option_adv=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.loading_gif);
        View close;
        Discover_shop_dialog1.setContentView(R.layout.advertisement_details_popup_zooming);
        advertisement_image_zoom=Discover_shop_dialog1.findViewById(R.id.adv_image_zooming);
        advertisement_title_zoom=Discover_shop_dialog1.findViewById(R.id.name_adv);
        advertisement_description_zoom=Discover_shop_dialog1.findViewById(R.id.description_adv);
//        Glide.with(this).load(IMG_URL).apply(option_adv).into(advertisement_image_zoom);
//        Picasso.get().load(IMG_URL).placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1).into(advertisement_image_zoom);
        advertisement_title_zoom.setText(ADVER_TITLE);
        advertisement_description_zoom.setText(ADVER_DESC);
        advertisement_title_zoom.setTypeface(tf);
        advertisement_description_zoom.setTypeface(tf);
        close = (View) Discover_shop_dialog1.findViewById(R.id.close_btn_adv2);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog1.dismiss();
            }
        });

        Glide.with(this).downloadOnly().load(IMG_URL)
                /* todo replace error icon */
                .apply(option_adv)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        advertisement_image_zoom.setImage(ImageSource.uri(Uri.fromFile(resource)));
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });


        //show Dialog
        Discover_shop_dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Discover_shop_dialog1.show();


//        Toast.makeText(getApplicationContext(),"ahmed salem",Toast.LENGTH_LONG).show();

    }

    /********************************************************************************/

    /********************************************************************************/
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    /********************************************************************************/

    /********************************************************************************/
    /********************************************************************************/
    //mainsearch json request
    private void jsonRequest_Main_Search(final String search)
    {

        SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        final String govenment_sh=user_setting.getString("government_user_set","");
        final String city_sh=user_setting.getString("city_user_set","");


        Log.i("ahmed_salem_main",city_sh+govenment_sh);


        StringRequest request_offer1=new StringRequest(Request.Method.POST,JSON_URL_MAIN_SEARCH, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.i("ahmedSalem_offer_json",jsonObject+"");
                    if(jsonObject.length()!=0)
                    {

                        internet_layout.setVisibility(View.GONE);
                        data_layout.setVisibility(View.GONE);
                    }else{
//                        data_layout.setVisibility(View.VISIBLE);
//                        data_server.setVisibility(View.GONE);
//                        scroll_container.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < jsonObject.length(); i++)
                    {


                        jsonObject2=jsonObject.getJSONObject(i+"");
                        ShopSearch shop_model=new ShopSearch();
                        shop_model.setType(jsonObject2.getString("type"));
                        if(jsonObject2.getString("type").equals("shop"))
                        {
                            //shop
                            shop_model.setShop_id(jsonObject2.getString("shop_id"));
                            shop_model.setShop_name(jsonObject2.getString("shop_name"));
                            shop_model.setShop_address(jsonObject2.getString("shop_address"));
                            shop_model.setShop_allowed_offers(jsonObject2.getString("shop_allowed_offers"));
                            shop_model.setShop_allowed_product(jsonObject2.getString("shop_allowed_product"));
                            shop_model.setShop_country(jsonObject2.getString("shop_country"));
                            shop_model.setShop_city(jsonObject2.getString("shop_city"));
                            shop_model.setShop_description(jsonObject2.getString("shop_description"));
                            shop_model.setShop_government(jsonObject2.getString("shop_government"));
                            shop_model.setShop_lat(jsonObject2.getString("shop_lat"));
                            shop_model.setShop_log(jsonObject2.getString("shop_log"));
                            shop_model.setShop_shop_activity(jsonObject2.getString("shop_shop_activity"));
                            shop_model.setShop_user_id(jsonObject2.getString("shop_user_id"));
                            shop_model.setShop_photo(jsonObject2.getString("shop_photo"));
                            List_shops.add(shop_model);
                        }else if(jsonObject2.getString("type").equals("offer"))
                        {
                            //offers
                            shop_model.setOffer_id(jsonObject2.getString("offer_id"));
                            shop_model.setOffer_name(jsonObject2.getString("offer_name"));
                            shop_model.setOffer_description(jsonObject2.getString("offer_description"));
                            shop_model.setOffer_photo(jsonObject2.getString("offer_photo"));
                            shop_model.setShop_user_id(jsonObject2.getString("user_id"));
                            shop_model.setShop_id(jsonObject2.getString("shop_id"));
                            shop_model.setUser_name(jsonObject2.getString("user_name"));
                            shop_model.setEmail(jsonObject2.getString("email"));
                            shop_model.setPhoto(jsonObject2.getString("user_photo"));
                            shop_model.setAddress(jsonObject2.getString("user_address"));
                            shop_model.setTelephone1(jsonObject2.getString("telephone1"));
                            shop_model.setTelephone2(jsonObject2.getString("telephone2"));
                            List_shops.add(shop_model);

                        }else if(jsonObject2.getString("type").equals("product"))
                        {
                            //products
                            shop_model.setProduct_id(jsonObject2.getString("product_id"));
                            shop_model.setProduct_name(jsonObject2.getString("product_name"));
                            shop_model.setProduct_description(jsonObject2.getString("product_description"));
                            shop_model.setProduct_price(jsonObject2.getString("product_price"));
                            shop_model.setProduct_photo(jsonObject2.getString("product_photo"));
                            shop_model.setShop_user_id(jsonObject2.getString("user_id"));
                            shop_model.setShop_id(jsonObject2.getString("shop_id"));
                            shop_model.setUser_name(jsonObject2.getString("user_name"));
                            shop_model.setEmail(jsonObject2.getString("email"));
                            shop_model.setPhoto(jsonObject2.getString("user_photo"));
                            shop_model.setAddress(jsonObject2.getString("user_address"));
                            shop_model.setTelephone1(jsonObject2.getString("telephone1"));
                            shop_model.setTelephone2(jsonObject2.getString("telephone2"));
                            List_shops.add(shop_model);
                        }











                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                setting_main_search_adapter(List_shops);

//                offerSliderViewPager.removeViewAt(List_Offers.size()/2);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                internet_layout.setVisibility(View.VISIBLE);
                scroll_container.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_Search_list");
                params.put("country", "مصر");
                params.put("government", govenment_sh);
                params.put("city", city_sh);
                params.put("search", search);

                return params;
            }
        };



        requestQueue_offer= Volley.newRequestQueue(this);
        requestQueue_offer.add(request_offer1);

    }

    /********************************************************************************/
    /********************************************************************************/

    private void setting_main_search_adapter(List<ShopSearch> List_shop)
    {

        mRecycleViewAdapter=new MainSearchAdapter(this,List_shop);

        Search_Main.setLayoutManager(new LinearLayoutManager(this));
        Search_Main.removeAllViews();
        Search_Main.removeAllViewsInLayout();
        Search_Main.setAdapter(mRecycleViewAdapter);



    }



}
