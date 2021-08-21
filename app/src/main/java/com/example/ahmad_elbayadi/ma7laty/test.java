package com.example.ahmad_elbayadi.ma7laty;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class test extends AppCompatActivity {
    private RequestOptions option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        option=new RequestOptions().fitCenter().placeholder(R.drawable.loading_gif).error(R.drawable.default_person);
        ImageView x=(ImageView)findViewById(R.id.image_test);
        Glide.with(getApplicationContext()).load("http://ma7latcom.com/59a1cac00edcbfa80c57957dc1e9018a/images/users/62/61/offers/21/044132pmf84c1fc858e9b741518dab7b4a4667e6.jpg").apply(option).into(x);
    }
}
