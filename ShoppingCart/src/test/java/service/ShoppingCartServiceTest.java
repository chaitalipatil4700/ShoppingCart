package service;

import exception.ItemNotFoundException;
import model.Barcode;
import model.Bill;
import model.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
class ShoppingCartServiceTest {

    Bill bill = new Bill(new HashMap<Barcode,Integer>(),0.0);

    ShoppingCartService shoppingCartService = new ShoppingCartService(bill);

    @Test
    void testAdd3ItemsRemove1Item() throws ItemNotFoundException {
        Barcode barcode1 =new Barcode();
        barcode1.setItem(Item.APPLE);
        barcode1.setItemNumber("01001");
        barcode1.setItemPrice(1.1);

        Barcode barcode2 =new Barcode();
        barcode2.setItem(Item.BANANA);
        barcode2.setItemNumber("02002");
        barcode2.setItemPrice(1.3);

        Barcode barcode3 =new Barcode();
        barcode3.setItem(Item.CARROT);
        barcode3.setItemNumber("03003");
        barcode3.setItemPrice(0.9);

        Integer beforeAppleQuantity = shoppingCartService.getBill().getProducts().get(barcode1);
        Integer beforeBananaQuantity = shoppingCartService.getBill().getProducts().get(barcode2);
        Integer beforeCarrotQuantity = shoppingCartService.getBill().getProducts().get(barcode3);

        shoppingCartService.addItem(barcode1);
        shoppingCartService.addItem(barcode2);
        shoppingCartService.addItem(barcode3);
        shoppingCartService.removeItem(barcode2);


        Integer afterAppleQuantity = shoppingCartService.getBill().getProducts().get(barcode1);
        Integer afterBananaQuantity = shoppingCartService.getBill().getProducts().get(barcode2);
        Integer afterCarrotQuantity = shoppingCartService.getBill().getProducts().get(barcode3);

        Assert.assertNotEquals(beforeAppleQuantity,afterAppleQuantity);
        Assert.assertEquals(beforeBananaQuantity,afterBananaQuantity);
        Assert.assertNotEquals(beforeCarrotQuantity,afterCarrotQuantity);


    }

    @Test
    void testRemove1ItemFromEmptyCart() throws ItemNotFoundException {
        Barcode barcode2 =new Barcode();
        barcode2.setItem(Item.BANANA);
        barcode2.setItemNumber("02002");
        barcode2.setItemPrice(1.3);

        Assert.assertThrows(ItemNotFoundException.class,() -> shoppingCartService.removeItem(barcode2));

    }

    @Test
    void testPrintBillWithItems() throws ItemNotFoundException {
        Barcode barcode1 =new Barcode();
        barcode1.setItem(Item.APPLE);
        barcode1.setItemNumber("01001");
        barcode1.setItemPrice(1.1);

        Barcode barcode11 =new Barcode();
        barcode11.setItem(Item.APPLE);
        barcode11.setItemNumber("01002");
        barcode11.setItemPrice(1.1);

        Barcode barcode2 =new Barcode();
        barcode2.setItem(Item.BANANA);
        barcode2.setItemNumber("02002");
        barcode2.setItemPrice(1.3);

        Barcode barcode3 =new Barcode();
        barcode3.setItem(Item.CARROT);
        barcode3.setItemNumber("03003");
        barcode3.setItemPrice(0.9);

        Barcode barcode33 =new Barcode();
        barcode33.setItem(Item.CARROT);
        barcode33.setItemNumber("03004");
        barcode33.setItemPrice(0.9);

        shoppingCartService.addItem(barcode1);
        shoppingCartService.addItem(barcode11);
        shoppingCartService.addItem(barcode2);
        shoppingCartService.addItem(barcode3);
        shoppingCartService.addItem(barcode33);

        shoppingCartService.printBill();
        Assert.assertEquals(new Double(5.3),Math.round(shoppingCartService.getBill().getTotalPrice()),1);

    }

    @Test
    void testPrintBillWithNoItems() throws ItemNotFoundException {

        shoppingCartService.printBill();
        Assert.assertEquals(new Double(0.0),Math.round(shoppingCartService.getBill().getTotalPrice()),1);

    }




}