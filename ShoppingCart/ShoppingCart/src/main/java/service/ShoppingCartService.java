package service;

import exception.EmptyCartException;
import exception.ItemNotFoundInCartException;
import exception.NoSuchItemInInventoryException;
import model.Inventory;
import model.Item;
import model.Product;

import java.util.*;

public class ShoppingCartService {

    private final Map<String, Product> productMap = new HashMap<>();


    public void addItem(String barcode) throws NoSuchItemInInventoryException {

        if (Inventory.isItemPresentInInventory(barcode)) {
            Item item = Inventory.getItemFromBarcode(barcode);
            if (productMap.containsKey(barcode)) {
                productMap.get(barcode).incrementQuantity();

            } else {
                productMap.put(barcode, new Product(item));
            }
        } else {
            throw new NoSuchItemInInventoryException();
        }
        System.out.println("Item has been added successfully.");

    }

    public void removeItem(String barcode) throws EmptyCartException, NoSuchItemInInventoryException, ItemNotFoundInCartException {
        if (Inventory.isItemPresentInInventory(barcode)) {
            if (!productMap.isEmpty()) {
                if (productMap.containsKey(barcode)) {
                    Integer quantity = productMap.get(barcode).getQuantity();
                    if (quantity > 1)
                        productMap.get(barcode).decrementQuantity();
                    else
                        productMap.remove(barcode);
                } else {
                    throw new ItemNotFoundInCartException();
                }
            } else {
                throw new EmptyCartException();
            }
        } else {
            throw new NoSuchItemInInventoryException();
        }
        System.out.println("Item has been added successfully.");

    }

    public List<Product> getAllProductsFromCart() {
        return Collections.unmodifiableList(new ArrayList<>(productMap.values()));

    }
}