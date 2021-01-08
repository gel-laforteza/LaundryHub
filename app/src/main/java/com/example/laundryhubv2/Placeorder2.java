package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Placeorder2 extends AppCompatActivity {


    Prices prices;
    TextView totalheavy;
    double total_price_heavy;
    TextView Totalpriceheavy;
    Button Buttonplaceorderheavy;
    FirebaseAuth FAuthhhheavy;
    DatabaseReference databaseReferenceeeheavy;
    FirebaseDatabase firebaseDatabaseeeheavy;
    CheckBox Washheavy, Dryheavy, Foldheavy, Pressheavy;
    RadioButton Fourheavy, Fiveheavy, Sixheavy, Sevenheavy, Eightheavy;
    String wash, dry, fold, press, four, five, six, seven, eight, totalprice;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder2);

        prices = new Prices();
        totalheavy = findViewById(R.id. total1heavy);
        Washheavy = findViewById(R.id. Checkboxwashheavy);
        Dryheavy = (CheckBox)findViewById(R.id. Checkboxdryheavy);
        Foldheavy = (CheckBox)findViewById(R.id. Checkboxfoldheavy);
        Pressheavy = (CheckBox)findViewById(R.id. Checkboxpressheavy);
        Fourheavy = (RadioButton)findViewById(R.id. Radiobutton4heavy);
        Fiveheavy = (RadioButton)findViewById(R.id. Radiobutton5heavy);
        Sixheavy = (RadioButton)findViewById(R.id. Radiobutton6heavy);
        Sevenheavy = (RadioButton)findViewById(R.id. Radiobutton7heavy);
        Eightheavy = (RadioButton)findViewById(R.id. Radiobutton8heavy);
        Buttonplaceorderheavy = (Button)findViewById(R.id. buttonheavy);
        Totalpriceheavy = (TextView)findViewById(R.id. total1heavy);

        FAuthhhheavy = FirebaseAuth.getInstance();

        Buttonplaceorderheavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String totall = Totalpriceheavy.getText().toString().trim();
                //totalprice = Totalprice.getText().toString().trim();
                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReferenceeeheavy = FirebaseDatabase.getInstance().getReference("Heavy Orders").child(useridd);
                //HashMap<String, String> dataMap = new HashMap<String, String>();
                //dataMap.put("Total Price", totall);

                databaseReferenceeeheavy.child("Total Price").setValue(totall);



                if (Washheavy.isChecked()) {
                    //String washh = Wash.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useridd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Wash", washh);

                    databaseReferenceeeheavy.child("Wash").setValue("Yes");

                } else {
                    databaseReferenceeeheavy.child("Wash").setValue("No");

                }

                if (Dryheavy.isChecked()){

                    //String dryy = Dry.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Dry", dryy);

                    databaseReferenceeeheavy.child("Dry").setValue("Yes");

                } else {
                    databaseReferenceeeheavy.child("Dry").setValue("No");

                }

                if (Foldheavy.isChecked()){
                    //String foldd = Fold.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Fold", foldd);

                    databaseReferenceeeheavy.child("Fold").setValue("Yes");

                } else {
                    databaseReferenceeeheavy.child("Fold").setValue("No");


                }

                if (Pressheavy.isChecked()){
                    //String presss = Press.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Press", presss);

                    databaseReferenceeeheavy.child("Press").setValue("Yes");

                } else {
                    databaseReferenceeeheavy.child("Press").setValue("No");

                }

                if (Fourheavy.isChecked()) {
                    //String fourr = Four.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Weight", fourr);

                    databaseReferenceeeheavy.child("Weight").setValue("4 Kilos");

                }else{
                    databaseReferenceeeheavy.child("Weight").setValue("3 Kilos");


                }

                if (Fiveheavy.isChecked()){
                    //String fivee = Five.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Weight", fivee);

                    databaseReferenceeeheavy.child("Weight").setValue("5 Kilos");

                }else{
                    databaseReferenceeeheavy.child("Weight").setValue("3 Kilos");

                }

                if (Sixheavy.isChecked()){
                    //String sixx = Six.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Weight", sixx);

                    databaseReferenceeeheavy.child("Weight").setValue("6 Kilos");

                }else{
                    databaseReferenceeeheavy.child("Weight").setValue("3 Kilos");

                }

                if (Sevenheavy.isChecked()){
                    //String sevenn = Seven.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Weight", sevenn);

                    databaseReferenceeeheavy.child("Weight").setValue("7 Kilos");

                }else{
                    databaseReferenceeeheavy.child("Weight").setValue("3 Kilos");

                }

                if (Eightheavy.isChecked()){
                    //String eightt = Seven.getText().toString().trim();
                    //String useriddd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //databaseReferenceee = FirebaseDatabase.getInstance().getReference("Soft Orders").child(useriddd);
                    //HashMap<String, String> dataMapp = new HashMap<String, String>();
                    //dataMapp.put("Weight", eightt);

                    //databaseReferenceee.setValue(dataMapp);

                    databaseReferenceeeheavy.child("Weight").setValue("8 Kilos");

                }else{
                    databaseReferenceeeheavy.child("Weight").setValue("3 Kilos");

                }



            }
        });



    }

    public void radioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){

            case R.id.Radiobutton4heavy:
                if (checked)
                    prices.setLaundry_prices(40);
                break;

            case R.id.Radiobutton5heavy:
                if (checked)
                    prices.setLaundry_prices(80);
                break;

            case R.id.Radiobutton6heavy:
                if (checked)
                    prices.setLaundry_prices(120);
                break;

            case R.id.Radiobutton7heavy:
                if (checked)
                    prices.setLaundry_prices(160);
                break;

            case R.id.Radiobutton8heavy:
                if (checked)
                    prices.setLaundry_prices(200);
                break;
        }

        totalheavy.setText("TOTAL PRICE:" + calculate_total1heavy());
    }

    private double calculate_total1heavy(){
        total_price_heavy = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price_heavy;
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.Checkboxwashheavy:
                if (checked)
                    prices.setWash_price(120);
                else
                    prices.setWash_price(0);
                break;

            case R.id.Checkboxdryheavy:
                if (checked)
                    prices.setDry_price(25);
                else
                    prices.setDry_price(0);
                break;

            case R.id.Checkboxfoldheavy:
                if (checked)
                    prices.setFold_price(25);
                else
                    prices.setFold_price(0);
                break;

            case R.id.Checkboxpressheavy:
                if (checked)
                    prices.setPress_price(25);
                else
                    prices.setPress_price(0);
                break;

        }

        totalheavy.setText("TOTAL PRICE:" + calculate_totalheavy());
    }

    private double calculate_totalheavy(){
        total_price_heavy = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price_heavy;
    }
}