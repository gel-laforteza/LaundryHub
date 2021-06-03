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

public class Bookingheavy extends AppCompatActivity {

    private DatabaseReference databaseReferencebookingheavy;
    private TextView washbookingheavy,drybookingheavy,foldbookingheavy,pressbookingheavy,weightbookingheavy,totalpricebookingheavy, timebookingheavy;
    private Button buttoncancelheavy, buttondeliveredheavy;
    DatabaseReference databaseReferencebooking3;
    FirebaseAuth Fauthhheavy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingheavy);

        //Fauthh.getCurrentUser();
        Fauthhheavy = FirebaseAuth.getInstance();
        String uid = (String) Fauthhheavy.getCurrentUser().getUid();
        databaseReferencebookingheavy = FirebaseDatabase.getInstance().getReference().child("Heavy Orders").child(Fauthhheavy.getCurrentUser().getUid());
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference();
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference("Soft Orders").child(FirebaseAuth.getInstance().getUid()+"/Wash");
        //databaseReferencebooking = FirebaseDatabase.getInstance().getReference("Soft Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wash");
        washbookingheavy = (TextView) findViewById(R.id.Textviewwashbookingheavy);
        drybookingheavy = (TextView) findViewById(R.id. Textviewdrybookingheavy);
        foldbookingheavy = (TextView) findViewById(R.id. Textviewfoldbookingheavy);
        pressbookingheavy = (TextView) findViewById(R.id. Textviewpressbookingheavy);
        weightbookingheavy = (TextView) findViewById(R.id. Textviewweightbookingheavy);
        totalpricebookingheavy = (TextView) findViewById(R.id. Textviewtotalpricebookingheavy);
        buttoncancelheavy = (Button) findViewById(R.id.Buttoncancelorderheavy);
        timebookingheavy = (TextView) findViewById(R.id. Textviewtimeheavy);
        buttondeliveredheavy = (Button) findViewById(R.id. Buttondeliveredheavy);





        databaseReferencebookingheavy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // role = snapshot.getValue(String.class);

                String washhh = (String) dataSnapshot.child("Wash").getValue();
                washbookingheavy.setText("Wash: " + washhh);

                if (washhh == null){
                    washbookingheavy.setText("Wash: ");
                }


                String dryyy = (String) dataSnapshot.child("Dry").getValue();
                drybookingheavy.setText(("Dry: " + dryyy));

                if (dryyy ==null){
                    drybookingheavy.setText(("Dry: "));
                }


                String folddd = (String) dataSnapshot.child("Fold").getValue();
                foldbookingheavy.setText(("Fold: " + folddd));

                if (dryyy ==null){
                    foldbookingheavy.setText(("Fold: "));
                }

                String presss = (String) dataSnapshot.child("Press").getValue();
                pressbookingheavy.setText(("Press: " + presss));

                if (dryyy ==null){
                    pressbookingheavy.setText(("Press: "));
                }

                String weighttt = (String) dataSnapshot.child("Weight").getValue();
                weightbookingheavy.setText(("Weight: " + weighttt));

                if (weighttt ==null){
                    weightbookingheavy.setText(("Weight: "));
                }

                String timeee = (String) dataSnapshot.child("DateAndTime").getValue();
                timebookingheavy.setText("Placed on: " + timeee);

                if (timeee == null){
                    timebookingheavy.setText("Placed on: ");
                }


                String totalpriceee = (String) dataSnapshot.child("TotalPrice").getValue();
                totalpricebookingheavy.setText(( totalpriceee));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttoncancelheavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Bookingheavy.this);
                builder.setTitle("CANCEL BOOKING");
                builder.setMessage("ARE YOU SURE YOU WANT TO CANCEL YOUR BOOKING?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReferencebookingheavy = FirebaseDatabase.getInstance().getReference("Heavy Orders").child(useridd);
                        databaseReferencebookingheavy.setValue(null);
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

        buttondeliveredheavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Bookingheavy.this);
                builder.setTitle("DELIVERED");
                builder.setMessage("DID THE CLOTHES ARRIVE?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReferencebookingheavy = FirebaseDatabase.getInstance().getReference("Heavy Orders").child(useridd);
                        databaseReferencebookingheavy.setValue(null);
                        Toast.makeText(Bookingheavy.this,"THANK YOU FOR AVAILING LAUNDRYHUB'S LAUNDRY SERVICE! HAVE A GOOD DAY!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Bookingheavy.this, SelectType.class));
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

