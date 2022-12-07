package com.example.mybookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notes_recy extends AppCompatActivity {


    String na[],au[];
    RecyclerView recyclerView;
    Button addcart;
    Notes_row notes_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_recy);

        recyclerView=(RecyclerView)findViewById(R.id.view4);
        na=getResources().getStringArray(R.array.booklist);
        au=getResources().getStringArray(R.array.author);

        notes_row= new Notes_row(Notes_recy.this,na,au);
        recyclerView.setAdapter(notes_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notes_recy.this));

        addcart=(Button)findViewById(R.id.addcart);
        addcart.setVisibility(View.INVISIBLE);
    }
}