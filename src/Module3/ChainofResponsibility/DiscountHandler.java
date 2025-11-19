package Module3.ChainofResponsibility;

import Module3.DTO.Order;

public abstract class DiscountHandler {
    protected DiscountHandler next;

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    public abstract void applyDiscount(Order order);
}
