package com.example.myshopapp.Screens;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshopapp.Adapter.Adapter_Cart;
import com.example.myshopapp.Adapter.Adapter_Product;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.Model.OrderModel;
import com.example.myshopapp.R;

import java.util.ArrayList;
import java.util.List;
public class CartScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<CartModel> data;
    private static Adapter_Cart adapter;
    private Database database=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        recyclerView=findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data=new ArrayList<>();
        data= database.getAllProductInCart();
        adapter=new Adapter_Cart(this,data,database);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.backButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.orderNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderModel orderModel=new OrderModel("you","20,20");
                database.addOrder(orderModel);
                finish(); }
        });
    }
    public static void notifyAdapter()
    {
        adapter.notifyDataSetChanged();
    }
}