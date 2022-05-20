package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.FavoriteModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductScreen extends AppCompatActivity {
    private Bundle extras;
    private TextView title, price, description,count;
    ImageView image,favorite;
    Database db = new Database(this);
    AuthDatabase authDatabase=new AuthDatabase();
    HomeModel homeModel;
    public int i=0;
    int id;
    enum isFavorite
    {
        favorite,desFavorite
    }
    isFavorite isfavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        extras = getIntent().getExtras();
         id= setProductData(extras);
        title = findViewById(R.id.titleID);
        price = findViewById(R.id.priceID);
        description = findViewById(R.id.descriptionID);
        count=findViewById(R.id.countID);
        favorite=findViewById(R.id.favorite);
        image = findViewById(R.id.imageUrlID);
        homeModel = db.searchData(id);
        image.setImageBitmap(homeModel.getImage());
        title.setText(homeModel.getTitle());
        price.setText(homeModel.getPrice());
        description.setText(homeModel.getDescription());
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.increaseID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i= Integer.parseInt(count.getText().toString());
                i++;
                count.setText(String.valueOf(i));
            }
        });
        findViewById(R.id.decreaseID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i= Integer.parseInt(count.getText().toString());
                if(i!=0)
                {i--;
                  count.setText(String.valueOf(i));}
            }
        });
        findViewById(R.id.addToCartID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart();
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfavorite==isFavorite.desFavorite)
                {
                    FavoriteModel favoriteModel = new FavoriteModel();
                    favoriteModel.setId(id);
                    favoriteModel.setFavorite(authDatabase.getUserId());
                    db.addToFavorite(favoriteModel);
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    isfavorite=statue(isfavorite);
                }
                else {
                    db.deleteDataFromFavorite(id);
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    isfavorite=statue(isfavorite);
                }

            }
        });


        List<FavoriteModel> fav;
        fav=new ArrayList<>();
        fav =db.getProductInFavorite();
        for (int i=0;i<fav.size();i++)
        {
            FavoriteModel model=fav.get(i);
            if(model.getId()==id)
            {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                isfavorite = isFavorite.favorite;
            }
            else {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                isfavorite=isFavorite.desFavorite;
            }
        }

    }
    private isFavorite statue(isFavorite val) {
        if(val==isFavorite.favorite)
        {
            val=isFavorite.desFavorite;
        }
        else
        {
            val=isFavorite.favorite;
        }
        return val;
    }
    private int setProductData(Bundle extras) {
        int id;
        if (extras != null)
            id = extras.getInt("id");
        else
            return 0;
        return id;
    }
    public void addProductToCart() {
        db.addProductToCart(new CartModel(String.valueOf(id),
                title.getText().toString(),price.getText().toString(),count.getText().toString()));
        finish();
    }

}