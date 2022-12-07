package com.example.mybookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookstore.databinding.ActivityAddBookBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class Add_Book extends AppCompatActivity {

    ActivityAddBookBinding bb;
    String Bn,au,pu,Ed,pno,id;
    String otps;
    String otpr;

    AlertDialog.Builder builder1;

    FirebaseAuth auth;
    FirebaseDatabase database;

    EditText textview3;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bb=ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(bb.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        Log.i("addbook", "onshow add");
        View view2=getLayoutInflater().inflate(R.layout.activity_v_v_otp,null);
        textView=(TextView)view2.findViewById(R.id.textView);
        textview3=(EditText)view2.findViewById(R.id.textView3);
        button=(Button)view2.findViewById(R.id.button);

       // builder1=new AlertDialog.Builder(Add_Book.this);
        //builder1.setView(view2);
        //builder1.create();

        bb.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("addbook", "onclick add");
                Bn=bb.textView22.getText().toString();
                au=bb.textView23.getText().toString();
                pu=bb.textView24.getText().toString();
                Ed=bb.textView25.getText().toString();
                pno="91"+bb.textView26.getText().toString();

//                Intent intent=new Intent(Add_Book.this,v_v_otp.class);
//                intent.putExtra("bn",Bn);
//                intent.putExtra("au",au);
//                intent.putExtra("pu",pu);
//                intent.putExtra("Ed",Ed);
//                intent.putExtra("phone_no",pno);
//
//                startActivity(intent);

               // builder1.show();
                Log.i("addbook", "onclick add1");
                id = Bn + au + Ed;
                Log.i("addbook", "onclick add2");
                textView.setText(pno);

                auth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance();
                Log.i("addbook", "onclick add3");

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(pno)
                        .setActivity(Add_Book.this)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signinphoneno(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                otps = s;
                            }
                        }).build();

                PhoneAuthProvider.verifyPhoneNumber(options);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otpr = textview3.getText().toString();
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otps, otpr);
                        Log.i("signaddbbook", "outside");
                        signinphoneno(phoneAuthCredential);


                    }
                });
            }
         public void signinphoneno(PhoneAuthCredential phoneAuthCredential) {
             auth.signInWithCredential(phoneAuthCredential)
                     .addOnCompleteListener(Add_Book.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 Booklist booklist = new Booklist(Bn, au, Ed, pu);
                                 //database.getReference("available_book").child(id).setValue(booklist);
                                 FirebaseFirestore.getInstance().collection("available_book").document(id).set(booklist)
                                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                                             @Override
                                             public void onComplete(@NonNull Task<Void> task) {
                                                 Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT).show();
                                             }
                                         });
                             }
                         }
                     });
         }

        });



    }
}