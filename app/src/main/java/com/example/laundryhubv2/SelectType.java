package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectType extends AppCompatActivity {

    private Button button,buttonsignout;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        buttonsignout = (Button)findViewById(R.id. Buttonsignout);
        fAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.Buttonsoft);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openplaceorder();
            }
        });

        button = (Button) findViewById(R.id.Buttonheavy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openplaceorder2();
            }
        });

        button = (Button) findViewById(R.id.Buttonviewbookingsoft);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openviewbooking();
            }
        });

        button = (Button) findViewById(R.id.Buttonviewbookingheavy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openviewbookingheavy();
            }
        });

        buttonsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(SelectType.this);
                builder.setTitle("SIGNOUT");
                builder.setMessage("ARE YOU SURE YOU WANT TO SIGNOUT?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fAuth.signOut();
                        signOutUser();
                        //String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        //databaseReference = FirebaseDatabase.getInstance().getReference().child(useridd);

                    }

                    private void signOutUser() {
                        Intent mainActivity = new Intent(SelectType.this, MainActivity.class);
                        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainActivity);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }

    
    public void openplaceorder(){
        Intent intent = new Intent(this, Placeorder.class);
        startActivity(intent);
    }

    private void openplaceorder2(){
        Intent intent = new Intent(this, Placeorder2.class);
        startActivity(intent);
    
    }

    private  void openviewbooking(){
        Intent intent = new Intent(this, Booking.class);
        startActivity((intent));
    }

    private  void openviewbookingheavy(){
        Intent intent = new Intent(this, Bookingheavy.class);
        startActivity((intent));
    }



}