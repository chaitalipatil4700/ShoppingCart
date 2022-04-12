package service;

import exception.ItemNotFoundException;
import model.Bill;
import model.Item;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService {

    private Bill bill;

    public ShoppingCartService(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void addItem(Item item) {
        try {
            if (isItemPresentInCart(item)) {
                Integer quantity = getExistingQuantity(item);
                setItemQuantity(item, quantity + 1);
            } else {
                List<Product> products = new ArrayList<>(bill.getProducts());
                products.add(new Product(item, 1));
                bill.setProducts(products);
            }
            System.out.println("Item has been added successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding item to the cart!!!");
        }
    }

    public void removeItem(Item item) throws ItemNotFoundException {
        if (isItemPresentInCart(item)) {
            Integer quantity = getExistingQuantity(item);
            if (quantity > 1) {
                setItemQuantity(item, quantity - 1);
            } else {
                List<Product> products = new ArrayList<>(bill.getProducts());
                Product product = getProductOfMentionedItem(item);
                products.remove(product);
                bill.setProducts(products);
            }
            System.out.println("Item has been deleted successfully.");

        } else {
            throw new ItemNotFoundException();
        }

    }
//    public void printBill(){
//        Double totalPrice = bill.getTotalPrice();
//        System.out.println("-----------Bill-----------");
//        for(Product product:bill.getProducts()){
//            System.out.println(product.getQuantity()+" x "+product.getItem()+"@"+product.getItem().getItemPrice()+" = "
//                    +(product.getQuantity() * product.getItem().getItemPrice()));
//        }
//        System.out.println("Total bill is : "+totalPrice);
//    }

    private Boolean isItemPresentInCart(Item item) {
        for (Product product : bill.getProducts()) {
            if (product.getItem().getItemType().equals(item.getItemType())
                    && product.getItem().getItemPrice().equals(item.getItemPrice())
                    && product.getItem().getBarcode().equals(item.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    private Integer getExistingQuantity(Item item) {
        for (Product product : bill.getProducts()) {
            if (product.getItem().getItemType().equals(item.getItemType())
                    && product.getItem().getItemPrice().equals(item.getItemPrice())
                    && product.getItem().getBarcode().equals(item.getBarcode())) {
                return product.getQuantity();
            }
        }
        return -1;
    }

    private void setItemQuantity(Item item, Integer newQuantity) {

        for (Product product : bill.getProducts()) {
            if (product.getItem().getItemType().equals(item.getItemType())
                    && product.getItem().getItemPrice().equals(item.getItemPrice())
                    && product.getItem().getBarcode().equals(item.getBarcode())) {

                product.setQuantity(newQuantity);
            }
        }
    }

    private Product getProductOfMentionedItem(Item item) {
        for (Product product : bill.getProducts()) {
            if (product.getItem().getItemType().equals(item.getItemType())
                    && product.getItem().getItemPrice().equals(item.getItemPrice())
                    && product.getItem().getBarcode().equals(item.getBarcode())) {
                return product;
            }
        }
        return null;
    }

}
