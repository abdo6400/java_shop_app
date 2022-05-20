package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myshopapp.Adapter.Adapter_Product;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.FavoriteModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<HomeModel> data;
    private Adapter_Product adapter;
    List<FavoriteModel> fav;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_screen);
        database=new Database(this);
        recyclerView = findViewById(R.id.favoriteView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        data=new ArrayList<>();
        fav=new ArrayList<>();
        fav =database.getProductInFavorite();
            for (int i=0;i<fav.size();i++) {
               FavoriteModel model=fav.get(i);
               data.add(database.searchData(model.getId()));
            }
        adapter = new Adapter_Product(FavoriteScreen.this, data,database);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.backButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}