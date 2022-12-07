package com.example.mybookstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class home_frag extends Fragment {

  TextView t1;
  RelativeLayout Book,notes,ins,ass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_frag, container, false);

        t1=(TextView)view.findViewById(R.id.textView5);
        Book=(RelativeLayout)view.findViewById(R.id.Book);
        notes=(RelativeLayout)view.findViewById(R.id.Notes);
        ass=(RelativeLayout)view.findViewById(R.id.AAssignment);
        ins=(RelativeLayout)view.findViewById(R.id.Instrument);

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Book_Recy.class);
                startActivity(intent);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Notes_recy.class);
                startActivity(intent);
            }
        });

        ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Ass_recy.class);
                startActivity(intent);
            }
        });

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Ins_recy.class);
                startActivity(intent);
            }
        });

        Log.i("home","inside home frag");

        //String uname=getArguments().getString("uname").toString();
       // t1.setText(uname);


        return  view;
    }
}