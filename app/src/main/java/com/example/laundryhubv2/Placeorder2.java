package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Placeorder2 extends AppCompatActivity {


    Prices prices;
    TextView totalheavy;
    double total_price_heavy;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder2);

        prices = new Prices();
        totalheavy = findViewById(R.id. total1heavy);


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
                    prices.setWash_price(105);
                else
                    prices.setWash_price(0);
                break;

            case R.id.Checkboxdryheavy:
                if (checked)
                    prices.setDry_price(20);
                else
                    prices.setDry_price(0);
                break;

            case R.id.Checkboxfoldheavy:
                if (checked)
                    prices.setFold_price(20);
                else
                    prices.setFold_price(0);
                break;

            case R.id.Checkboxpressheavy:
                if (checked)
                    prices.setPress_price(20);
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