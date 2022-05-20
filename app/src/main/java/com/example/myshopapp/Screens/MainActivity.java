package com.example.myshopapp.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myshopapp.Adapter.Adapter_Product;
import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<HomeModel> data;
    private static  Adapter_Product adapter;
    private AuthDatabase authDatabase = new AuthDatabase();
    private Database database;
    private LinearLayout MyAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goActivityScreen();
        database=new Database(this);
        MyAddButton = findViewById(R.id.adminEdit);

        DrawerLayout drawerLayout = findViewById(R.id.MyDrawer);
        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyAccountScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.OpenDrawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(authDatabase.getUserId().equals("An3ylp5NJKbSaskcrp11IW8UlMi2"))
                {
                    MyAddButton.setVisibility(View.VISIBLE);
                }*/
               // MyAddButton.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        MyAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Admin_Screen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.LogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authDatabase.LogOut();
                finish();
               Intent intent = new Intent(MainActivity.this, Auth.class);
                startActivity(intent);
            }
        });

    }

    public void menuOptions(int id, Class screen) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, screen);
                startActivity(intent);
            }
        });
    }

    public void goActivityScreen() {
        menuOptions(R.id.GoHomeScreen, MainActivity.class);
        menuOptions(R.id.GoFavoritesScreen, FavoriteScreen.class);
        menuOptions(R.id.GoAccountScreen, MyAccountScreen.class);
        menuOptions(R.id.GoCartScreen, CartScreen.class);
        menuOptions(R.id.GoOrdersScreen, OrdersScreen.class);
    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerView=findViewById(R.id.homeScreen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        data=new ArrayList<>();
        data= database.getAllProduct();
        adapter=new Adapter_Product(this,data,database);
        recyclerView.setAdapter(adapter);
    }
    public static void notifyAdapter()
    {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            database.addUser(new UserDataModel(authDatabase.getUserId(),bundle.getString("userName"),bundle.getString("PhoneNumber")));
        }
    }
}
