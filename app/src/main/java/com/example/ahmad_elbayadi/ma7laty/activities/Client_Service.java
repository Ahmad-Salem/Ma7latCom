package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad_elbayadi.ma7laty.R;

public class Client_Service extends AppCompatActivity implements View.OnClickListener{

    private TextView toolbar,t1,t2,t3,t4,t5;
    private Typeface tf;
    private Button dial_number1, dial_number2,dial_number3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__service);


        Toolbar toolbar2 = (Toolbar) findViewById(R.id.more_info_toolbar);
        setSupportActionBar(toolbar2);

        // Font path
        String fontPath = "beinarnormal.ttf";

        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //casting android component
        toolbar = (TextView) findViewById(R.id.toolbar_title_more_info1);
        t1 = (TextView) findViewById(R.id.heading_info1);
        t2 = (TextView) findViewById(R.id.body_info1);
        t3 = (TextView) findViewById(R.id.phone_title_info1);



        //setting type face
        toolbar.setTypeface(tf);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);



        dial_number1 = (Button) findViewById(R.id.dial_number1_info1);
        dial_number2 = (Button) findViewById(R.id.dial_number2_info1);
        dial_number3 = (Button) findViewById(R.id.dial_number2_info31);

        dial_number1.setOnClickListener(this);
        dial_number2.setOnClickListener(this);
        dial_number3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dial_number1_info1:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01091887791"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }
                break;

            case R.id.dial_number2_info1:
                Intent dialIntent2 = new Intent(Intent.ACTION_DIAL);
                dialIntent2.setData(Uri.parse("tel:" + "01091811800"));
                if (dialIntent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent2);
                } else {
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.dial_number2_info31:
                Intent dialIntent3 = new Intent(Intent.ACTION_DIAL);
                dialIntent3.setData(Uri.parse("tel:" + "01030442242"));
                if (dialIntent3.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent3);
                } else {
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
