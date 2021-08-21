package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edit_shop_info extends AppCompatActivity {

    private List<String> cityList;
    private String government_text_value,city_text_value,shop_activity_text_value;
    private Spinner gevernments,city,shop_activity;

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
    private TextView toolbar,shop_name,shop_description,spinner_gov;
    private Button add_shop_button;
    private LinearLayout govenment_layout,city_layout;
    private EditText shop_name_et,shop_description_et,shop_address,shop_log,shop_lat;
    private Typeface tf;
    private String intent_Shop_id,intent_Shop_name,intent_Shop_address,intent_Shop_gov,intent_Shop_city,intent_Shop_activity,intent_Shop_description,intent_Shop_lat,intent_Shop_log;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/edit_shop.php";
    private RequestQueue requestQueue_edit_shop_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop_info);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_shop_2288);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("SHOP_INFORMATION_OWN", Context.MODE_PRIVATE);

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

                Intent Main=new Intent(getApplicationContext(),Shop_details_own.class);

                Main.putExtra("shop_id",shop_id);
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

        // for back button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //setting android component with intent data
        intent_Shop_id=getIntent().getExtras().getString("Shop_id");
        intent_Shop_name=getIntent().getExtras().getString("Shop_name");
        intent_Shop_address=getIntent().getExtras().getString("Shop_address");
        intent_Shop_gov=getIntent().getExtras().getString("Shop_gov");
        intent_Shop_city=getIntent().getExtras().getString("Shop_city");
        intent_Shop_activity=getIntent().getExtras().getString("Shop_activity");
        intent_Shop_description=getIntent().getExtras().getString("Shop_descreption");
        intent_Shop_lat=getIntent().getExtras().getString("Shop_lat");
        intent_Shop_log=getIntent().getExtras().getString("Shop_log");





        //casting android compontent screen
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_shop_2288);
        shop_name=(TextView)findViewById(R.id.shop_name_add88);
        shop_description=(TextView)findViewById(R.id.shop_description_add88);
        spinner_gov=(TextView)findViewById(R.id.spinner_gov);
        govenment_layout=(LinearLayout)findViewById(R.id.user_shop_gov_container88);
        city_layout=(LinearLayout)findViewById(R.id.user_shop_city_container88);
        add_shop_button=(Button)findViewById(R.id.add_new_shop88);
        shop_name_et=(EditText) findViewById(R.id.shop_name_add88);
        shop_description_et=(EditText) findViewById(R.id.shop_description_add88);
        shop_address=(EditText) findViewById(R.id.shop_address_add88);
        shop_log=(EditText) findViewById(R.id.log_shop_add88);
        shop_lat=(EditText) findViewById(R.id.lat_shop_add88);




        //casting the governments spinner
        gevernments = (Spinner) findViewById(R.id.government_spine_pop_up2288);
        city = (Spinner) findViewById(R.id.city_spine_pop_up2288);
        shop_activity = (Spinner) findViewById(R.id.activity_spine_pop_up2288);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        //setting the font face
        toolbar.setTypeface(tf);
        shop_name.setTypeface(tf);
        shop_description.setTypeface(tf);
        add_shop_button.setTypeface(tf);
        shop_address.setTypeface(tf);
        shop_log.setTypeface(tf);
        shop_lat.setTypeface(tf);



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
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }




                    }else if(government_text_value.equals("محافظة الجِيزَة"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Giza_cities_list));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة القليوبية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(kalyobiya_cities_list));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الإسكندرية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Alex_cities_list));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة البحيرة"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Elbehera_cities_list));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة مطروح"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(matroh_cities_list3));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة دمياط"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(domyat_cities_list4));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الدقهلية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(daqahliya_cities_list5));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة كفر الشيخ"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(kafrelsheish_cities_list6));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الغربية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(gharbiya_cities_list7));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة المنوفية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(monofiya_cities_list8));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الشرقية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Elsharkiya_cities_list9));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة بورسعيد"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(port_said_cities_list10));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الإسماعيلية"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(isamilai_cities_list11));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة السويس"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(suze_cities_list12));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة شمال سيناء"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(northofsinai_cities_list13));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة جنوب سيناء"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(southofsinai_cities_list14));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة بني سويف"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(bani_swaf_cities_list15));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الفيوم"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(fayuom_cities_list16));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة المنيا"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(qena_cities_list21));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة أسيوط"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(asout_cities_list18));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة البحر الأحمر"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Red_sea_cities_list19));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة سوهاج"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(Sohag_cities_list20));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة قنا"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(qena_cities_list21));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة الأقصر"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(louxor_cities_list22));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
                    }
                    else if(government_text_value.equals("محافظة أَسْوان"))
                    {
                        cityList = new ArrayList<>(Arrays.asList(aswan_cities_list23));
                        govenment_layout.setVisibility(View.GONE);
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
                        if (intent_Shop_city != null) {
                            int spinnerPosition = spinnerArrayAdapter_city.getPosition(intent_Shop_city);
                            city.setSelection(spinnerPosition);
                        }
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

        //setting intent data to ui componet
        shop_name_et.setText(intent_Shop_name);
        shop_address.setText(intent_Shop_address);
        shop_description_et.setText(intent_Shop_description);
        shop_lat.setText(intent_Shop_lat);
        shop_log.setText(intent_Shop_log);
        if (intent_Shop_gov != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(intent_Shop_gov);
            gevernments.setSelection(spinnerPosition);
        }
        if (intent_Shop_activity != null) {
            int spinnerPosition = spinnerArrayAdapter_shop_activity.getPosition(intent_Shop_activity);
            shop_activity.setSelection(spinnerPosition);
        }


        add_shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(government_text_value!=null&&city_text_value!=null&&shop_activity_text_value!=null&&shop_name_et.getText().toString()!=null&&shop_description_et.getText().toString()!=null&&shop_address.getText().toString()!=null&&shop_lat.getText().toString()!=null&&shop_log.getText().toString()!=null)
                {
//                    Toast.makeText(getApplicationContext(),""+government_text_value+"/"+city_text_value+"/"+shop_activity_text_value,Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),""+shop_name_et.getText().toString()+"/"+shop_description_et.getText().toString()+"/"+shop_address.getText().toString()+"/"+shop_lat.getText().toString()+"/"+shop_log.getText().toString(),Toast.LENGTH_LONG).show();
                    jsonRequest_edit_shop(shop_name_et.getText().toString(),shop_address.getText().toString(),shop_description_et.getText().toString(),shop_lat.getText().toString(),shop_log.getText().toString(),government_text_value,city_text_value,shop_activity_text_value);
                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال كل القيم",Toast.LENGTH_LONG).show();
                }
            }
        });


                }


    //json request
    private void jsonRequest_edit_shop(final String shop_name,final String shop_address,final String shop_descrption,final String shop_lat,final String shop_log,final String shop_gov,final String shop_city,final String shop_activity)
    {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Uploading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD1=user_info.getString("user_password","");





//        Toast.makeText(getApplicationContext(),"json function is work",Toast.LENGTH_LONG).show();


        StringRequest request_reg=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("edit_shop_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("edit_shop_flag").equals("1"))
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
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "edit_shop");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", intent_Shop_id);
                params.put("add_shop_name", shop_name);
                params.put("add_address", shop_address);
                params.put("country", "مصر");
                params.put("latitude_name", shop_lat);
                params.put("longitude_name", shop_log);
                params.put("government", shop_gov);
                params.put("city", shop_city);
                params.put("shop_description", shop_descrption);
                params.put("shop_activity", shop_activity);

                return params;
            }

        };



        requestQueue_edit_shop_info= Volley.newRequestQueue(this);
        requestQueue_edit_shop_info.add(request_reg);
    }

}
