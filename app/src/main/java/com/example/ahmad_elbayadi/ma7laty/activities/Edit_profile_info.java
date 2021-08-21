package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class Edit_profile_info extends AppCompatActivity {

    private TextView toolbar;
    private EditText user_name,password,re_password,address,phone1,phone2;
    private Button edit_profile;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/user_profile/edit_user_info.php";
    private RequestQueue requestQueue_edit_ptofile_info;

    private String USER_NAME;
    private String ID;
    private String EMAIL;
    private String PASSWORD;
    private String ADDRESS;
    private String PHONE1;
    private String PHONE2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_info);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar77);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //casting android component
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg77);
        user_name=(EditText)findViewById(R.id.user_name77);
        password=(EditText)findViewById(R.id.password77);
        re_password=(EditText)findViewById(R.id.re_password77);
        address=(EditText)findViewById(R.id.address77);
        phone1=(EditText)findViewById(R.id.phone177);
        phone2=(EditText)findViewById(R.id.phone277);
        edit_profile=(Button)findViewById(R.id.add_new_user77);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting typeface
        toolbar.setTypeface(tf);
        user_name.setTypeface(tf);
        password.setTypeface(tf);
        re_password.setTypeface(tf);
        address.setTypeface(tf);
        phone1.setTypeface(tf);
        phone2.setTypeface(tf);
        edit_profile.setTypeface(tf);

        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        USER_NAME=user_info.getString("user_user_name","");
        ID=user_info.getString("user_id","");
        EMAIL=user_info.getString("user_email","");
        PASSWORD=user_info.getString("user_password","");
        ADDRESS=user_info.getString("user_address","");
        PHONE1=user_info.getString("user_telephone1","");
        PHONE2=user_info.getString("user_telephone2","");

        user_name.setText(""+USER_NAME);
        password.setText(""+PASSWORD);
        re_password.setText(""+PASSWORD);
        address.setText(""+ADDRESS);
        phone1.setText(""+PHONE1);
        phone2.setText(""+PHONE2);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_name.getText().toString()!=null&&password.getText().toString()!=null&&re_password.getText().toString()!=null&&address.getText().toString()!=null&&phone1.getText().toString()!=null&&phone2.getText().toString()!=null)
                {
                   if(password.getText().toString().equals(re_password.getText().toString()))
                   {
                       //json request
                       jsonRequest_new_user_registeration(user_name.getText().toString(),password.getText().toString(),address.getText().toString(),phone1.getText().toString(),phone2.getText().toString());

                   }else{
                       Toast.makeText(getApplicationContext(),"كلمات المرور غير متطابقة",Toast.LENGTH_LONG).show();
                   }

                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال كل القيم",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private void jsonRequest_new_user_registeration(final String user_name_pr,final  String password_pr,final String address_pr,final String phone1_pr,final String phone2_pr)
    {

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Uploading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();



        StringRequest request_reg=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.getString("user_flag").equals("1"))
                    {
                        SharedPreferences.Editor editor_login = getSharedPreferences("ACCOUNT_INFORMATION", MODE_PRIVATE).edit();
                        editor_login.putString("login_flag", "a");
                        editor_login.putString("user_user_name", user_name_pr);
                        editor_login.putString("user_email", EMAIL);
                        editor_login.putString("user_password", password_pr);
                        editor_login.putString("user_telephone1", phone1_pr);
                        editor_login.putString("user_telephone2", phone2_pr);
                        editor_login.putString("user_address", address_pr);
                        editor_login.apply();

                        //intent to activation code activity
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
                params.put("do_action", "update_user_info");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", password_pr);
                params.put("user_name", user_name_pr);
                params.put("gender", "male");
                params.put("address", address_pr);
                params.put("telephone1", phone1_pr);
                params.put("telephone2", phone2_pr);
                return params;
            }

        };

        requestQueue_edit_ptofile_info= Volley.newRequestQueue(this);
        requestQueue_edit_ptofile_info.add(request_reg);
    }

}
