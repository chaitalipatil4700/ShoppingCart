package model;

import java.util.Objects;

public class Barcode {
    private String itemNumber;
    private Item item;
    Double itemPrice;


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }


    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barcode barcode = (Barcode) o;
        return item == barcode.item && Objects.equals(itemPrice, barcode.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, itemPrice);
    }
}
