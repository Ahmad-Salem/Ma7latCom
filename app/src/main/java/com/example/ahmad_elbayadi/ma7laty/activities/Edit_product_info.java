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

public class Edit_product_info extends AppCompatActivity {

    private TextView toolbar,product_title;
    private EditText product_name_et,product_price_et,product_description_et;
    private Button edit_product;
    private String intent_shop_id,intent_product_id,intent_product_name,intent_product_price,intent_product_description;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/edit_product.php";
    private RequestQueue requestQueue_edit_product_info;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_info);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_product_2299pro22);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        toolbar_actual.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences editor_shop_details=getSharedPreferences("PRODUCT_INFORMATION_OWN", Context.MODE_PRIVATE);

                final String product_id=editor_shop_details.getString("product_id", "");
                final String product_price_p=editor_shop_details.getString("product_price_p", "");
                final String product_name_p=editor_shop_details.getString("product_name_p", "");
                final String product_description_p=editor_shop_details.getString("product_description_p", "");
                final String shop_id=editor_shop_details.getString("shop_id", "");
                final String product_rest_photos=editor_shop_details.getString("product_rest_photos", "");

                Intent Main=new Intent(getApplicationContext(),Product_details_own.class);

                Main.putExtra("product_id",product_id);
                Main.putExtra("product_price",product_price_p);
                Main.putExtra("product_name",product_name_p);
                Main.putExtra("product_description",product_description_p);
                Main.putExtra("shop_id",shop_id);
                Main.putExtra("product_rest_photos",product_rest_photos);
                startActivity(Main);

            }
        });

        //casting android component
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_product_2299);
        product_title=(TextView)findViewById(R.id.product_title99);
        product_name_et=(EditText) findViewById(R.id.product_name_add99);
        product_price_et=(EditText) findViewById(R.id.product_price_add99);
        product_description_et=(EditText) findViewById(R.id.product_description_add99);
        edit_product=(Button) findViewById(R.id.add_new_product99);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        //setting the font face
        toolbar.setTypeface(tf);
        product_title.setTypeface(tf);
        product_name_et.setTypeface(tf);
        product_price_et.setTypeface(tf);
        product_description_et.setTypeface(tf);
        edit_product.setTypeface(tf);

        //setting intent values
        intent_shop_id=getIntent().getExtras().getString("Shop_id");
        intent_product_id=getIntent().getExtras().getString("product_id");
        intent_product_name=getIntent().getExtras().getString("product_name");
        intent_product_price=getIntent().getExtras().getString("product_price");
        intent_product_description=getIntent().getExtras().getString("product_description");

        product_name_et.setText(intent_product_name);
        product_price_et.setText(intent_product_price);
        product_description_et.setText(intent_product_description);

        edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product_name_et.getText().toString()!=null&&product_price_et.getText().toString()!=null&&product_description_et.getText().toString()!=null)
                {
//                    Toast.makeText(getApplicationContext(),"go to json",Toast.LENGTH_LONG).show();
                    jsonRequest_edit_product(product_name_et.getText().toString(),product_price_et.getText().toString(),product_description_et.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال كل القيم",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //json request
    private void jsonRequest_edit_product(final String product_name12,final String product_price12,final String product_des12)
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
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("product_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("product_flag").equals("1"))
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
                params.put("do_action", "edit_product");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", intent_shop_id);
                params.put("product_id", intent_product_id);
                params.put("add_product_name",product_name12);
                params.put("add_product_price", product_price12);
                params.put("product_description", product_des12);


                return params;
            }

        };



        requestQueue_edit_product_info= Volley.newRequestQueue(this);
        requestQueue_edit_product_info.add(request_reg);
    }

}
