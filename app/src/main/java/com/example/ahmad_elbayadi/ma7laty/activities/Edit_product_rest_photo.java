package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
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
import com.example.ahmad_elbayadi.ma7laty.models.Spacecraft;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Edit_product_rest_photo extends AppCompatActivity {

    private TextView toolbar,product_title,number_of_photos;
    private Button select_rest_photos,edit_product;

    private Typeface tf;
    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    Bitmap bitmap, decoded;
    Bitmap []bitmap_array;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 500;
    private String number_of_photo_value="0";
    //multiple images
    private ArrayList<String> filePaths=new ArrayList<String>();
    private GridView gv;
    private Spacecraft s;
    private ArrayList<Spacecraft> spacecrafts;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/edit_product_rest_photo.php";
    private RequestQueue requestQueue_edit_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_rest_photo);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_product_22_rest);
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

        //casting andoid component
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_product_22_rest);
        product_title=(TextView)findViewById(R.id.shop_title99ofpro_rest);
        number_of_photos=(TextView)findViewById(R.id.photo_number_rest);
        select_rest_photos=(Button)findViewById(R.id.photo_product2_rest);
        edit_product=(Button)findViewById(R.id.add_new_product_rest);

        gv= (GridView) findViewById(R.id.gv_rest);
        gv.setVisibility(View.GONE);




        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting the font face
        toolbar.setTypeface(tf);
        product_title.setTypeface(tf);
        number_of_photos.setTypeface(tf);
        select_rest_photos.setTypeface(tf);
        edit_product.setTypeface(tf);

        select_rest_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setVisibility(View.VISIBLE);
                filePaths.clear();

//                FilePickerBuilder.getInstance().setMaxCount(5)
//                        .setSelectedFiles(filePaths)
//                        .setActivityTheme(R.style.AppTheme2)
//                        .pickPhoto(Edit_product_rest_photo.this);

            }
        });

        //add product button
        edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spacecrafts.size()>=1)
                {
//                    Toast.makeText(getApplicationContext(),""+spacecrafts.size()+"/"+product_name.getText().toString()+"/"+product_price.getText().toString()+"/"+product_description.getText().toString(),Toast.LENGTH_LONG).show();
                    jsonRequest_add_product();
                }else
                {
//                    Toast.makeText(getApplicationContext(),"يجب أن تملا جميع القيم",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"يجب أن تختار صوره علي الأقل لتعديل الصور السابقة",Toast.LENGTH_LONG).show();

                }

            }
        });





    }



    //multiple images
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE:

                if(resultCode==RESULT_OK && data!=null)
                {
                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);

                    spacecrafts=new ArrayList<>();

                    try
                    {
                        for (String path:filePaths) {
                            s=new Spacecraft();
                            s.setName(path.substring(path.lastIndexOf("/")+1));

                            s.setUri(Uri.fromFile(new File(path)));

                            spacecrafts.add(s);
                        }

                        gv.setAdapter(new Custom_rest_photo_Adapter(this,spacecrafts));
                        Toast.makeText(Edit_product_rest_photo.this, "Total = "+String.valueOf(spacecrafts.size()), Toast.LENGTH_SHORT).show();
                        number_of_photos.setText("عدد الصور:"+String.valueOf(spacecrafts.size()));



                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }



                }

        }


    }

    */

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


    //add product

    //json request
    private void jsonRequest_add_product()
    {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Uploading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        //receive data
        SharedPreferences user_info = getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID = user_info.getString("user_id", "");
        final String EMAIL = user_info.getString("user_email", "");
        final String PASSWORD1 = user_info.getString("user_password", "");
        final String shop_id = getIntent().getExtras().getString("Shop_id");
        final String product_id = getIntent().getExtras().getString("product_id");
        bitmap_array=new Bitmap[spacecrafts.size()];
        for (int i=0;i<spacecrafts.size();i++)
        {
            try {
                bitmap_array[i] =getResizedBitmap((MediaStore.Images.Media.getBitmap(this.getContentResolver(), spacecrafts.get(i).getUri())), max_resolution_image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//        main_product_photo_preview.setImageBitmap(bitmap_array[0]);
        if(bitmap_array!=null)
        {
            Log.i("ahmed_012818",bitmap_array.length+"");
            number_of_photo_value=bitmap_array.length+"";
        }else{
            Log.i("ahmed_012818","null");

        }



        final String [] imageString=new String[spacecrafts.size()];
        for (int i=0;i<bitmap_array.length;i++)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap_array[i].compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            imageString[i] = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        }

        final JSONObject jsonObject_pr=new JSONObject();
        for(int i=0;i<bitmap_array.length;i++)
        {
            try {
                jsonObject_pr.put("photo"+i,imageString[i]);
                Log.i("image_0128193632",""+jsonObject_pr.getString("photo1"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        Toast.makeText(getApplicationContext(),"json function is work",Toast.LENGTH_LONG).show();


        StringRequest request_reg=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("product_photo_rest_flage"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsono_0128193632",jsonObject+"");
                    }
                    if(jsonObject.getString("product_photo_rest_flage").equals("1"))
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"حدث مشكلة أثناء الأتصال بالانترنت",Toast.LENGTH_LONG).show();
            }
        }){





            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String, String> params = new HashMap<>();
                params.put("do_action", "edit_rest_product_photo");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", shop_id);
                params.put("product_id", product_id);
                params.put("number_of_photos", number_of_photo_value);
                params.put("product_photos", jsonObject_pr.toString());



                return params;
            }

        };



        requestQueue_edit_product= Volley.newRequestQueue(this);
        requestQueue_edit_product.add(request_reg);
    }


}
