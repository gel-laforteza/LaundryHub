package com.example.laundryhubv2;

public class Prices {

    double laundry_prices;
    double wash_price = 0;
    double dry_price = 0;
    double fold_price = 0;
    double press_price = 0;

    public double getLaundry_prices() {
        return laundry_prices;
    }

    public void setLaundry_prices(double laundry_prices) {
        this.laundry_prices = laundry_prices;
    }

    public double getWash_price() {
        return wash_price;
    }

    public void setWash_price(double wash_price) {
        this.wash_price = wash_price;
    }

    public double getDry_price() {
        return dry_price;
    }

    public void setDry_price(double dry_price) {
        this.dry_price = dry_price;
    }

    public double getFold_price() {
        return fold_price;
    }

    public void setFold_price(double fold_price) {
        this.fold_price = fold_price;
    }

    public double getPress_price() {
        return press_price;
    }

    public void setPress_price(double press_price) {
        this.press_price = press_price;
    }

    public Prices() {
    }
}
