package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectType extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

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