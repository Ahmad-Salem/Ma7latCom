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
import android.widget.Button;
import android.widget.EditText;
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

public class Add_offer_own extends AppCompatActivity {

    private TextView toolobar;
    private EditText offer_name,offer_description;
    private Button offer_photo_btn,add_offer_btn;
    private Typeface tf;
    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    private ImageView offer_photo_preview;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 500;
    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/shop_owner_credential/shop_setting/add_offer.php";
    private RequestQueue requestQueue_add_offer;
    private Dialog Discover_shop_dialog;
    private Button take_photo,choose_from_device,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer_own);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.newuser_activity_toolbar_add_offer_22);
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

                Intent Main=new Intent(getApplicationContext(),List_Of_Product_own.class);

                Main.putExtra("Shop_id_offers",shop_id);
                startActivity(Main);

            }
        });

        //casting screen component
        toolobar=(TextView)findViewById(R.id.toolbar_title_reg_add_offer_22);
        offer_name=(EditText) findViewById(R.id.offer_name_add);
        offer_description=(EditText) findViewById(R.id.shop_description_add);
        offer_photo_btn=(Button) findViewById(R.id.photo_offer);
        add_offer_btn=(Button) findViewById(R.id.add_new_offer);
        offer_photo_preview=(ImageView) findViewById(R.id.offer_photo_reg);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting the font face
        toolobar.setTypeface(tf);
        offer_name.setTypeface(tf);
        offer_description.setTypeface(tf);
        offer_photo_btn.setTypeface(tf);
        add_offer_btn.setTypeface(tf);

        //reset button
        FloatingActionButton fab = findViewById(R.id.fab_add_offer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                offer_name.setText("");
                offer_description.setText("");
                offer_photo_preview.setImageResource(R.drawable.store);

                Snackbar.make(view, "تم مسح كل القيم", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        offer_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImagev2();
            }
        });

        add_offer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),""+offer_name.getText().toString()+"/"+offer_description.getText().toString(),Toast.LENGTH_LONG).show();
                if(decoded!=null&&offer_name.getText().toString()!=null&&offer_description.getText().toString()!=null)
                {
//                    Toast.makeText(getApplicationContext(),"image not null",Toast.LENGTH_LONG).show();
                    jsonRequest_add_offer(offer_name.getText().toString(),offer_description.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال جميع القيم",Toast.LENGTH_LONG).show();
                }



            }
        });
    }
    //select image
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
        offer_photo_preview.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Add_offer_own.this);
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
                    bitmap = MediaStore.Images.Media.getBitmap(Add_offer_own.this.getContentResolver(), data.getData());
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
        offer_photo_preview.setImageBitmap(decoded);
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
    private void jsonRequest_add_offer(final String offer_name1,final String offer_descrption1)
    {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.getWindow().setGravity(Gravity.RIGHT);
        progress.setMessage("جاري التحميل...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        //receive data
        SharedPreferences user_info = getSharedPreferences("ACCOUNT_INFORMATION", Context.MODE_PRIVATE);
        final String ID = user_info.getString("user_id", "");
        final String EMAIL = user_info.getString("user_email", "");
        final String PASSWORD1 = user_info.getString("user_password", "");
        final String shop_id = getIntent().getExtras().getString("shop_id");




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
                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("add_offer_flag"),Toast.LENGTH_LONG).show();
//                    if(response!=null)
//                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
//                        Log.i("jsonopopop",jsonObject+"");
//                    }
                    if(jsonObject.getString("add_offer_flag").equals("1"))
                    {
//                        intent to activation code activity
                        Toast.makeText(getApplicationContext(),"تم إضافة العرض بنجاح",Toast.LENGTH_LONG).show();
                        Intent activation=new Intent(getApplicationContext(),MainActivity_private.class);
                        startActivity(activation);
                    }else{
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"حاول مرة أخري",Toast.LENGTH_LONG).show();
                        progress.cancel();
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
                params.put("do_action", "add_offers");
                params.put("id", ID);
                params.put("email", EMAIL);
                params.put("password", PASSWORD1);
                params.put("shop_id", shop_id);
                params.put("offer_name", offer_name1);
                params.put("offer_description", offer_descrption1);
                params.put("image_offer", imageString);

                return params;
            }

        };



        requestQueue_add_offer= Volley.newRequestQueue(this);
        requestQueue_add_offer.add(request_reg);
    }

}
