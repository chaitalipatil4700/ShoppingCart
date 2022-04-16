package model;

public final class Item {
    private String itemDescription;
    private Double itemPrice;

    public Item(String itemDescription, Double itemPrice) {
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemDescription.equals(item.itemDescription) && itemPrice.equals(item.itemPrice);
    }

}
