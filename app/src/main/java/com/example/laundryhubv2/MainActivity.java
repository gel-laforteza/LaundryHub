package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button button;
    TextInputLayout email, pass;
    Button login;
    String emailid, pwd;
    FirebaseAuth Fauth;
    FirebaseAuth Fauthh;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.Buttonregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        try {

            email =(TextInputLayout)findViewById(R.id.Textinputemail);
            pass = (TextInputLayout)findViewById(R.id.Textinputpassword);
            login = (Button)findViewById(R.id.Buttonlogin);

            Fauth = FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){
                        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Signing in please wait...");
                        mDialog.show();


                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()) {
                                        mDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "You have successfully logged in!", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(MainActivity.this, Placeorder.class);
                                        startActivity(Z);
                                        finish();
                                    }else{
                                        ReusableCodeForAll.ShowAlert(MainActivity.this, "Verification failed.", "Please verify your email to log in.");

                                    }
                                }else{
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(MainActivity.this, "Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, register.class));
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;
        if(TextUtils.isEmpty(emailid)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }else{
            if(emailid.matches(emailpattern)) {
                isvalidemail = true;
            }else{
                email.setErrorEnabled((true));
                email.setError("Invalid email address");
            }
        }
        if(TextUtils.isEmpty(pwd)) {

            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Fauthh = FirebaseAuth.getInstance();
                    if(Fauthh.getCurrentUser()!=null){
                        if(Fauthh.getCurrentUser().isEmailVerified()){
                            Fauthh=FirebaseAuth.getInstance();

                            databaseReference = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getUid());
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot){
                                    String role = snapshot.getValue(String.class);
                                    if(role.equals("Customer")){
                                        startActivity(new Intent(MainActivity.this,Placeorder.class));
                                        finish();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

                                }
                            });
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Check whether you have verified your email, otherwise please verify to log in");
                            builder.setCancelable(false);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            Fauthh.signOut();
                        }
                    }
                }
            },3000);
        }
    }


    public void openRegister() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}