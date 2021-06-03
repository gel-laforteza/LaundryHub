package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Details extends AppCompatActivity {

    TextInputLayout Textinputlayoutfirstname, Textinputlayoutlastname, Textinputlayoutaddress, Textinputlayoutmobilenumber;
    Button Buttonconfirm;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String firstname, lastname, address, mobilenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Textinputlayoutfirstname = (TextInputLayout) findViewById(R.id.Textinputfirstname);
        Textinputlayoutlastname = (TextInputLayout)findViewById(R.id.Textinputlastname);
        Textinputlayoutaddress = (TextInputLayout)findViewById(R.id.Textinputaddress);
        Textinputlayoutmobilenumber= (TextInputLayout)findViewById(R.id.Textinputmobilenumber);

        Buttonconfirm = (Button)findViewById(R.id.Buttonconfirm);


        FAuth = FirebaseAuth.getInstance();


        Buttonconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = Textinputlayoutfirstname.getEditText().getText().toString().trim();
                lastname = Textinputlayoutlastname.getEditText().getText().toString().trim();
                address = Textinputlayoutaddress.getEditText().getText().toString().trim();
                mobilenumber = Textinputlayoutmobilenumber.getEditText().getText().toString().trim();

                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useridd);


                if(mobilenumber.length() < 11){
                    ReusableCodeForAll.ShowAlert(Details.this, "", ("PLEASE ENTER 11 DIGIT MOBILE NUMBER"));
                }

                if(address.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Details.this, "", ("PLEASE ENTER THE DELIVERY ADDRESS"));
                }

                if(lastname.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Details.this, "", ("PLEASE ENTER YOUR LASTNAME"));
                }

                if(firstname.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Details.this, "", ("PLEASE ENTER YOUR FIRSTNAME"));
                }

                else if(mobilenumber.length() < 11 == false && firstname.isEmpty() == false && lastname.isEmpty() == false && address.isEmpty() == false){
                     AlertDialog.Builder builder = new AlertDialog.Builder(Details.this);
                     builder.setMessage("BOOKING PLACED! A STAFF WILL PICKUP YOUR BOOKING SOON. YOU CAN NOW VIEW YOUR BOOKING!");
                     builder.setCancelable(false);
                     builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                             startActivity(new Intent(Details.this, SelectType.class));
                         }
                     });
                     AlertDialog Alert = builder.create();
                     Alert.show();
                }

                databaseReference.child("Firstname").setValue(firstname);
                databaseReference.child("Lastname").setValue(lastname);
                databaseReference.child("Address").setValue(address);
                databaseReference.child("MobileNumber").setValue(mobilenumber);


            }
        });

    }
}