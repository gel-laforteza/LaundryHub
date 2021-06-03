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

public class Detailsheavy extends AppCompatActivity {

    TextInputLayout Textinputlayoutfirstnameheavy, Textinputlayoutlastnameheavy, Textinputlayoutaddressheavy, Textinputlayoutmobilenumberheavy;
    Button Buttonconfirmheavy;
    FirebaseAuth FAuth;
    DatabaseReference databaseReferenceheavy;
    FirebaseDatabase firebaseDatabase;
    String firstnameheavy, lastnameheavy, addressheavy, mobilenumberheavy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsheavy);

        Textinputlayoutfirstnameheavy = (TextInputLayout) findViewById(R.id.Textinputfirstnameheavy);
        Textinputlayoutlastnameheavy = (TextInputLayout)findViewById(R.id.Textinputlastnameheavy);
        Textinputlayoutaddressheavy = (TextInputLayout)findViewById(R.id.Textinputaddressheavy);
        Textinputlayoutmobilenumberheavy= (TextInputLayout)findViewById(R.id.Textinputmobilenumberheavy);

        Buttonconfirmheavy = (Button)findViewById(R.id.Buttonconfirmheavy);


        FAuth = FirebaseAuth.getInstance();


        Buttonconfirmheavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstnameheavy = Textinputlayoutfirstnameheavy.getEditText().getText().toString().trim();
                lastnameheavy = Textinputlayoutlastnameheavy.getEditText().getText().toString().trim();
                addressheavy = Textinputlayoutaddressheavy.getEditText().getText().toString().trim();
                mobilenumberheavy = Textinputlayoutmobilenumberheavy.getEditText().getText().toString().trim();

                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReferenceheavy = FirebaseDatabase.getInstance().getReference("Heavy Orders").child(useridd);


                if(mobilenumberheavy.length() < 11){
                    ReusableCodeForAll.ShowAlert(Detailsheavy.this, "", ("PLEASE ENTER 11 DIGIT MOBILE NUMBER"));
                }

                if(addressheavy.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Detailsheavy.this, "", ("PLEASE ENTER THE DELIVERY ADDRESS"));
                }

                if(lastnameheavy.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Detailsheavy.this, "", ("PLEASE ENTER YOUR LASTNAME"));
                }

                if(firstnameheavy.isEmpty()){
                    ReusableCodeForAll.ShowAlert(Detailsheavy.this, "", ("PLEASE ENTER YOUR FIRSTNAME"));
                }

                else if(mobilenumberheavy.length() < 11 == false && firstnameheavy.isEmpty() == false && lastnameheavy.isEmpty() == false && addressheavy.isEmpty() == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Detailsheavy.this);
                    builder.setMessage("BOOKING PLACED! A STAFF WILL PICKUP YOUR BOOKING SOON. YOU CAN NOW VIEW YOUR BOOKING!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(Detailsheavy.this, SelectType.class));
                        }
                    });
                    AlertDialog Alert = builder.create();
                    Alert.show();

                }

                databaseReferenceheavy.child("Firstname").setValue(firstnameheavy);
                databaseReferenceheavy.child("Lastname").setValue(lastnameheavy);
                databaseReferenceheavy.child("Address").setValue(addressheavy);
                databaseReferenceheavy.child("MobileNumber").setValue(mobilenumberheavy);

            }
        });
    }
}