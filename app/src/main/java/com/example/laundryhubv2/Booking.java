    package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class Booking extends AppCompatActivity {

        private DatabaseReference databaseReferencebooking;
        private TextView washbooking,drybooking,foldbooking,pressbooking,weightbooking,totalpricebooking, timebooking;
        private Button buttoncancel, buttondelivered;
        DatabaseReference databaseReferencebooking2;
        FirebaseAuth Fauthh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        //Fauthh.getCurrentUser();
        Fauthh = FirebaseAuth.getInstance();
        String uid = (String) Fauthh.getCurrentUser().getUid();
        databaseReferencebooking = FirebaseDatabase.getInstance().getReference().child("Soft Orders").child(Fauthh.getCurrentUser().getUid());
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference();
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference("Soft Orders").child(FirebaseAuth.getInstance().getUid()+"/Wash");
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference("Soft Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wash");
        washbooking = (TextView) findViewById(R.id.Textviewwashbooking);
        drybooking = (TextView) findViewById(R.id. Textviewdrybooking);
        foldbooking = (TextView) findViewById(R.id. Textviewfoldbooking);
        pressbooking = (TextView) findViewById(R.id. Textviewpressbooking);
        weightbooking = (TextView) findViewById(R.id. Textviewweightbooking);
        totalpricebooking = (TextView) findViewById(R.id. Textviewtotalpricebooking);
        buttoncancel = (Button) findViewById(R.id.Buttoncancelorder);
        timebooking = (TextView) findViewById(R.id. Textviewtime);
        buttondelivered = (Button) findViewById(R.id. Buttondelivered);






        databaseReferencebooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // role = snapshot.getValue(String.class);

                String washhh = (String) dataSnapshot.child("Wash").getValue();
                washbooking.setText("Wash: " + washhh);

                if (washhh == null){
                    washbooking.setText("Wash: ");
                }


                String dryyy = (String) dataSnapshot.child("Dry").getValue();
                drybooking.setText(("Dry: " + dryyy));

                if (dryyy ==null){
                    drybooking.setText(("Dry: "));
                }


                String folddd = (String) dataSnapshot.child("Fold").getValue();
                foldbooking.setText(("Fold: " + folddd));

                if (dryyy ==null){
                    foldbooking.setText(("Fold: "));
                }

                String presss = (String) dataSnapshot.child("Press").getValue();
                pressbooking.setText(("Press: " + presss));

                if (dryyy ==null){
                    pressbooking.setText(("Press: "));
                }

                String weighttt = (String) dataSnapshot.child("Weight").getValue();
                weightbooking.setText(("Weight: " + weighttt));

                if (weighttt ==null) {
                    weightbooking.setText(("Weight: "));
                }

                String timeee = (String) dataSnapshot.child("DateAndTime").getValue();
                timebooking.setText("Placed on: " + timeee);

                if (timeee == null){
                    timebooking.setText("Placed on: ");
                }



                String totalpriceee = (String) dataSnapshot.child("TotalPrice").getValue();
                totalpricebooking.setText(( totalpriceee));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Booking.this);
                builder.setTitle("CANCEL BOOKING");
                builder.setMessage("ARE YOU SURE YOU WANT TO CANCEL YOUR BOOKING?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReferencebooking2 = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useridd);
                        databaseReferencebooking2.setValue(null);
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




        buttondelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Booking.this);
                builder.setTitle("DELIVERED");
                builder.setMessage("DID THE CLOTHES ARRIVE?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReferencebooking2 = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useridd);
                        databaseReferencebooking2.setValue(null);
                        Toast.makeText(Booking.this,"THANK YOU FOR AVAILING LAUNDRYHUB'S LAUNDRY SERVICE! HAVE A GOOD DAY!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Booking.this, SelectType.class));
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
}