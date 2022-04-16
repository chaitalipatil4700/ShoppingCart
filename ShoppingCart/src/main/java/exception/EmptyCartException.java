package exception;

public class EmptyCartException extends Exception {
    public EmptyCartException(){
        System.out.println("Cart is empty!!!");
    }
}
