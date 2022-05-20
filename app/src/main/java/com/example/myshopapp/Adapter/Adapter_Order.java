package com.example.myshopapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.OrderModel;
import com.example.myshopapp.R;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter_Order extends RecyclerView.Adapter<Adapter_Order.ViewHolder> {
    Context context;
    List<OrderModel> list;
    Database database;
    public Adapter_Order(Context context, List<OrderModel> list) {
        this.context = context;
        this.list = list;
    }

    public Adapter_Order(Context context,List<OrderModel> list, Database database) {
        this.context = context;
        this.list = list;
        this.database = database;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter_Order.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitem,parent,false);
          return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Order.ViewHolder holder, int position) {
         OrderModel item= list.get(position);
         holder.orderNo.setText(" Order No : "+String.valueOf(item.getId()));
         holder.amount.setText(item.getAmount());
         holder.dateTime.setText(item.getDateTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView amount;
            TextView dateTime;
            TextView orderNo;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            amount=itemView.findViewById(R.id.amountOrderID);
            dateTime=itemView.findViewById(R.id.dateID);
            orderNo=itemView.findViewById(R.id.orderNo);

        }
    }
  /*  private String formatDate(String dateStr)
    {
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=fmt.parse(dateStr);
            SimpleDateFormat fmtout=new SimpleDateFormat("MMM d");
        } catch (ParseException e) {
            Log.e("error",e.getMessage());
        }
        return "";
    }*/
}
