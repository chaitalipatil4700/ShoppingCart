package exception;

public class ItemNotFoundInCartException extends Exception {
    public ItemNotFoundInCartException() {
        System.out.println("Item Not Found");
    }
}
