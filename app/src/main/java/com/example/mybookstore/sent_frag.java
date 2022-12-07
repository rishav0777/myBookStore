package com.example.mybookstore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


public class sent_frag extends Fragment {

    RecyclerView recyclerView;
    Ins_row ins_row;
    String na[];
    Button add;

    String otps;
    String otpr;
    String bna,au,pu,ed,pno;
    String id;

    EditText textview3;
    TextView textView;
    Button button,continueb;

    FirebaseAuth auth;
    FirebaseDatabase database;


    EditText editText22,editText23,editText24,editText25,editText26;


    AlertDialog.Builder builder,builder1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sent_frag, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.view4);
        add=(Button)view.findViewById(R.id.order);


        na=getResources().getStringArray(R.array.things);

        ins_row=new Ins_row(getActivity(),na);
        recyclerView.setAdapter(ins_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        View view1=getLayoutInflater().inflate(R.layout.activity_add__book,null);
        editText22=(EditText)view1.findViewById(R.id.textView22);
        editText23=(EditText)view1.findViewById(R.id.textView23);
        editText24=(EditText)view1.findViewById(R.id.textView24);
        editText25=(EditText)view1.findViewById(R.id.textView25);
        editText26=(EditText)view1.findViewById(R.id.textView26);
        continueb=(Button)view1.findViewById(R.id.button4);

        builder=new AlertDialog.Builder(getActivity());
        builder.setView(view1);
        builder.create();





        View view2=getLayoutInflater().inflate(R.layout.activity_v_v_otp,null);
        textView=(TextView)view2.findViewById(R.id.textView);
        textview3=(EditText)view2.findViewById(R.id.textView3);
        button=(Button)view2.findViewById(R.id.button);

        builder1=new AlertDialog.Builder(getActivity());
        builder1.setView(view2);
        builder1.create();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("frag", "onclick add");
                builder.show();
                Log.i("frag", "onshow add");



                continueb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("frag", "onclickbutton4 add");
                        bna = editText22.getText().toString();
                        au = editText23.getText().toString();
                        pu = editText24.getText().toString();
                        ed = editText25.getText().toString();
                        pno = editText26.getText().toString();

                        builder1.show();
                        String phone_no = "+91" + pno;
                        id = na + au + ed;

                        textView.setText(phone_no);

                        auth = FirebaseAuth.getInstance();
                        database = FirebaseDatabase.getInstance();


                        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(phone_no)
                                .setActivity(getActivity())
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
                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otps, otpr);
                                Log.i("sign", "outside");
                                signinphoneno(credential);

                            }
                        });
                    }


                    private void signinphoneno (PhoneAuthCredential phoneAuthCredential){
                        auth.signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.i("sent","successfull");
                                            Booklist booklist = new Booklist(au,bna,ed, pu);
                                            //database.getReference("available_book").child(au).setValue(booklist);
                                            FirebaseFirestore.getInstance().collection("available_book").document(id).set(booklist)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(),"Inserted",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                });
            }
        });

           

        return view;
    }
}