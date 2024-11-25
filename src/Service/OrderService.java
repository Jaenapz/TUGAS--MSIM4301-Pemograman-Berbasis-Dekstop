package Service;

import Entity.OrderInput;
import Entity.ReceiptDetail;

public interface OrderService {

    void displayMenu();

    void addNewMenu();

    void updateMenuPrice();

    void removeMenu();

    double calculateTotalOrder(String[] orderitem, int[] quantity);

    ReceiptDetail totalReceipt(String[] orderItem, int[] quantity, double totalCost);

}
