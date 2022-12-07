package com.example.mybookstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Ins_row extends RecyclerView.Adapter<Ins_row.holder> {

    String na[];
    Context context;
 // Ins_recy ins_recy=new Ins_recy();

    public Ins_row(Context contex,String a[]) {
        na=a;
        context=contex;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.demo,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.name.setText(na[position]);

        holder.itemView .setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("fnrf","onlong click");
                holder.imageView.setImageResource(R.drawable.tickk);
              //  ins_recy.addcart.setVisibility(View.VISIBLE);
              //  holder.addcart.setVisibility(View.VISIBLE);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return na.length;
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
       // Button addcart;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView21);
            imageView=(ImageView)itemView.findViewById(R.id.imageView6);
            //addcart=(Button)itemView.findViewById(R.id.addcart);

        }
    }
}
