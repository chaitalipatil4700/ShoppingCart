package model;

import java.util.Objects;

public class Product {

    private final Item item;
    private Integer quantity = 1;

    public Product(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity(){
        this.quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(item, product.item) && Objects.equals(quantity, product.quantity);
    }
}
