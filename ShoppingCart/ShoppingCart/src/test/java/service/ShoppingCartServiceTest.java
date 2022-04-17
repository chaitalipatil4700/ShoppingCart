package service;

import exception.EmptyCartException;
import exception.ItemNotFoundInCartException;
import exception.NoSuchItemInInventoryException;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ShoppingCartServiceTest {

    ShoppingCartService shoppingCartService = new ShoppingCartService();


    @Test
    void testAdd1Item() throws NoSuchItemInInventoryException {
        shoppingCartService.addItem("01001");
        List<Product> products = shoppingCartService.getAllProductsFromCart();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(1, products.get(0).getQuantity());
        Assertions.assertEquals(Inventory.INVENTORY.get("01001"), products.get(0).getItem());
    }

    @Test
    void testAddMultipleItemsOfDifferentType() throws NoSuchItemInInventoryException {
        shoppingCartService.addItem("01001");
        shoppingCartService.addItem("02002");
        shoppingCartService.addItem("03003");
        List<Product> products = shoppingCartService.getAllProductsFromCart();

        Assertions.assertEquals(3, products.size());
        Assertions.assertEquals(1, products.get(0).getQuantity());
        Assertions.assertEquals(1, products.get(1).getQuantity());
        Assertions.assertEquals(1, products.get(2).getQuantity());
        Assertions.assertEquals(Inventory.INVENTORY.get("01001"), products.get(0).getItem());
        Assertions.assertEquals(Inventory.INVENTORY.get("02002"), products.get(1).getItem());
        Assertions.assertEquals(Inventory.INVENTORY.get("03003"), products.get(2).getItem());

    }

    @Test
    void testAddMultipleItemsOfSameType() throws NoSuchItemInInventoryException {
        shoppingCartService.addItem("01001");
        shoppingCartService.addItem("01001");
        List<Product> products = shoppingCartService.getAllProductsFromCart();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(2, products.get(0).getQuantity());
        Assertions.assertEquals(Inventory.INVENTORY.get("01001"), products.get(0).getItem());
    }

    @Test
    void testRemove1ItemFromNonEmptyCartWithDifferentItemTypes() throws NoSuchItemInInventoryException, EmptyCartException, ItemNotFoundInCartException {
        shoppingCartService.addItem("01001");
        shoppingCartService.addItem("02002");
        shoppingCartService.removeItem("02002");
        List<Product> products = shoppingCartService.getAllProductsFromCart();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(1, products.get(0).getQuantity());
        Assertions.assertEquals(Inventory.INVENTORY.get("01001"), products.get(0).getItem());

    }

    @Test
    void testRemove1ItemFromNonEmptyCartWithSameItemTypes() throws ItemNotFoundInCartException, NoSuchItemInInventoryException, EmptyCartException {
        shoppingCartService.addItem("01001");
        shoppingCartService.addItem("02002");
        shoppingCartService.addItem("03003");
        shoppingCartService.removeItem("01001");

        List<Product> products = shoppingCartService.getAllProductsFromCart();
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals(1, products.get(0).getQuantity());
        Assertions.assertEquals(1, products.get(1).getQuantity());

        Assertions.assertEquals(Inventory.INVENTORY.get("02002"), products.get(0).getItem());
        Assertions.assertEquals(Inventory.INVENTORY.get("03003"), products.get(1).getItem());

    }

    @Test
    void testRemove1ItemFromEmptyCart() throws EmptyCartException{
        Assertions.assertThrows(EmptyCartException.class, () -> shoppingCartService.removeItem("01001"));
    }

    @Test
    public void testInvalidAddItem(){
        Assertions.assertThrows(NoSuchItemInInventoryException.class, () -> shoppingCartService.addItem("05005"));
    }

    @Test
    public void testInvalidRemoveItem(){
        Assertions.assertThrows(NoSuchItemInInventoryException.class, () -> shoppingCartService.removeItem("05005"));
    }

    @Test
    void testRemoveValidItemNotPresentInCart() throws NoSuchItemInInventoryException {
        shoppingCartService.addItem("01001");
        Assertions.assertThrows(ItemNotFoundInCartException.class, () -> shoppingCartService.removeItem("02002"));
    }



}