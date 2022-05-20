package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;


import com.example.myshopapp.Adapter.Adapter_Order;
import com.example.myshopapp.Database.Database;

import com.example.myshopapp.Model.OrderModel;
import com.example.myshopapp.R;

import java.util.ArrayList;

import java.util.List;

public class OrdersScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<OrderModel> data;
    private Adapter_Order adapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_screen);
     //   database = new Database(this);
      //  recyclerView = findViewById(R.id.recyclerView2);
      //  recyclerView.setHasFixedSize(true);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // data = new ArrayList<>();
       // data = database.getAllOrder();
       // adapter = new Adapter_Order(this, data, database);
      // recyclerView.setAdapter(adapter);
        findViewById(R.id.backButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}