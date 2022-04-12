package exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        System.out.println("Item Not Found");
    }
}
