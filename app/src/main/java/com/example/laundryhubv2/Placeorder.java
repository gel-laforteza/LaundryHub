package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Placeorder extends AppCompatActivity {

    Prices prices;
    TextView total;
    double total_price;
    TextView Totalprice;
    Button Buttonplaceorder;
    FirebaseAuth FAuthhh;
    DatabaseReference databaseReferenceee;
    FirebaseDatabase firebaseDatabaseee;
    CheckBox Wash, Dry, Fold, Press;
    RadioButton Four, Five, Six, Seven, Eight;
    String wash, dry, fold, press, four, five, six, seven, eight, totalprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);

        prices = new Prices();
        total = findViewById(R.id. total1);
        Wash = findViewById(R.id. Checkboxwash);
        Dry = (CheckBox)findViewById(R.id. Checkboxdry);
        Fold = (CheckBox)findViewById(R.id. Checkboxfold);
        Press = (CheckBox)findViewById(R.id. Checkboxpress);
        Four = (RadioButton)findViewById(R.id. Radiobutton4);
        Five = (RadioButton)findViewById(R.id. Radiobutton5);
        Six = (RadioButton)findViewById(R.id. Radiobutton6);
        Seven = (RadioButton)findViewById(R.id. Radiobutton7);
        Eight = (RadioButton)findViewById(R.id. Radiobutton8);
        Buttonplaceorder = (Button)findViewById(R.id. Buttonplaceordersoft);
        Totalprice = (TextView)findViewById(R.id. total1);


        FAuthhh = FirebaseAuth.getInstance();

        Buttonplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String totall = Totalprice.getText().toString().trim();
                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useridd);
                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Total Price", totall);

                databaseReferenceee.push().setValue(dataMap);



                if (Wash.isChecked()) {
                    String washh = Wash.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Wash", washh);

                    databaseReferenceee.push().setValue(dataMapp);
                }

                if (Dry.isChecked()){

                    String dryy = Dry.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Dry", dryy);

                    databaseReferenceee.push().setValue(dataMapp);

                }

                if (Fold.isChecked()){
                    String foldd = Fold.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Fold", foldd);

                    databaseReferenceee.push().setValue(dataMapp);


                }

                if (Press.isChecked()){
                    String presss = Press.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Press", presss);

                    databaseReferenceee.push().setValue(dataMapp);


                }

                if (Four.isChecked()){
                    String fourr = Four.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Weight", fourr);

                    databaseReferenceee.push().setValue(dataMapp);

                }

                if (Five.isChecked()){
                    String fivee = Five.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Weight", fivee);

                    databaseReferenceee.push().setValue(dataMapp);

                }

                if (Six.isChecked()){
                    String sixx = Six.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Weight", sixx);

                    databaseReferenceee.push().setValue(dataMapp);

                }

                if (Seven.isChecked()){
                    String sevenn = Seven.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Weight", sevenn);

                    databaseReferenceee.push().setValue(dataMapp);

                }

                if (Eight.isChecked()){
                    String eightt = Seven.getText().toString().trim();
                    String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    HashMap<String, String> dataMapp = new HashMap<String, String>();
                    dataMapp.put("Weight", eightt);

                    databaseReferenceee.push().setValue(dataMapp);

                }



            }
        });


    }



    public void radioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){

            case R.id.Radiobutton4:
                if (checked)
                    prices.setLaundry_prices(35);
                break;

            case R.id.Radiobutton5:
                if (checked)
                    prices.setLaundry_prices(70);
                break;

            case R.id.Radiobutton6:
                if (checked)
                    prices.setLaundry_prices(105);
                break;

            case R.id.Radiobutton7:
                if (checked)
                    prices.setLaundry_prices(140);
                break;

            case R.id.Radiobutton8:
                if (checked)
                    prices.setLaundry_prices(175);
                break;
        }

        total.setText("TOTAL PRICE:" + calculate_total1());
    }

    private double calculate_total1(){
        total_price = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price;
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.Checkboxwash:
                if (checked)
                    prices.setWash_price(105);
                else
                    prices.setWash_price(0);
                break;

            case R.id.Checkboxdry:
                if (checked)
                    prices.setDry_price(20);
                else
                    prices.setDry_price(0);
                break;

            case R.id.Checkboxfold:
                if (checked)
                    prices.setFold_price(20);
                else
                    prices.setFold_price(0);
                break;

            case R.id.Checkboxpress:
                if (checked)
                    prices.setPress_price(20);
                else
                    prices.setPress_price(0);
                break;

        }

        total.setText("TOTAL PRICE:" + calculate_total());
    }

    private double calculate_total(){
        total_price = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price;
    }
}