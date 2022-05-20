package com.example.myshopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.R;
import com.example.myshopapp.Screens.CartScreen;
import com.example.myshopapp.Screens.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter_Cart extends RecyclerView.Adapter<Adapter_Cart.ViewHolder> {

    Context context;
    List<CartModel> list;
    Database database;
    public Adapter_Cart(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }
    public Adapter_Cart(Context context, List<CartModel> list, Database database) {
        this.context = context;
        this.list = list;
        this.database = database;
    }
    @NonNull
    @NotNull
    @Override
    public Adapter_Cart.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displayproduct_in_cart, parent,
                false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Cart.ViewHolder holder, int position) {
        CartModel item = list.get(position);
        HomeModel homeModel = database.searchData(Integer.parseInt(item.getImage()));
        double total=Double.parseDouble(item.getPrice())*Double.parseDouble(item.getQuantity());
        holder.title.setText(item.getTitle());
        holder.price.setText("Total Price : " + total + " Pounds ");
        holder.imageView.setImageBitmap(homeModel.getImage());
        holder.quantity.setText("Quantity :   " + item.getQuantity());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                database.deleteDataFromCart(item.getId());
                list.remove(position);
                CartScreen.notifyAdapter();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView quantity;
        TextView title;
        ImageView imageView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.totalPriceID);
            quantity = itemView.findViewById(R.id.quantityID);
            title = itemView.findViewById(R.id.titleCartID);
            imageView = itemView.findViewById(R.id.imageCartID);
        }
    }

}
