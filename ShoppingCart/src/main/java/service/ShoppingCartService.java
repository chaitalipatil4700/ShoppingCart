package service;

import exception.EmptyCartException;
import exception.ItemNotFoundInCartException;
import exception.NoSuchItemInInventoryException;
import model.Inventory;
import model.Item;
import model.Product;

import java.util.Collections;
import java.util.List;

public class ShoppingCartService {

    private List<Product> products;

    ShoppingCartService(List<Product> products){
        this.products = products;
    }

    public void addItem(String barcode) throws NoSuchItemInInventoryException {

            if (isItemPresentInInventory(barcode)) {
                Item item = getItemFromInventory(barcode);
                if (isItemPresentInCart(barcode)) {
                    incrementQuantityOfProduct(item);
                } else {
                    products.add(new Product(item));
                }
            } else {
                throw new NoSuchItemInInventoryException();
            }
            System.out.println("Item has been added successfully.");

    }

    public void removeItem(String barcode) throws EmptyCartException, NoSuchItemInInventoryException, ItemNotFoundInCartException {
            if (isItemPresentInInventory(barcode)) {
                if(!isCartEmpty()) {
                    Item item = getItemFromInventory(barcode);
                    if (isItemPresentInCart(barcode)) {
                        if(getQuantity(item)>1)
                            decrementQuantityOfProduct(item);
                        else
                            products.remove(new Product(item));
                    } else {
                       throw new ItemNotFoundInCartException();
                    }
                }else{
                    throw new EmptyCartException();
                }
            } else {
                throw new NoSuchItemInInventoryException();
            }
            System.out.println("Item has been added successfully.");

    }

    private boolean isCartEmpty() {
        return products.isEmpty();
    };

    public List<Product> getAllProductsFromCart(){
        return Collections.unmodifiableList(products);
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


    private Boolean isItemPresentInCart(String barcode) throws NoSuchItemInInventoryException {
        Item item = Inventory.INVENTORY.get(barcode);
        for (Product product : products) {
            if (item.equals(product.getItem())) {
                return true;
            }
        }
        return false;
    }

    private Item getItemFromInventory(String barcode) {
        return Inventory.INVENTORY.get(barcode);
    }

    private Boolean isItemPresentInInventory(String barcode) {
        return Inventory.INVENTORY.containsKey(barcode);
    }

    private void incrementQuantityOfProduct(Item item) {
        for (Product product : products) {
            if (item.equals(product.getItem())) {
                product.incrementQuantity();
            }
        }
    }

    private void decrementQuantityOfProduct(Item item) {
        for (Product product : products) {
            if (item.equals(product.getItem())) {
                product.decrementQuantity();
            }
        }
    }

    private Integer getQuantity(Item item){
        for (Product product : products) {
            if (item.equals(product.getItem())) {
                return product.getQuantity();
            }
        }
        return 0;
    }


}
