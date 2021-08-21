package com.example.ahmad_elbayadi.ma7laty.user_general_setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
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
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.activities.MainActivity;
import com.example.ahmad_elbayadi.ma7laty.models.city;
import com.example.ahmad_elbayadi.ma7laty.models.government;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class general_setting extends AppCompatActivity {
    private Typeface tf;
    private Spinner government_sp,city_sp;
    private TextView toolbar_mahalty_setting,title_setting_tv1;
    private String government_text_value,city_text_value;
    private List<String> cityList;
    private Button main_menu;
    private final String JSON_URL_PLACES="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a//API/get_places_information/get_places.php";
    private RequestQueue requestQueue_places;
    private List<com.example.ahmad_elbayadi.ma7laty.models.government> List_government;
    private List<com.example.ahmad_elbayadi.ma7laty.models.city> List_cities;
    private List<String> List_government_modified;
    private List<String> List_government_activation_modified;
    private List<String> List_government_id_modified;
    private List<city> List_city_modified;
    private List<String> List_city_name_modified;
    private List<String> List_city_activation_modified;
    private RelativeLayout mRelativeLayoutPrgressBar;


    // Initializing a String Array government
    private String[] governments_list = new String[]{
            "أختر المحافظة"
    };

    private String[] default_list = new String[]{
        "أختر المدينه",
         "يجب أن تختار أولا المحافظة"
    };
    private List<String> govList;
    private ArrayAdapter<String> spinnerArrayAdapter_government;
    private RelativeLayout loading_container;
    private  RotateLoading rotateLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_general_setting);

        loading_container=(RelativeLayout)findViewById(R.id.loading_container);
        rotateLoading = findViewById(R.id.rotateloading);
        rotateLoading.start();

        List_government=new ArrayList<>();
        List_cities=new ArrayList<>();
        List_government_modified=new ArrayList<>();
        List_government_activation_modified=new ArrayList<>();
        List_government_id_modified=new ArrayList<>();
        List_city_modified=new ArrayList<>();

        jsonRequest_places();



        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //casting screen component
        toolbar_mahalty_setting=(TextView)findViewById(R.id.toolbar_title_setting);
        title_setting_tv1=(TextView)findViewById(R.id.title_setting_tv1);
        main_menu=(Button)findViewById(R.id.main_menu_btn);
        government_sp=(Spinner)findViewById(R.id.gov_spine_setting);
        city_sp=(Spinner)findViewById(R.id.city_spine_setting);
        cityList = new ArrayList<>(Arrays.asList(default_list));





//        gov_adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,
//                governments_list);
//        gov_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        government.setAdapter(gov_adapter);










        //setting the font
        toolbar_mahalty_setting.setTypeface(tf);
        title_setting_tv1.setTypeface(tf);
        main_menu.setTypeface(tf);

        SharedPreferences editor_shop_details=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);

        final String firsttime=editor_shop_details.getString("firsttime", "");
        final String government_user_set=editor_shop_details.getString("government_user_set", "");
        final String city_user_set=editor_shop_details.getString("city_user_set", "");




    }


    private void saveUserInfo(final String Government, final String City)
    {
        SharedPreferences  user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=user_setting.edit();

        editor.putString("firsttime","1");
        editor.putString("government_user_set",Government+"");
        editor.putString("city_user_set",City+"");
        editor.apply();
//        Toast.makeText(getApplicationContext(),""+Government+"/"+City,Toast.LENGTH_LONG).show();
        if(Government!=null&&City!=null&&!Government.equals("أختر المحافظة")&&!City.equals("أختر المدينة")&&!City.equals("يجب أن تختار أولا المحافظة"))
        {
            Toast.makeText(getApplicationContext(),"بأمكانك الذهاب للقائمة الرئيسية",Toast.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"gov is:"+Government,Toast.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"city is:"+City,Toast.LENGTH_LONG).show();
        }
        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Government!=null&&City!=null&&!Government.equals("أختر المحافظة")&&!City.equals("أختر المدينة")&&!City.equals("يجب أن تختار أولا المحافظة"))
                {

                    Intent main_menu_step=new Intent(general_setting.this,MainActivity.class);
                    startActivity(main_menu_step);

                }else{
                    Toast.makeText(getApplicationContext(),"يجب أن تختار المحافظة والمدينة...",Toast.LENGTH_LONG).show();
                }

            }
        });

    }



    private void jsonRequest_places() {



        StringRequest request_places=new StringRequest(Request.Method.POST,JSON_URL_PLACES, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                JSONObject jsonObject3 = null;
                JSONObject jsonObject4 = null;
                try {
                    jsonObject = new JSONObject(response);



                    for (int i = 0; i < jsonObject.length(); i++)
                    {

                        jsonObject2=jsonObject.getJSONObject(i+"");

                        government government_model=new government();

                        government_model.setGov_id(jsonObject2.getString("gov_id"));
                        government_model.setGov_name(jsonObject2.getString("gov_name"));
                        government_model.setGov_activation(jsonObject2.getString("gov_activation"));
//                        Toast.makeText(getApplicationContext(),""+jsonObject2.getJSONObject("city_info").length(),Toast.LENGTH_LONG).show();
                        jsonObject3=jsonObject2.getJSONObject("city_info");



                        Log.i("places_list565656",jsonObject2.getJSONObject("city_info").length()+"");


                        List_government.add(government_model);
                    }


                    //get city information
                    for(int j = 0; j < jsonObject3.length(); j++)
                    {
                        jsonObject4=jsonObject3.getJSONObject(j+"");
                        city city_model=new city();
                        city_model.setCity_id(jsonObject4.getString("city_id"));
                        city_model.setCity_name(jsonObject4.getString("city_name"));
                        city_model.setCity_activation(jsonObject4.getString("c_activation"));
                        city_model.setGov_id(jsonObject4.getString("c_gov_id"));

                        //set city in formation
                        List_city_modified.add(city_model);


                    }
                    Log.i("places_list565656",jsonObject3+"");
                    Log.i("places_list565656",List_city_modified.size()+"");
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

                List_government_modified.add("أختر المحافظة");
                List_government_activation_modified.add("0");
                List_government_id_modified.add("0");
                for(int i=0; i<List_government.size();i++)
                {
                    List_government_modified.add(List_government.get(i).getGov_name());
                    List_government_activation_modified.add(List_government.get(i).getGov_activation());
                    List_government_id_modified.add(List_government.get(i).getGov_id());

                }






                // Initializing an ArrayAdapter
                govList = new ArrayList<>((List_government_modified));

                Log.i("places_list565656",List_government.get(0).getGov_id()+"");
//                Log.i("places_list565656",List_government.get(0).getCity_list().getCity_id()+"");
                Log.i("places_list56565623",govList.get(0)+"");

                /**********************************************************************************************/
                /**********************************************************************************************/
                // Initializing an ArrayAdapter
                spinnerArrayAdapter_government= new ArrayAdapter<String>(
                        getApplicationContext(),R.layout.govermment_spin_prototype,govList){

                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);


                        ((TextView) v).setTypeface(tf);

                        return v;
                    }
                    @Override
                    public boolean isEnabled(int position){
                        //get government information

                        if(List_government_activation_modified.get(position).equals("0"))
                        {
                            return false;
                        }else{
                            return true;
                        }

                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTypeface(tf);
                        //get government information

                        if(List_government_activation_modified.get(position).equals("0"))
                        {
                            // Set the disable item text color
                            tv.setTextColor(Color.GRAY);
                        }else{
                            tv.setTextColor(Color.BLACK);
                        }

                        return view;
                    }
                };
                spinnerArrayAdapter_government.setDropDownViewResource(R.layout.gove_down_spin_prototype);

                government_sp.setAdapter(spinnerArrayAdapter_government);


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
                city_sp.setAdapter(spinnerArrayAdapter_city);


                //spinner on selected item
        government_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(!government_sp.getSelectedItem().toString().equalsIgnoreCase("أختر المحافظة")){
                    // Toast.makeText(ge    tApplicationContext(),"good selection",Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    //to get the text value of spinner
                    government_text_value = government_sp.getSelectedItem().toString();

//                    Log.i("govermentahmed",government_text_value);

//                    Toast.makeText(getApplicationContext(),List_city_modified.size()+"",Toast.LENGTH_LONG).show();
                    for(int i=0; i<List_city_modified.size();i++)
                    {
                        Log.i("city_details",List_city_modified.get(i).getCity_name()+"");
                    }

                    /**************************************************************************************/
                    /**************************************************************************************/
                    for(int i=1;i<govList.size()-1;i++)
                    {
                        if(government_text_value.equals(govList.get(i)))
                        {
//                            Toast.makeText(getApplicationContext(),"oki",Toast.LENGTH_LONG).show();
                            // Initializing an ArrayAdapter
//                            Toast.makeText(getApplicationContext(),"gov_id:"+List_city_modified.get(1).getGov_id(),Toast.LENGTH_LONG).show();

                            List_city_name_modified=new ArrayList<>();
                            List_city_activation_modified=new ArrayList<>();
                            List_city_name_modified.add("أختر المدينة");
                            List_city_activation_modified.add("0");
                            for(int j=0;j<List_city_modified.size();j++)
                            {
                                if(List_city_modified.get(j).getGov_id().equals(i+""))
                                {
                                    List_city_name_modified.add(List_city_modified.get(j).getCity_name());
                                    List_city_activation_modified.add(List_city_modified.get(j).getCity_activation());
                                }
                            }
                            cityList = new ArrayList<>(List_city_name_modified);
//                        government.setVisibility(View.GONE);
                            city_sp.setVisibility(View.VISIBLE);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter_city = new ArrayAdapter<String>(getApplicationContext(),R.layout.govermment_spin_prototype,cityList){

                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View v = super.getView(position, convertView, parent);


                                    ((TextView) v).setTypeface(tf);

                                    return v;
                                }

                                @Override
                                public boolean isEnabled(int position){
                                    if(List_city_activation_modified.get(position).equals("0"))
                                    {
                                        return false;
                                    }else
                                        {
                                            return  true;
                                        }
                                }

                                @Override
                                public View getDropDownView(int position, View convertView,
                                                            ViewGroup parent) {
                                    View view = super.getDropDownView(position, convertView, parent);
                                    TextView tv = (TextView) view;
                                    tv.setTypeface(tf);

                                    if(List_city_activation_modified.get(position).equals("0"))
                                    {
                                        tv.setTextColor(Color.GRAY);
                                    }else
                                    {
                                        tv.setTextColor(Color.BLACK);
                                    }

                                    return view;
                                }
                            };
                            spinnerArrayAdapter_city.setDropDownViewResource(R.layout.gove_down_spin_prototype);
                            city_sp.setAdapter(spinnerArrayAdapter_city);


                            city_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    // your code here
                                    city_text_value=city_sp.getSelectedItem().toString();
                                    //save usersetting
                                    saveUserInfo(government_text_value,city_text_value);
                                    if(!city_text_value.equals("اختر المدينة"))
                                    {
                                    //                    Intent Main =new Intent(general_setting.this, MainActivity.class);
                                    //                    startActivity(Main);
                                                                        }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }

                            });


                        }
                    }

                    /**************************************************************************************/
                    /**************************************************************************************/



                }else{

                    loading_container.setVisibility(View.GONE);
                    rotateLoading.stop();
                    Toast.makeText(getApplicationContext(),"من فضلك أختر المحافظة",Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


                /**********************************************************************************************/
                /**********************************************************************************************/


                loading_container.setVisibility(View.GONE);
                rotateLoading.stop();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"تأكد من اتصالك بالأنترنت..",Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "get_places");


                return params;
            }
        };



        requestQueue_places= Volley.newRequestQueue(this);
        requestQueue_places.add(request_places);
    }
}
