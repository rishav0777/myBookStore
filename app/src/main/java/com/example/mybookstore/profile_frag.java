package com.example.mybookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class profile_frag extends Fragment {

    FirebaseAuth auth;
    TextView log;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_profile_frag, container, false);
       log=(TextView)view.findViewById(R.id.textView12);

       log.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               auth.signOut();

               startActivity(new Intent(getActivity(),sigtn_in.class));
           }
       });

       return view;
    }
}