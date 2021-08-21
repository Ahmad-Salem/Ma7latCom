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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.ahmad_elbayadi.ma7laty.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Edit_shop_photo extends AppCompatActivity {


    private TextView toolbar,edit_title;
    private Button select_photo,edit_shop;
    private ImageView shop_photo_preview;
    private Typeface tf;
    private RequestOptions option;
    private String intent_Shop_id,intent_Shop_photo;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/edit_shop_main_photo.php";
    private RequestQueue requestQueue_edit_shop_photo;
    private Dialog Discover_shop_dialog;
    private Button take_photo,choose_from_device,end;

    Intent intent;
    Uri fileUri;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop_photo);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_shop_2299);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        //dialog this context
        Discover_shop_dialog=new Dialog(this);

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

        //casting android component
        toolbar=(TextView)findViewById(R.id.toolbar_title_reg_add_shop_2299);
        edit_title=(TextView)findViewById(R.id.shop_title99);
        select_photo=(Button)findViewById(R.id.photo_shop99);
        edit_shop=(Button)findViewById(R.id.add_new_shop99);
        shop_photo_preview=(ImageView)findViewById(R.id.shop_photo_reg99);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        toolbar.setTypeface(tf);
        edit_title.setTypeface(tf);
        select_photo.setTypeface(tf);
        edit_shop.setTypeface(tf);

        //reciving information from intent don't wory
        //setting android component with intent data
        intent_Shop_id=getIntent().getExtras().getString("Shop_id");
        intent_Shop_photo=getIntent().getExtras().getString("Shop_photo");


        //request option for Gilde
        option=new RequestOptions().fitCenter().placeholder(R.drawable.photo_startup1).error(R.drawable.photo_startup1);
        Glide.with(this).load(intent_Shop_photo).apply(option).into(shop_photo_preview);

        select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImagev2();
            }
        });
        edit_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(decoded!=null)
                {
                    //calling json
                    jsonRequest_edit_shop();
                }else{
                    Toast.makeText(getApplicationContext(),"أختر صورة لتعديل الصورة السابقة",Toast.LENGTH_LONG).show();
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
        take_photo.setVisibility(View.GONE);
        end.setTypeface(tf);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_shop_photo.this);
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
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(Edit_shop_photo.this.getContentResolver(), data.getData());
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

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
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
    private void jsonRequest_edit_shop()
    {
        ProgressDialog progress = new ProgressDialog(this);
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
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("shop_flag_main_photo"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("shop_flag_main_photo").equals("1"))
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
                params.put("image_shop", imageString);

                return params;
            }

        };



        requestQueue_edit_shop_photo= Volley.newRequestQueue(this);
        requestQueue_edit_shop_photo.add(request_reg);
    }

}
