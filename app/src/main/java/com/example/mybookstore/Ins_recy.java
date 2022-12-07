package com.example.mybookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ins_recy extends AppCompatActivity {

    String na[];
    RecyclerView recyclerView;
    Ins_row ins_row;
    Button addcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_recy);

        recyclerView=(RecyclerView)findViewById(R.id.view4);

        na=getResources().getStringArray(R.array.booklist);

        ins_row=new Ins_row(Ins_recy.this,na);
        recyclerView.setAdapter(ins_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(Ins_recy.this));

        addcart=(Button)findViewById(R.id.addcart);
        addcart.setVisibility(View.INVISIBLE);
    }
}