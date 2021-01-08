    package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class Booking extends AppCompatActivity {

        private DatabaseReference databaseReferencebooking;
        private TextView washbooking,drybooking,foldbooking,pressbooking,weightbooking,totalpricebooking;
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





        databaseReferencebooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // role = snapshot.getValue(String.class);

                String washhh = (String) dataSnapshot.child("Wash").getValue();
                washbooking.setText("Wash: " + washhh);

                if (washhh == null){
                    washbooking.setText("Wash: No");
                }


                String dryyy = (String) dataSnapshot.child("Dry").getValue();
                drybooking.setText(("Dry: " + dryyy));

                if (dryyy ==null){
                    drybooking.setText(("Dry: No"));
                }


                String folddd = (String) dataSnapshot.child("Fold").getValue();
                foldbooking.setText(("Fold: " + folddd));

                if (dryyy ==null){
                    foldbooking.setText(("Fold: No"));
                }

                String presss = (String) dataSnapshot.child("Press").getValue();
                pressbooking.setText(("Press: " + presss));

                if (dryyy ==null){
                    pressbooking.setText(("Press: No"));
                }

                String weighttt = (String) dataSnapshot.child("Weight").getValue();
                weightbooking.setText(("Weight: " + weighttt));

                if (weighttt ==null){
                    weightbooking.setText(("Weight: 3 Kilos"));
                }


                String totalpriceee = (String) dataSnapshot.child("Total Price").getValue();
                totalpricebooking.setText(( totalpriceee));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}