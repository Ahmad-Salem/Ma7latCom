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

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    private Button dial_number1, dial_number2, dial_number3;
    private TextView toolbar1,t1,t2,t3;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.forgot_password_toolbar);
        setSupportActionBar(toolbar);
        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);


        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);




        toolbar1 = (TextView) findViewById(R.id.toolbar_title_forgot_password);
        t1 = (TextView) findViewById(R.id.heading);
        t2 = (TextView) findViewById(R.id.body);
        t3 = (TextView) findViewById(R.id.phone_title);
        dial_number1 = (Button) findViewById(R.id.dial_number1);
        dial_number2 = (Button) findViewById(R.id.dial_number2);
        dial_number3 = (Button) findViewById(R.id.dial_number3);

        dial_number1.setOnClickListener(this);
        dial_number2.setOnClickListener(this);
        dial_number3.setOnClickListener(this);

        //setting the font face
        toolbar1.setTypeface(tf);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dial_number1:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01002111402"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }
                break;
            case R.id.dial_number2:
                Intent dialIntent2 = new Intent(Intent.ACTION_DIAL);
                dialIntent2.setData(Uri.parse("tel:" + "01010100789"));
                if (dialIntent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent2);
                } else {
                    Toast.makeText(getApplicationContext(), "جهازك لا يدعم هذه الخاصية", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dial_number3:
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