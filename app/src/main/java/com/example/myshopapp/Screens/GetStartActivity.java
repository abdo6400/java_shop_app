package com.example.myshopapp.Screens;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myshopapp.Adapter.SlideViewPageAdapter;
import com.example.myshopapp.R;
public class GetStartActivity extends AppCompatActivity {
 public static ViewPager viewPager;
    int i=0;
  SlideViewPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_start);
       viewPager=findViewById(R.id.viewPager);
       adapter=new SlideViewPageAdapter(this);
       viewPager.setAdapter(adapter);
          if(isOpenAlready()) {
              Intent intent=new Intent(GetStartActivity.this, Auth.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
          }
          else {
              SharedPreferences.Editor editor=getSharedPreferences("slide",MODE_PRIVATE).edit();
              editor.putBoolean("slide",true);
              editor.commit();
          }
    }
    private boolean isOpenAlready() {

        SharedPreferences sharedPreferences=getSharedPreferences("slide",MODE_PRIVATE);
        boolean result=sharedPreferences.getBoolean("slide",false);
        return result;
    }
}