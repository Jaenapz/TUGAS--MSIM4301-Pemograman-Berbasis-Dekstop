package Entity;

import java.util.List;

public class ReceiptDetail {

    private final List<String> orderItem;
    private final double tax;
    private final double serviceCharge;
    private final double discount;
    private final double finalCharge;

    public ReceiptDetail(List<String> orderItem, double tax, double serviceCharge, double discount, double finalCharge) {
        this.orderItem = orderItem;
        this.tax = tax;
        this.serviceCharge = serviceCharge;
        this.discount = discount;
        this.finalCharge = finalCharge;
    }

    public double getTax() {
        return tax;
    }

    public List<String> getOrderItem() {
        return orderItem;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public double getDiscount() {
        return discount;
    }
}
