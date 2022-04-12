package model;


import java.util.Collections;
import java.util.List;

public class Bill {
    private List<Product> products;
    private Double totalPrice;

    public Bill(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getQuantity() * product.getItem().getItemPrice();
        }
    }


    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
