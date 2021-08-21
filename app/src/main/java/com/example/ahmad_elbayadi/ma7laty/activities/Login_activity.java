package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class Login_activity extends AppCompatActivity {

    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/login/login.php";
    private RequestQueue requestQueue_login_action;
    private Typeface Bien;
    private EditText login_Email,Login_password;
    private TextView new_user,forgot_passweord;
    private Button login_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        final RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.login_activity);

        // Font path
        String fontPath = "beinarnormal.ttf";

        //casting screen component
        login_Email=(EditText)findViewById(R.id.email_address);
        Login_password=(EditText)findViewById(R.id.password);
        new_user=(TextView)findViewById(R.id.new_user);
        forgot_passweord=(TextView)findViewById(R.id.forgotPassword);
        login_submit=(Button)findViewById(R.id.login_btn);


        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        login_Email.setTypeface(tf);
        Login_password.setTypeface(tf);
        new_user.setTypeface(tf);
        forgot_passweord.setTypeface(tf);
        login_submit.setTypeface(tf);

        forgot_passweord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fogot_pass=new Intent(Login_activity.this,Forgot_Password.class);
                startActivity(fogot_pass);
            }
        });
        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest_login_action(login_Email.getText().toString(),Login_password.getText().toString(),relativeLayout);

            }
        });


        SharedPreferences user_info=getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String EMAIL=user_info.getString("user_email","");
        final String USER_NAME=user_info.getString("user_user_name","");
        final String USER_PHOTO=user_info.getString("user_photo","");

        if(EMAIL!=""&&USER_NAME!=""&&USER_PHOTO!="")
        {
            Toast.makeText(getApplicationContext(),"أنت بالفعل مسجل دخول باسم"+USER_NAME,Toast.LENGTH_LONG).show();
            Intent Main_private= new Intent(Login_activity.this,MainActivity_private.class);
            startActivity(Main_private);

        }else{
//            Toast.makeText(getApplicationContext(),"null information",Toast.LENGTH_LONG).show();
        }


        //adding new user
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_user_reg=new Intent(Login_activity.this,newUserResgister.class);
                startActivity(new_user_reg);
            }
        });
    }



    //get advertisements using volley

    private void jsonRequest_login_action(final String Email,final String password,final RelativeLayout relativeLayout) {


        StringRequest request_login_action=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {

                    jsonObject = new JSONObject(response);
                    Log.i("sjbdkasjd",jsonObject+"");
                    if(jsonObject.getString("login_flag").equals("a"))
                    {
                        SharedPreferences.Editor editor_login = getSharedPreferences("ACCOUNT_INFORMATION", MODE_PRIVATE).edit();
                        editor_login.putString("login_flag", "a");
                        editor_login.putString("user_id", jsonObject.getString("user_id"));
                        editor_login.putString("user_user_name", jsonObject.getString("user_user_name"));
                        editor_login.putString("user_type", jsonObject.getString("user_type"));
                        editor_login.putString("user_email", jsonObject.getString("user_email"));
                        editor_login.putString("user_password", jsonObject.getString("user_password"));
                        editor_login.putString("user_photo", jsonObject.getString("user_photo"));
                        editor_login.putString("user_status", jsonObject.getString("user_status"));
                        editor_login.putString("user_gender", jsonObject.getString("user_gender"));
                        editor_login.putString("user_telephone1", jsonObject.getString("user_telephone1"));
                        editor_login.putString("user_telephone2", jsonObject.getString("user_telephone2"));
                        editor_login.putString("user_address", jsonObject.getString("user_address"));
                        editor_login.putString("user_activated", jsonObject.getString("user_activated"));
                        editor_login.putString("message", jsonObject.getString("message"));

                        editor_login.apply();

                        Intent login=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(login);

                    }else if(jsonObject.getString("login_flag").equals("b"))
                    {
                        SharedPreferences.Editor editor_login = getSharedPreferences("ACCOUNT_INFORMATIONV2", MODE_PRIVATE).edit();
                        editor_login.putString("login_flag", "b");
                        editor_login.putString("user_id", jsonObject.getString("user_id"));
                        editor_login.putString("user_user_name", jsonObject.getString("user_user_name"));
                        editor_login.putString("user_type", jsonObject.getString("user_type"));
                        editor_login.putString("user_email", jsonObject.getString("user_email"));
                        editor_login.putString("user_password", jsonObject.getString("user_password"));
                        editor_login.putString("user_photo", jsonObject.getString("user_photo"));
                        editor_login.putString("user_status", jsonObject.getString("user_status"));
                        editor_login.putString("user_gender", jsonObject.getString("user_gender"));
                        editor_login.putString("user_telephone1", jsonObject.getString("user_telephone1"));
                        editor_login.putString("user_telephone2", jsonObject.getString("user_telephone2"));
                        editor_login.putString("user_address", jsonObject.getString("user_address"));
                        editor_login.putString("user_activated", jsonObject.getString("user_activated"));
                        editor_login.putString("message", jsonObject.getString("message"));


                        editor_login.apply();

                        Intent login=new Intent(getApplicationContext(),activation_activity.class);
                        login.putExtra("email",jsonObject.getString("user_email"));
                        startActivity(login);

                    }else if(jsonObject.getString("login_flag").equals("c")){
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
                        Toast.makeText(getApplicationContext(),"البريد الألكتروني أو كلمة المرور غير صحيحة",Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(relativeLayout, jsonObject.getString("message"), Snackbar.LENGTH_LONG)
                                .setAction("علم", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(view, "حاول مرة أخري", Snackbar.LENGTH_SHORT);
                                        snackbar1.show();
                                    }
                                });

                        snackbar.show();

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
                params.put("do_action", "login_normal");
                params.put("email", Email);
                params.put("password", password);


                return params;
            }
        };



        requestQueue_login_action= Volley.newRequestQueue(this);
        requestQueue_login_action.add(request_login_action);
    }

}
