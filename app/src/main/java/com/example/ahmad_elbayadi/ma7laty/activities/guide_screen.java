package com.example.ahmad_elbayadi.ma7laty.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad_elbayadi.ma7laty.R;

public class guide_screen extends AppCompatActivity implements View.OnClickListener{

    private TextView toolbar,t1,t2,t3,t4,t5;
    private Typeface tf;
    private Button dial_number1, dial_number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_screen);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.more_info_toolbar);
        setSupportActionBar(toolbar2);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        //casting android component
        toolbar = (TextView) findViewById(R.id.toolbar_title_more_info);
        t1 = (TextView) findViewById(R.id.heading_info);
        t2 = (TextView) findViewById(R.id.body_info);
        t3 = (TextView) findViewById(R.id.phone_title_info);
        t4 = (TextView) findViewById(R.id.next_info);
        t5 = (TextView) findViewById(R.id.pre_info);

        //setting type face
        toolbar.setTypeface(tf);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);
        t4.setTypeface(tf);
        t5.setTypeface(tf);

        dial_number1 = (Button) findViewById(R.id.dial_number1_info);
        dial_number2 = (Button) findViewById(R.id.dial_number2_info);

        dial_number1.setOnClickListener(this);
        dial_number2.setOnClickListener(this);


        //next_button
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to login activity
                Intent i=new Intent(getApplicationContext(),Login_activity.class);
                startActivity(i);
            }
        });

        //pre_button
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to main screen
                Intent main=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main);
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dial_number1_info:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01091887791"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }
                break;
            case R.id.dial_number2_info:
                Intent dialIntent2 = new Intent(Intent.ACTION_DIAL);
                dialIntent2.setData(Uri.parse("tel:" + "01091811800"));
                if (dialIntent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent2);
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
