package com.example.ahmad_elbayadi.ma7laty.splash_screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ahmad_elbayadi.ma7laty.activities.MainActivity;
import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.startup_screen.startupscreens;

public class splash_screen extends AppCompatActivity {
    private int SPLASH_TIME_OUT = 4000;
    private TextView splash_text1,splash_text2;
    private Typeface Bien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*linking javaclass to the xml*/
        setContentView(R.layout.activity_splash_screen);

        // Font path
        String fontPath = "beinarnormal.ttf";

        splash_text1=(TextView)findViewById(R.id.splash_txt1);
        splash_text2=(TextView)findViewById(R.id.splash_txt2);


        /*the time that the splash screen will spend */

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        splash_text1.setTypeface(tf);
        splash_text2.setTypeface(tf);

        final Thread SplashScreen = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(SPLASH_TIME_OUT);
//                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    SharedPreferences  user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                    final String first_check_sh=user_setting.getString("firsttime","");
                    if(first_check_sh.equals("1")){
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                        Intent i=new Intent(getApplicationContext(),startupscreens.class);
                        startActivity(i);
                    }else{
                        Intent i=new Intent(getApplicationContext(),startupscreens.class);
                        startActivity(i);
                    }


                    finish();
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        SplashScreen.start();
    }
}
