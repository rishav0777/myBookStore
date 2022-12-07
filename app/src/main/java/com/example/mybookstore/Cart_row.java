package com.example.mybookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Cart_row extends RecyclerView.Adapter<Cart_row.holder> {

    String na[];
    Context context;

    public Cart_row(Context contex,String a[]) {
        na=a;
        context=contex;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_cart,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.name.setText(na[position]);
    }

    @Override
    public int getItemCount() {
        return na.length;
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView name;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView21);
        }
    }
}
