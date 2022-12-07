package com.example.mybookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ass_recy extends AppCompatActivity {

    String na[],au[];
    RecyclerView recyclerView;
    Ass_row ass_row;
    Button addcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_recy);



        recyclerView=(RecyclerView)findViewById(R.id.view4);
        na=getResources().getStringArray(R.array.booklist);
        au=getResources().getStringArray(R.array.author);

        ass_row=new Ass_row(Ass_recy.this,na,au);
        recyclerView.setAdapter(ass_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(Ass_recy.this));

        addcart=(Button)findViewById(R.id.addcart);
        addcart.setVisibility(View.INVISIBLE);
    }
}