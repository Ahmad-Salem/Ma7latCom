package com.example.ahmad_elbayadi.ma7laty.startup_screen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad_elbayadi.ma7laty.R;
import com.example.ahmad_elbayadi.ma7laty.user_general_setting.general_setting;

public class startupscreens extends AppCompatActivity {

    private ViewPager mySliderViewPager;
    private LinearLayout mDotLayout;
    private startup_screens_adapter silderAdapter;
    private TextView[] mDots;
    private Button next,back;
    private int currentPage;
    public Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startupscreens);

        // Font path
        String fontPath = "beinarnormal.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //casting android component
        mySliderViewPager=(ViewPager) findViewById(R.id.sliderviewpage);
        mDotLayout=(LinearLayout)findViewById(R.id.dotslayout);

        silderAdapter=new startup_screens_adapter(this,tf);
        mySliderViewPager.setAdapter(silderAdapter);

        next=(Button) findViewById(R.id.next_btn);
        back=(Button) findViewById(R.id.back_btn);

        next.setTypeface(tf);
        back.setTypeface(tf);

        //add indicators
        addDotsIndicators(0);

        //caling the view pager listener
        mySliderViewPager.addOnPageChangeListener(viewListener);


        //on click listener
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySliderViewPager.setCurrentItem(currentPage+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySliderViewPager.setCurrentItem(currentPage-1);
            }
        });

    }



    public void addDotsIndicators( int pos)
    {
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for(int i=0; i<mDots.length; i++)
        {
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0 )
        {
            mDots[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }


    //view pager handler
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
            addDotsIndicators(position);
            currentPage=position;

            if(position == 0 )
            {
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("التالي");
                back.setText("");

            }else if(position == mDots.length-1){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(),"finish",Toast.LENGTH_SHORT).show();
                next.setText("إنهاء");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent get_started=new Intent(startupscreens.this, general_setting.class);
                        startActivity(get_started);
                    }
                });
                back.setText("السابق");
            }else if(position==1){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("التالي");
                back.setText("السابق");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
