package com.example.mybookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mybookstore.databinding.ActivityTakingDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class taking_Data extends AppCompatActivity {

    ActivityTakingDataBinding bb;
    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bb=ActivityTakingDataBinding.inflate(getLayoutInflater());
        setContentView(bb.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        Intent intent=getIntent();
        String key=intent.getStringExtra("key");

        bb.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=bb.textView17.getText().toString();
                String user_n=bb.textView18.getText().toString();
                String pno=bb.textView19.getText().toString();

                user usr=new user(name,user_n,pno);
                //database.getReference("users").child(key).setValue(user);

             try {
                 FirebaseFirestore.getInstance().collection("users").document(key).set(usr)
                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();
                             }
                         });

             }
             catch (Exception e) {
                 e.printStackTrace();
             }
                Log.i("dvff","not inserteddddd");
                Intent intent=new Intent(taking_Data.this,MainActivity.class);
                intent.putExtra("flag","0");
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });



    }
}