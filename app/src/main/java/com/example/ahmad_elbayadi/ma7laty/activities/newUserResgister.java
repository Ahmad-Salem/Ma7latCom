package com.example.ahmad_elbayadi.ma7laty.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class newUserResgister extends AppCompatActivity {

    private final String JSON_URL="http://www.ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/API/registeration/registeration.php";
    private RequestQueue requestQueue_reg;



    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    private Dialog Discover_shop_dialog;
    private Button take_photo,choose_from_device,end;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 500;
    private Typeface tf;

    private Typeface Bien;
    private TextView toolbar_title;
    private EditText user_name,Email_address,Password,re_password,Title,mobile_number1,mobile_number2;
    private Button register_submit,upload_photo;
    private ImageView user_photo_preview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_resgister);

        btn_choose_image = (Button) findViewById(R.id.photo);
        user_photo_preview = (ImageView) findViewById(R.id.user_photo_reg);

        btn_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImagev2();
            }
        });



        Toolbar toolbar = (Toolbar)findViewById(R.id.newuser_activity_toolbar);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        //dialog this context
        Discover_shop_dialog=new Dialog(this);

        // Font path
        String fontPath = "beinarnormal.ttf";
        //casting screen component
        user_name=(EditText)findViewById(R.id.user_name);
        Email_address=(EditText)findViewById(R.id.email_address);
        Password=(EditText)findViewById(R.id.password);
        re_password=(EditText)findViewById(R.id.re_password);
        Title=(EditText)findViewById(R.id.address);
        mobile_number1=(EditText)findViewById(R.id.phone1);
        mobile_number2=(EditText)findViewById(R.id.phone2);
        toolbar_title=(TextView) findViewById(R.id.toolbar_title_reg);
        register_submit=(Button) findViewById(R.id.add_new_user);
        upload_photo=(Button) findViewById(R.id.photo);



        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        // setting the font
        user_name.setTypeface(tf);
        Email_address.setTypeface(tf);
        Password.setTypeface(tf);
        re_password.setTypeface(tf);
        Title.setTypeface(tf);
        mobile_number1.setTypeface(tf);
        mobile_number2.setTypeface(tf);
        toolbar_title.setTypeface(tf);
        register_submit.setTypeface(tf);
        upload_photo.setTypeface(tf);

        //reset button
        FloatingActionButton fab = findViewById(R.id.Reset_registeration_value);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_name.setText("");
                Email_address.setText("");
                Password.setText("");
                re_password.setText("");
                Title.setText("");
                mobile_number1.setText("");
                mobile_number2.setText("");
                user_photo_preview.setImageResource(R.drawable.default_person);

                Snackbar.make(view, "تم مسح كل القيم", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //add user button listner
        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_name.getText().toString()!=null&&Email_address.getText().toString() !=null&&Password.getText().toString() !=null&& re_password.getText().toString()!=null&& Title.getText().toString()!=null&&mobile_number1.getText().toString() !=null&& mobile_number2.getText().toString()!=null&&decoded!=null)
                {
                    if(user_name.getText().toString().equals(user_name.getText().toString()))
                    {
                        //request json
                        jsonRequest_new_user_registeration(user_name.getText().toString(),Email_address.getText().toString(),Password.getText().toString(),Title.getText().toString(),mobile_number1.getText().toString(),mobile_number2.getText().toString());

                    }else{
                        Toast.makeText(getApplicationContext(),"كلمات المرور غير متطابقة",Toast.LENGTH_LONG).show();
                    }
                    //request json
                    jsonRequest_new_user_registeration(user_name.getText().toString(),Email_address.getText().toString(),Password.getText().toString(),Title.getText().toString(),mobile_number1.getText().toString(),mobile_number2.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"يجب إدخال جميع القيم",Toast.LENGTH_LONG).show();
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
        user_photo_preview.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(newUserResgister.this);
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
                    bitmap = MediaStore.Images.Media.getBitmap(newUserResgister.this.getContentResolver(), data.getData());
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
        user_photo_preview.setImageBitmap(decoded);
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


    //get advertisements using volley

    private void jsonRequest_new_user_registeration(final String user_name1,final String email1,final  String password1,final String address1,final String phone11,final String phone22)
    {

        ProgressDialog progress = new ProgressDialog(this);
        progress.getWindow().setGravity(Gravity.RIGHT);
        progress.setMessage("جاري التحميل...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        decoded.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


        StringRequest request_reg=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
                    if(jsonObject.getString("reg_flag").equals("1"))
                    {
                        //intent to activation code activity
                        Intent activation=new Intent(getApplicationContext(),activation_activity.class);
                        startActivity(activation);
                    }else{
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(),"حاول مرة أخري",Toast.LENGTH_LONG).show();

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
                params.put("do_action", "register");
                params.put("user_name", user_name1);
                params.put("email", email1);
                params.put("password", password1);
                params.put("user_job", "shopowner");
                params.put("user_status", "offline");
                params.put("user_gender", "male");
                params.put("user_title", address1);
                params.put("telephone1", phone11);
                params.put("telephone2", phone22);
                params.put("user_photo", imageString);

                return params;
            }

        };

        requestQueue_reg= Volley.newRequestQueue(this);
        requestQueue_reg.add(request_reg);
    }

}
