package com.example.myshopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myshopapp.Screens.Auth;
import com.example.myshopapp.Screens.GetStartActivity;
import com.example.myshopapp.R;

public class SlideViewPageAdapter extends PagerAdapter {
    Context context;

    public SlideViewPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull  View view, @NonNull  Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.start_page,container,false);
        View logo=view.findViewById(R.id.logoID);
        ImageView ind3=view.findViewById(R.id.ind_3);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind1=view.findViewById(R.id.ind_1);

        TextView des=view.findViewById(R.id.des);
            ImageView next=view.findViewById(R.id.next);
            ImageView back=view.findViewById(R.id.back);

        Button getStarted=view.findViewById(R.id.start);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent=new Intent(context, Auth.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetStartActivity.viewPager.setCurrentItem(position+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetStartActivity.viewPager.setCurrentItem(position-1);
            }
        });
        switch (position) {
            case 0:
                logo.setBackgroundResource(R.drawable.icons8_shopping_cart_500px);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                des.setText("You can find any thing you want here");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;
            case 1:
                logo.setBackgroundResource(R.drawable.icons8_favorite_cart_500px);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);
                des.setText("Easy and Fast");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;
            case 2:
                logo.setBackgroundResource(R.drawable.icons8_add_shopping_cart_500px);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);
                des.setText("Let's Start");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;

        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position,  Object object) {
        container.removeView((View) object);
    }
}
