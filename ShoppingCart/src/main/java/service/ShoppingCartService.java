package service;

import exception.ItemNotFoundException;
import model.Barcode;
import model.Bill;

import java.util.List;
import java.util.Map;

public class ShoppingCartService {

    Bill bill;

    public ShoppingCartService(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void addItem(Barcode barcode) {
        try {
            if (bill.getProducts().containsKey(barcode)) {
                bill.getProducts().put(barcode, bill.getProducts().get(barcode) + 1);
            } else {
                bill.getProducts().put(barcode, 1);
            }
            System.out.println("Item has been added successfully.");
        }
        catch (Exception e){
            System.out.println("Error while adding item to the cart!!!");
        }
    }

    public void removeItem(Barcode barcode) throws ItemNotFoundException {
        if(bill.getProducts().containsKey(barcode)){
            Integer quantity = bill.getProducts().get(barcode);
            if(quantity>1){
                bill.getProducts().put(barcode,bill.getProducts().get(barcode)-1);
            }else{
                bill.getProducts().remove(barcode);
            }
            System.out.println("Item has been deleted successfully.");

        }else{
           throw new ItemNotFoundException();
        }
    }
    public void printBill(){
        Double totalPrice = bill.getTotalPrice();
        System.out.println("-----------Bill-----------");
        for(Map.Entry<Barcode, Integer> entry:bill.getProducts().entrySet()){
            System.out.println(entry.getValue()+" x "+entry.getKey().getItem()+"@"+entry.getKey().getItemPrice()+" = "
                    +(entry.getValue()*entry.getKey().getItemPrice()));
        }
        System.out.println("Total bill is : "+totalPrice);
    }

}
