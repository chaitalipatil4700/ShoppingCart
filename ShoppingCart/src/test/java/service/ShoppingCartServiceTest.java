package service;

import exception.ItemNotFoundException;
import model.Bill;
import model.Item;
import model.ItemType;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ShoppingCartServiceTest {

    Bill bill = new Bill(new ArrayList<Product>());

    ShoppingCartService shoppingCartService = new ShoppingCartService(bill);


    @Test
    void testAdd1Item() {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        shoppingCartService.addItem(item1);

        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().size());
        Assertions.assertEquals(ItemType.APPLE, shoppingCartService.getBill().getProducts().get(0).getItem().getItemType());
        Assertions.assertEquals("01001", shoppingCartService.getBill().getProducts().get(0).getItem().getBarcode());
        Assertions.assertEquals(1.1, shoppingCartService.getBill().getProducts().get(0).getItem().getItemPrice());
        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().get(0).getQuantity());
    }

    @Test
    void testAddMultipleItemsOfDifferentType() {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        Item item2 = new Item();
        item2.setItemType(ItemType.BANANA);
        item2.setBarcode("02002");
        item2.setItemPrice(1.3);

        Item item3 = new Item();
        item3.setItemType(ItemType.CARROT);
        item3.setBarcode("03003");
        item3.setItemPrice(0.9);

        shoppingCartService.addItem(item1);
        shoppingCartService.addItem(item2);
        shoppingCartService.addItem(item3);

        Assertions.assertEquals(3, shoppingCartService.getBill().getProducts().size());

        Assertions.assertEquals(ItemType.APPLE, shoppingCartService.getBill().getProducts().get(0).getItem().getItemType());
        Assertions.assertEquals("01001", shoppingCartService.getBill().getProducts().get(0).getItem().getBarcode());
        Assertions.assertEquals(1.1, shoppingCartService.getBill().getProducts().get(0).getItem().getItemPrice());
        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().get(0).getQuantity());

        Assertions.assertEquals(ItemType.BANANA, shoppingCartService.getBill().getProducts().get(1).getItem().getItemType());
        Assertions.assertEquals("02002", shoppingCartService.getBill().getProducts().get(1).getItem().getBarcode());
        Assertions.assertEquals(1.3, shoppingCartService.getBill().getProducts().get(1).getItem().getItemPrice());
        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().get(1).getQuantity());

        Assertions.assertEquals(ItemType.CARROT, shoppingCartService.getBill().getProducts().get(2).getItem().getItemType());
        Assertions.assertEquals("03003", shoppingCartService.getBill().getProducts().get(2).getItem().getBarcode());
        Assertions.assertEquals(0.9, shoppingCartService.getBill().getProducts().get(2).getItem().getItemPrice());
        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().get(2).getQuantity());


    }

    @Test
    void testAddMultipleItemsOfSameType() {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        Item item2 = new Item();
        item2.setItemType(ItemType.APPLE);
        item2.setBarcode("01001");
        item2.setItemPrice(1.1);

        shoppingCartService.addItem(item1);
        shoppingCartService.addItem(item2);

        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().size());

        Assertions.assertEquals(ItemType.APPLE, shoppingCartService.getBill().getProducts().get(0).getItem().getItemType());
        Assertions.assertEquals("01001", shoppingCartService.getBill().getProducts().get(0).getItem().getBarcode());
        Assertions.assertEquals(1.1, shoppingCartService.getBill().getProducts().get(0).getItem().getItemPrice());
        Assertions.assertEquals(2, shoppingCartService.getBill().getProducts().get(0).getQuantity());
    }

    @Test
    void testRemove1ItemFromNonEmptyCartWithDifferentItemTypes() throws ItemNotFoundException {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        Item item2 = new Item();
        item2.setItemType(ItemType.BANANA);
        item2.setBarcode("02002");
        item2.setItemPrice(1.3);

        shoppingCartService.addItem(item1);
        shoppingCartService.addItem(item2);
        shoppingCartService.removeItem(item2);

        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().size());

        Assertions.assertEquals(ItemType.APPLE, shoppingCartService.getBill().getProducts().get(0).getItem().getItemType());
        Assertions.assertEquals("01001", shoppingCartService.getBill().getProducts().get(0).getItem().getBarcode());
        Assertions.assertEquals(1.1, shoppingCartService.getBill().getProducts().get(0).getItem().getItemPrice());
        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().get(0).getQuantity());

    }

    @Test
    void testRemove1ItemFromNonEmptyCartWithSameItemTypes() throws ItemNotFoundException {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        Item item2 = new Item();
        item2.setItemType(ItemType.APPLE);
        item2.setBarcode("01001");
        item2.setItemPrice(1.1);

        Item item3 = new Item();
        item3.setItemType(ItemType.APPLE);
        item3.setBarcode("01001");
        item3.setItemPrice(1.1);

        shoppingCartService.addItem(item1);
        shoppingCartService.addItem(item2);
        shoppingCartService.addItem(item3);
        shoppingCartService.removeItem(item1);

        Assertions.assertEquals(1, shoppingCartService.getBill().getProducts().size());

        Assertions.assertEquals(ItemType.APPLE, shoppingCartService.getBill().getProducts().get(0).getItem().getItemType());
        Assertions.assertEquals("01001", shoppingCartService.getBill().getProducts().get(0).getItem().getBarcode());
        Assertions.assertEquals(1.1, shoppingCartService.getBill().getProducts().get(0).getItem().getItemPrice());
        Assertions.assertEquals(2, shoppingCartService.getBill().getProducts().get(0).getQuantity());

    }

    @Test
    void testRemove1ItemFromEmptyCart() throws ItemNotFoundException {
        Item item1 = new Item();
        item1.setItemType(ItemType.APPLE);
        item1.setBarcode("01001");
        item1.setItemPrice(1.1);

        Assertions.assertThrows(ItemNotFoundException.class, () -> shoppingCartService.removeItem(item1));
    }



}