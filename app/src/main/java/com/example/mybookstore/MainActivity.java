package com.example.mybookstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mybookstore.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bb;
    BottomNavigationView bottomNavigationView;

    FirebaseDatabase database;
    FirebaseAuth auth;

    String uname,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bb=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        Log.i("main","before fragment");

        Bundle bundle = new Bundle();



        Intent intent=getIntent();
       flag=intent.getStringExtra("flag");
        if(flag.equals(0)) {
            String key = intent.getStringExtra("key");


            /*database.getReference("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (dataSnapshot.getKey().equals(key)) {
                            user u = dataSnapshot.getValue(user.class);
                            uname = u.getUser_Name();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            FirebaseFirestore.getInstance().collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                    for (DocumentSnapshot snapshot:value){
                        if(snapshot.getId().equals(key)){
                            uname=snapshot.getString("name");
                        }
                    }
                }
            });
            Log.i("dvff",uname);


            bundle.putString("uname", uname);
        }
        else{

            bundle.putString("uname", "uname");
        }


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.view);


     getSupportFragmentManager().beginTransaction()
             .setReorderingAllowed(true)
             .add(R.id.view2,home_frag.class,null)
             .commit();
      //  Log.i("dvff",uname);
        Log.i("main","beforeswitch fragment");


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             switch (item.getItemId()){
                 case R.id.profile:
                     getSupportFragmentManager().beginTransaction()
                             .setReorderingAllowed(true)
                             .replace(R.id.view2,profile_frag.class,null)
                             .commit();
                     break;
                 case R.id.homee:
                     getSupportFragmentManager().beginTransaction()
                             .setReorderingAllowed(true)
                             .replace(R.id.view2,home_frag.class,bundle)
                             .commit();
                     break;
                 case R.id.cart:
                     getSupportFragmentManager().beginTransaction()
                             .setReorderingAllowed(true)
                             .replace(R.id.view2,cart_frag.class,null)
                             .commit();
                     break;
                 case R.id.send:
                     getSupportFragmentManager().beginTransaction()
                             .setReorderingAllowed(true)
                             .replace(R.id.view2,sent_frag.class,null)
                             .commit();
                     break;


             }
             return true;
         }
     });


    }
}