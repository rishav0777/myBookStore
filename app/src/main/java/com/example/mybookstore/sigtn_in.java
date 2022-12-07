package com.example.mybookstore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookstore.databinding.ActivitySigtnInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class sigtn_in extends AppCompatActivity {

    ActivitySigtnInBinding bb;
    TextView textView;
    EditText text;
    Button button;
    AlertDialog.Builder dialog;

    FirebaseAuth auth;


    String otps;
    String otpr;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bb=ActivitySigtnInBinding.inflate(getLayoutInflater());
        setContentView(bb.getRoot());

        bb.textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("sign in","on click");
                Intent intent=new Intent(sigtn_in.this,MainActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });

        View view=getLayoutInflater().inflate(R.layout.activity_verifying_otp,null);
        textView=(TextView)view.findViewById(R.id.textView);
        text=(EditText)view.findViewById(R.id.textView3);
        button=(Button)view.findViewById(R.id.button);

        dialog=new AlertDialog.Builder(sigtn_in.this);
        dialog.setView(view);
        dialog.create();








        bb.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pno=bb.textView6.getText().toString();
                dialog.show();
                String phone_no="+91"+pno;

                textView.setText(phone_no);

                auth=FirebaseAuth.getInstance();


                PhoneAuthOptions options=PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phone_no)
                        .setActivity(sigtn_in.this)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signinphoneno(phoneAuthCredential,1);
                                Log.i("gh","a1");
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                otps=s;
                            }
                        }).build();

                PhoneAuthProvider.verifyPhoneNumber(options);




                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otpr=text.getText().toString();
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otps,otpr);
                        Log.i("sign","outside");
                        signinphoneno(credential,0);

                    }
                });
            }
            public void signinphoneno(PhoneAuthCredential phoneAuthCredential,int a){
                Log.i("sign","inside credential");
                auth.signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(sigtn_in.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isComplete()) {
                                    if(a==1){
                                        String key=task.getResult().getUser().getUid();
                                        Intent intent = new Intent(sigtn_in.this, MainActivity.class);
                                        intent.putExtra("key",key);
                                        intent.putExtra("flag","1");
                                        startActivity(intent);
                                    }
                                     else{
                                    Log.i("gh","nahhhhhhhhh");
                                        String key=task.getResult().getUser().getUid();
                                        Intent intent = new Intent(sigtn_in.this, taking_Data.class);
                                        intent.putExtra("key",key);
                                        intent.putExtra("flag","0");
                                        startActivity(intent);
                                    }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "authentication failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
//                Intent intent=new Intent(sigtn_in.this,verifying_otp.class);
//                intent.putExtra("phone_no",pno);
//                startActivity(intent);
            }
        });


    }
}