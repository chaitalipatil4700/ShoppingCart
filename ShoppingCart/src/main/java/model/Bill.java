package model;

import java.util.List;
import java.util.Map;

public class Bill {
    Map<Barcode,Integer> products;
    Double totalPrice;

    public Bill(Map<Barcode, Integer> products, Double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice=0.0;
        for( Map.Entry<Barcode, Integer> entry:products.entrySet()){
            totalPrice+= entry.getValue()*entry.getKey().getItemPrice();
        }
    }


    public Map<Barcode, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Barcode, Integer> products) {
        this.products = products;
    }
}
