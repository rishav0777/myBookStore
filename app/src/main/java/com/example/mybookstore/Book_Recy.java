package com.example.mybookstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mybookstore.databinding.ActivityBookRecyBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Book_Recy extends AppCompatActivity {

    ActivityBookRecyBinding bb;
    String na[];
    String au[];
    Book_row book_row;

    RecyclerView recyclerView;

    FirebaseDatabase database;
    FirebaseAuth auth;

    ArrayList<String>a=new ArrayList<String>();
    ArrayList<String>b=new ArrayList<String>();
    Button addcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bb=ActivityBookRecyBinding.inflate(getLayoutInflater());
        setContentView(bb.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        addcart=(Button)findViewById(R.id.addcart);
        addcart.setVisibility(View.INVISIBLE);

        recyclerView=(RecyclerView)findViewById(R.id.view4);
        Log.i("gvhj","name");
//        na=getResources().getStringArray(R.array.booklist);
//        au=getResources().getStringArray(R.array.author);
//
//        book_row=new Book_row(Book_Recy.this,na,au);
//        recyclerView.setAdapter(book_row);
//        recyclerView.setLayoutManager(new LinearLayoutManager(Book_Recy.this));


        /*database.getReference("available_book").limitToLast(50)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Booklist booklist=dataSnapshot.getValue(Booklist.class);
                    String bname=booklist.getBook_Name();
                    String author=booklist.getAuthor();
                   // Map booklist=(Map)dataSnapshot.getValue();
                    //String bname=booklist.get("book_Name").toString();
                   // String author=booklist.get("author").toString();
                    a.add(bname);
                    b.add(author);
                    Log.i("gvhj",bname);
                    Log.i("gvhj",author);



                }
                na= Arrays.copyOf(a.toArray(),a.size(),String[].class);
                au= Arrays.copyOf(b.toArray(),b.size(),String[].class);
                Log.i("gvhj",na[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                na=getResources().getStringArray(R.array.booklist);
                au=getResources().getStringArray(R.array.author);
                Log.i("gvhj","cancel");
            }
        });*/
//    for (int j=0;j<na.length;j++)
//       Log.i("gvhjjjjjj",na[j]);
        try {
            FirebaseFirestore.getInstance().collection("available_book").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    Log.i("gvhj","successss");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("gvhj","failuree");
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }


        book_row=new Book_row(Book_Recy.this,na,au);
        recyclerView.setAdapter(book_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(Book_Recy.this));









    }
}