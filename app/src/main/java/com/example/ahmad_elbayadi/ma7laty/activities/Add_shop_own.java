package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.ahmad_elbayadi.ma7laty.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Add_shop_own extends AppCompatActivity {
    private Dialog Discover_shop_dialog;
    private List<String> cityList;
    private String government_text_value,city_text_value,shop_activity_text_value;
    private Spinner gevernments,city,shop_activity;
    private Button take_photo,choose_from_device,end;

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
    private String[] default_list = new String[]{
            "أختر المدينه",
            "يجب أن تختار أولا المحافظة"
    };
    private TextView toolbar,shop_name,shop_description,spinner_gov;
    private Button add_shop_button,shop_photo_btn;
    private LinearLayout govenment_layout,city_layout;
    private EditText shop_name_et,shop_description_et,shop_address,shop_log,shop_lat;
    private Typeface tf;
    private Dialog myDialog;
    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    Bitmap bitmap, decoded,asd_bitmap;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    private ImageView shop_photo_preview;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 500;

    private LinearLayout lat_shop_container,log_shop_container;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/add_shop.php";
    private RequestQueue requestQueue_add_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_own);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_shop_22);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        //dialog this context
        Discover_shop_dialog=new Dialog(this);


        //casting android compontent screen
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_shop_22);
        shop_name=(TextView)findViewById(R.id.shop_name_add);
        shop_description=(TextView)findViewById(R.id.shop_description_add);
        spinner_gov=(TextView)findViewById(R.id.spinner_gov);
        govenment_layout=(LinearLayout)findViewById(R.id.user_shop_gov_container);
        city_layout=(LinearLayout)findViewById(R.id.user_shop_city_container);
        add_shop_button=(Button)findViewById(R.id.add_new_shop);
        shop_photo_btn=(Button)findViewById(R.id.photo_shop);
        shop_photo_preview=(ImageView)findViewById(R.id.shop_photo_reg);
        shop_name_et=(EditText) findViewById(R.id.shop_name_add);
        shop_description_et=(EditText) findViewById(R.id.shop_description_add);
        shop_address=(EditText) findViewById(R.id.shop_address_add);
        shop_log=(EditText) findViewById(R.id.log_shop_add);
        shop_lat=(EditText) findViewById(R.id.lat_shop_add);
        lat_shop_container=(LinearLayout)findViewById(R.id.lat_shop_container);
        log_shop_container=(LinearLayout)findViewById(R.id.log_shop_container);



        lat_shop_container.setVisibility(View.GONE);
        log_shop_container.setVisibility(View.GONE);
        //casting the governments spinner
        gevernments = (Spinner) findViewById(R.id.government_spine_pop_up22);
        city = (Spinner) findViewById(R.id.city_spine_pop_up22);
        cityList = new ArrayList<>(Arrays.asList(default_list));
        shop_activity = (Spinner) findViewById(R.id.activity_spine_pop_up22);


        /************************** start default city list *****************************************/
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                ((TextView) v).setTypeface(tf);

                return v;
            }

            @Override
            public boolean isEnabled(int position){
                if(position == 0 && position==1)
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
                else if(position==1){
                    tv.setTextColor(Color.RED);
                }
                return view;
            }
        };
        spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
        city.setAdapter(spinnerArrayAdapter_city);
        /************************** end default city list *****************************************/






        shop_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImagev2();
            }
        });
        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting the font face
        toolbar.setTypeface(tf);
        shop_name.setTypeface(tf);
        shop_description.setTypeface(tf);
        add_shop_button.setTypeface(tf);
        shop_photo_btn.setTypeface(tf);
        shop_address.setTypeface(tf);
        shop_log.setTypeface(tf);
        shop_lat.setTypeface(tf);

//        spinner_gov.setTypeface(tf);

        //reset button
        FloatingActionButton fab = findViewById(R.id.fab_add_shop);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shop_name_et.setText("");
                shop_description_et.setText("");
                shop_address.setText("");
                shop_log.setText("");
                shop_lat.setText("");
                govenment_layout.setVisibility(View.VISIBLE);
                city_layout.setVisibility(View.VISIBLE);
                gevernments.setSelection(0);
                city.setSelection(0);
                shop_activity.setSelection(0);
                shop_photo_preview.setImageResource(R.drawable.store);

                Snackbar.make(view, "تم مسح كل القيم", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                    else if(government_text_value.equals("محافظة بورسعيد"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(port_said_cities_list10));
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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
                        govenment_layout.setVisibility(View.VISIBLE);
                        city_layout.setVisibility(View.VISIBLE);

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

//                    Toast.makeText(getApplicationContext(),"من فضلك أختر المحافظة",Toast.LENGTH_LONG).show();
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


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        add_shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                &&shop_lat.getText().toString()!=null&&shop_log.getText().toString()!=null
                if(decoded!=null&& government_text_value!=null&&!government_text_value.equals("أختر المحافظة")&&!city_text_value.equals("اختر المدينة")&&!city_text_value.equals("يجب أن تختار أولا المحافظة") &&city_text_value!=null&&shop_activity_text_value!=null&&shop_name_et.getText().toString()!=null&&shop_description_et.getText().toString()!=null&&shop_address.getText().toString()!=null)
                {
//                    Toast.makeText(getApplicationContext(),""+government_text_value+"/"+city_text_value+"/"+shop_activity_text_value,Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),""+shop_name_et.getText().toString()+"/"+shop_description_et.getText().toString()+"/"+shop_address.getText().toString()+"/"+shop_lat.getText().toString()+"/"+shop_log.getText().toString(),Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),"image not null",Toast.LENGTH_LONG).show();
                    //call json function
                    jsonRequest_add_shop(shop_name_et.getText().toString(),shop_address.getText().toString(),shop_description_et.getText().toString(),"23","23",government_text_value,city_text_value,shop_activity_text_value);








                }else{






                    Toast.makeText(getApplicationContext(),"يجب إدخال كل القيم",Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    //select image
    private void selectImagev2()
    {
        TextView close,title;
        Discover_shop_dialog.setContentView(R.layout.select_image);
        close=(TextView) Discover_shop_dialog.findViewById(R.id.close_btn);
        title=(TextView) Discover_shop_dialog.findViewById(R.id.popup_title);



        take_photo = (Button) Discover_shop_dialog.findViewById(R.id.action_edit_shop_btn_nav);
        choose_from_device = (Button) Discover_shop_dialog.findViewById(R.id.action_edit_shop_btn_photo_nav);
        end = (Button) Discover_shop_dialog.findViewById(R.id.action_exit_shop_btn_nav);

        title.setTypeface(tf);
        take_photo.setTypeface(tf);
        choose_from_device.setTypeface(tf);
        end.setTypeface(tf);
        take_photo.setVisibility(View.GONE);
        /***** start take photo button **/
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"حدث خطأ اثناء فتح الكاميرا",Toast.LENGTH_LONG).show();
                }


            }
        });
        /***** end take photo button **/
        /***** start choose_from_device button **/
        choose_from_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
            }
        });
        /***** end choose_from_device button **/
        /**** start close dialog ***/
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover_shop_dialog.dismiss();
            }
        });
        /**** end close dialog ***/


        //show Dialog
        Discover_shop_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Discover_shop_dialog.show();

    }
    private void selectImage() {
        shop_photo_preview.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Add_shop_own.this);

        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    asd_bitmap=getResizedBitmap(bitmap, max_resolution_image);
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(Add_shop_own.this.getContentResolver(), data.getData());
                    asd_bitmap=getResizedBitmap(bitmap, max_resolution_image);
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Untuk menampilkan bitmap pada ImageView
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        shop_photo_preview.setImageBitmap(decoded);
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                Toast.makeText(getApplicationContext(),"حدث خطأ اثناء فتح الكاميرا",Toast.LENGTH_LONG).show();
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");

        return mediaFile;
    }



    //json request
    private void jsonRequest_add_shop(final String shop_name,final String shop_address,final String shop_descrption,final String shop_lat,final String shop_log,final String shop_gov,final String shop_city,final String shop_activity)
    {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.getWindow().setGravity(Gravity.RIGHT);
        progress.setMessage("جاري التحميل...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");



        if(bitmap==null){
//            Toast.makeText(getApplicationContext(),"null ya 5awal",Toast.LENGTH_LONG).show();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        decoded.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


//        Toast.makeText(getApplicationContext(),"json function is work",Toast.LENGTH_LONG).show();


        StringRequest request_reg=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("add_shop_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("add_shop_flag").equals("1"))
                    {
//                        intent to activation code activity
                        Intent activation=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(activation);
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
                progress.cancel();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "add_shops");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("add_shop_name", shop_name);
                params.put("add_address", shop_address);
                params.put("country", "مصر");
                params.put("latitude_name", shop_lat);
                params.put("longitude_name", shop_log);
                params.put("government", shop_gov);
                params.put("city", shop_city);
                params.put("shop_description", shop_descrption);
                params.put("shop_activity", shop_activity);
                params.put("image_shop", imageString);

                return params;
            }

        };



        requestQueue_add_shop= Volley.newRequestQueue(this);
        requestQueue_add_shop.add(request_reg);
    }

}
