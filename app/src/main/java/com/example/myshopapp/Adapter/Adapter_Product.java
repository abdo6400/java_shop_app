package com.example.myshopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.FavoriteModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.R;
import com.example.myshopapp.Screens.FavoriteScreen;
import com.example.myshopapp.Screens.MainActivity;
import com.example.myshopapp.Screens.ProductScreen;

import org.jetbrains.annotations.NotNull;

import java.util.List;
public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder> {
    private List<HomeModel> dataList;
    private Context context;
    private Database database;
    AuthDatabase authDatabase=new AuthDatabase();
    public Adapter_Product(Context context, List dataList) {
        this.dataList = dataList;
        this.context = context;
    }
    public Adapter_Product(Context context,List<HomeModel> dataList, Database database) {
        this.dataList = dataList;
        this.context = context;
        this.database = database;
    }
    @NonNull
    @NotNull
    @Override
    public Adapter_Product.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displayproduct, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Product.ViewHolder holder, int position) {
        HomeModel item = dataList.get(position);
        holder.imageUrl.setImageBitmap(item.getImage());
        holder.title.setText(item.getTitle());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteProduct(position);
                database.deleteDataFromFavorite(item.getId());
                database.deleteDataFromCart(item.getId());
                return true;
            }
        });
       /* if(authDatabase.getUserId().equals("An3ylp5NJKbSaskcrp11IW8UlMi2")){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteProduct(position);
                database.deleteDataFromFavorite(item.getId());
                database.deleteDataFromCart(item.getId());
                return true;
            }
        });}*/

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageUrl;
        TextView title;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageUrl=itemView.findViewById(R.id.imageProduct);
            title=itemView.findViewById(R.id.title);
        }
        @Override
        public void onClick(View v) {
            int position= getAdapterPosition();
            HomeModel getData=dataList.get(position);
            Intent intent=new Intent(context,ProductScreen.class);
            intent.putExtra("id",getData.getId());
            context.startActivity(intent);
        }
    }
    private void deleteProduct(int position) {
        database.deleteData(dataList.get(position));
        dataList.remove(position);
        MainActivity.notifyAdapter();
    }


}
