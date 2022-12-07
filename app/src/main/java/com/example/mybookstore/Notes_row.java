package com.example.mybookstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Notes_row extends RecyclerView.Adapter<Notes_row.holder> {
    String na[],au[];
    Context context;
    public Notes_row(Context contex, String a[], String b[]) {
        na=a;
        au=b;
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
        holder.author.setText(au[position]);

        holder.itemView .setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("fnrf","onlong click");
                holder.imageView.setImageResource(R.drawable.tickk);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return na.length;
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView name,author;
        ImageView imageView;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView21);
            author=(TextView)itemView.findViewById(R.id.textView20);
            imageView=(ImageView)itemView.findViewById(R.id.imageView6);
        }
    }
}
