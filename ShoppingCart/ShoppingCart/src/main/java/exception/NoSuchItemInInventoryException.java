package exception;

public class NoSuchItemInInventoryException extends Exception {

    public NoSuchItemInInventoryException(){
        System.out.println("Item Not Found");
    }
}
