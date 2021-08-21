package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Edit_offer_info extends AppCompatActivity {

    private TextView toolbar,offer_title;
    private EditText offer_name_et,offer_description_et;
    private Button edit_offer;
    private String intent_shop_id,intent_offer_id,intent_offer_name,intent_offer_decription;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/edit_offer.php";
    private RequestQueue requestQueue_edit_offer_info;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edir_offer_info);
        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.offer_activity_toolbar_add_offer_2299);
        setSupportActionBar(toolbar_actual);

        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor_shop_details=getSharedPreferences("OFFER_INFORMATION_OWN", Context.MODE_PRIVATE);

                final String offer_id=editor_shop_details.getString("offer_id", "");
                final String offer_name_v=editor_shop_details.getString("offer_name_v", "");
                final String offer_description_v=editor_shop_details.getString("offer_description_v", "");
                final String offer_photo=editor_shop_details.getString("offer_photo", "");
                final String shop_id=editor_shop_details.getString("shop_id", "");

                Intent Main=new Intent(getApplicationContext(),offer_details_own.class);

                Main.putExtra("offer_id",offer_id);
                Main.putExtra("offer_name_v",offer_name_v);
                Main.putExtra("offer_description_v",offer_description_v);
                Main.putExtra("offer_photo",offer_photo);
                Main.putExtra("shop_id",shop_id);
                startActivity(Main);

            }
        });


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //casting android component
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_offer_2299);
        offer_title=(TextView)findViewById(R.id.offer_title99);
        offer_name_et=(EditText) findViewById(R.id.offer_name_add99);
        offer_description_et=(EditText)findViewById(R.id.shop_description_add99);
        edit_offer=(Button)findViewById(R.id.add_new_offer99);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        toolbar.setTypeface(tf);
        offer_title.setTypeface(tf);
        offer_name_et.setTypeface(tf);
        offer_description_et.setTypeface(tf);
        edit_offer.setTypeface(tf);


        //setting intent values
        intent_shop_id=getIntent().getExtras().getString("Shop_id");
        intent_offer_id=getIntent().getExtras().getString("offer_id");
        intent_offer_name=getIntent().getExtras().getString("offer_name");
        intent_offer_decription=getIntent().getExtras().getString("offer_description");


        offer_name_et.setText(intent_offer_name);
        offer_description_et.setText(intent_offer_decription);

        edit_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offer_name_et.getText().toString()!=null&&offer_description_et.getText().toString()!=null)
                {
//                    Toast.makeText(getApplicationContext(),"go to json",Toast.LENGTH_LONG).show();
                    jsonRequest_edit_offer(offer_name_et.getText().toString(),offer_description_et.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال كل القيم",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    //json request
    private void jsonRequest_edit_offer(final String offer_name12,final String offer_des)
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
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("offer_edit_flage"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("offer_edit_flage").equals("1"))
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
                params.put("do_action", "edit_offer");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", intent_shop_id);
                params.put("offer_id", intent_offer_id);
                params.put("offer_name", offer_name12);
                params.put("offer_description", offer_des);

                return params;
            }

        };



        requestQueue_edit_offer_info= Volley.newRequestQueue(this);
        requestQueue_edit_offer_info.add(request_reg);
    }

}
