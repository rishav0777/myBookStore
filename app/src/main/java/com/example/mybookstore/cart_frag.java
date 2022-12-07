package com.example.mybookstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class cart_frag extends Fragment {

   Cart_row cart_row;
   RecyclerView recyclerView;
   String na[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart_frag, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.view4);

        na=getResources().getStringArray(R.array.things);
        cart_row=new Cart_row(getActivity(),na);
        recyclerView.setAdapter(cart_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return  view;
    }
}