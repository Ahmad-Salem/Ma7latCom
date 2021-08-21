package com.example.ahmad_elbayadi.ma7laty.startup_screen;

import android.content.Context;
import android.graphics.Typeface;
import androidx.viewpager.widget.PagerAdapter;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmad_elbayadi.ma7laty.R;

/**
 * Created by Ahmad_Elbayadi on 03/04/2018.
 */

public class startup_screens_adapter extends PagerAdapter {
    Context context;
    Typeface tf;
    LayoutInflater layoutInflater;

    public startup_screens_adapter(Context context,Typeface tf) {
        this.context = context;
        this.tf=tf;
    }

    // Arrays
    public int[] slide_images=
            {
                    R.drawable.photo_startup1,
                    R.drawable.photo_startup2,
                    R.drawable.photo_startup3
            };

    public String[] slide_heading={
            "محلاتكوم",
            "محلاتكوم",
            "محلاتكوم"
    };

    public String[] slide_description={
            "نسهل عليك عملية شراء ماتريد بأرخص الأسعار و أقرب الأماكن .. نساعدك علي التسوق بدون عناء ونساعدك للوصول إلي المحل الذي تريده مباشرة",
            "نسهل عليك عملية الأعلان بطريقة فعالة للمحل الخاص بك وعملية تسويق وبيع منتجاتك والأعلان لمحلك وإضافة عروض بداخله ",
            "برجاء إدخال مدينتك و المحافظة للحصول علي نتائج أفضل عند البحث عن محل معين حيث انه يمكن تغيره في وقت لاحق اذا أردت"

    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.startup_screens_layout,container,false);

        // Font path
        String fontPath = "beinarnormal.ttf";

        //casting silder component

        CardView card=(CardView) view.findViewById(R.id.info);
        ImageView SildeImageView=(ImageView) view.findViewById(R.id.icon);
        TextView SildeHeading=(TextView) view.findViewById(R.id.heading);
        TextView SildeDescription=(TextView) view.findViewById(R.id.body);

        //set opacity to card view
        card.getBackground().setAlpha(200);

        //set font face
        SildeHeading.setTypeface(tf);
        SildeDescription.setTypeface(tf);

        //setting android view components
        SildeImageView.setImageResource(slide_images[position]);
        SildeHeading.setText(slide_heading[position]);
        SildeDescription.setText(slide_description[position]);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
