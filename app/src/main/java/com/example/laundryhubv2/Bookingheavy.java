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

public class Bookingheavy extends AppCompatActivity {

    private DatabaseReference databaseReferencebookingheavy;
    private TextView washbookingheavy,drybookingheavy,foldbookingheavy,pressbookingheavy,weightbookingheavy,totalpricebookingheavy;
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





        databaseReferencebookingheavy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // role = snapshot.getValue(String.class);

                String washhh = (String) dataSnapshot.child("Wash").getValue();
                washbookingheavy.setText("Wash: " + washhh);

                if (washhh == null){
                    washbookingheavy.setText("Wash: No");
                }


                String dryyy = (String) dataSnapshot.child("Dry").getValue();
                drybookingheavy.setText(("Dry: " + dryyy));

                if (dryyy ==null){
                    drybookingheavy.setText(("Dry: No"));
                }


                String folddd = (String) dataSnapshot.child("Fold").getValue();
                foldbookingheavy.setText(("Fold: " + folddd));

                if (dryyy ==null){
                    foldbookingheavy.setText(("Fold: No"));
                }

                String presss = (String) dataSnapshot.child("Press").getValue();
                pressbookingheavy.setText(("Press: " + presss));

                if (dryyy ==null){
                    pressbookingheavy.setText(("Press: No"));
                }

                String weighttt = (String) dataSnapshot.child("Weight").getValue();
                weightbookingheavy.setText(("Weight: " + weighttt));

                if (weighttt ==null){
                    weightbookingheavy.setText(("Weight: 3 Kilos"));
                }


                String totalpriceee = (String) dataSnapshot.child("Total Price").getValue();
                totalpricebookingheavy.setText(( totalpriceee));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}

