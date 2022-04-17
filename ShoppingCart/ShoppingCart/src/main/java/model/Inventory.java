package model;

import java.util.Map;

public final class Inventory {

    public static final Map<String, Item> INVENTORY= Map.of("01001",new Item("APPLE",1.1),
                                                            "02002",new Item("BANANA",1.3),
                                                            "03003",new Item("CARROT",0.9));

    public static Boolean isItemPresentInInventory(String barcode) {
        return Inventory.INVENTORY.containsKey(barcode);
    }

    public static Item getItemFromBarcode(String barcode) {
        return Inventory.INVENTORY.get(barcode);
    }
}
