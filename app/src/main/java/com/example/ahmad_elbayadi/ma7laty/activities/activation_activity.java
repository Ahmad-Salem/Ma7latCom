package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

public class activation_activity extends AppCompatActivity {

    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/login/check_activation/resend_code_activation.php";
    private RequestQueue requestQueue_activation_action;
    private final String JSON_URL_activation_code="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/login/check_activation/check_activation.php";
    private RequestQueue requestQueue_activation_code_action;

    private TextView activation_title,resend_activation_code;
    private EditText activation_code;
    private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation_activity);

        //casting android component
        activation_title=(TextView) findViewById(R.id.activation_title);
        resend_activation_code=(TextView) findViewById(R.id.activation_title_link);
        activation_code=(EditText) findViewById(R.id.activation_code_value);
        login_btn=(Button)findViewById(R.id.login_btn_code1);
        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //set TypeFace
        activation_title.setTypeface(tf);
        resend_activation_code.setTypeface(tf);
        activation_code.setTypeface(tf);
        login_btn.setTypeface(tf);

        resend_activation_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_login_action();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_activation_code_action(activation_code.getText().toString());
            }
        });

    }



    //get advertisements using volley

    private void jsonRequest_login_action() {

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATIONV2", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD=user_info.getString("user_password","");

        StringRequest request_activation_action=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {

                    jsonObject = new JSONObject(response);
                    Log.i("sjbdkasjd",jsonObject+"");
                    if(jsonObject.getString("activation_flag").equals("1"))
                    {
                        //activation code sended
                        Toast.makeText(getApplicationContext(),"كود التفعيل أرسل بنجاح.",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"حدث خطأ أثناء إرسال كود التفعيل.",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"حدث مشكلة بالأتصال بالأنترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "send_activation_code");
                params.put("email", EMAIL);

                return params;
            }
        };


//        Toast.makeText(getApplicationContext(),""+ID+"/"+EMAIL+"/"+PASSWORD,Toast.LENGTH_LONG).show();
        requestQueue_activation_action= Volley.newRequestQueue(this);
        requestQueue_activation_action.add(request_activation_action);
    }

    //get advertisements using volley

    private void jsonRequest_activation_code_action(final String Activation_code) {

        //receive data
        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATIONV2", Context.MODE_PRIVATE);
        final String ID=user_info.getString("user_id","");
        final String EMAIL=user_info.getString("user_email","");
        final String PASSWORD=user_info.getString("user_password","");




        StringRequest request_activation_action=new StringRequest(Request.Method.POST,JSON_URL_activation_code, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {

                    jsonObject = new JSONObject(response);
                    Log.i("sjbdkasjd",jsonObject+"");
                    if(jsonObject.getString("check_activation_flag").equals("1"))
                    {
                        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATIONV2", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor_login = getSharedPreferences("ACCOUNT_INFORMATION", MODE_PRIVATE).edit();
                        editor_login.putString("login_flag", "a");
                        editor_login.putString("user_id", user_info.getString("user_id",""));
                        editor_login.putString("user_user_name", user_info.getString("user_user_name",""));
                        editor_login.putString("user_type", user_info.getString("user_type",""));
                        editor_login.putString("user_email", user_info.getString("user_email",""));
                        editor_login.putString("user_password", user_info.getString("user_password",""));
                        editor_login.putString("user_photo", user_info.getString("user_photo",""));
                        editor_login.putString("user_status", user_info.getString("user_status",""));
                        editor_login.putString("user_gender", user_info.getString("user_gender",""));
                        editor_login.putString("user_telephone1", user_info.getString("user_telephone1",""));
                        editor_login.putString("user_telephone2", user_info.getString("user_telephone2",""));
                        editor_login.putString("user_address", user_info.getString("user_address",""));
                        editor_login.putString("user_activated", user_info.getString("user_activated",""));
                        editor_login.putString("message", user_info.getString("message",""));
                        editor_login.apply();


                        //activation code sended
                        Intent login=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(login);

                    }else{
                        Toast.makeText(getApplicationContext(),"كود التفعيل غير صحيح",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"حدث مشكلة بالأتصال بالأنترنت",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "check_activation");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD);
                params.put("activation_code", Activation_code);


                return params;
            }
        };


//        Toast.makeText(getApplicationContext(),""+ID+"/"+EMAIL+"/"+PASSWORD,Toast.LENGTH_LONG).show();
        requestQueue_activation_code_action= Volley.newRequestQueue(this);
        requestQueue_activation_code_action.add(request_activation_action);
    }

}
