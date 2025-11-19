package Module3.ChainofResponsibility;

import Module3.DTO.Order;

public class LoyaltyDiscount extends DiscountHandler {
    public void applyDiscount(Order order) {
        if (order.getCustomerName().equals("VIP")) {
            double newPrice = order.getPrice() * 0.8;
            System.out.println("Применена скидка постоянного клиента. Новая цена: " + newPrice);
            order.setPrice(newPrice);
        } else if (next != null) {
            next.applyDiscount(order);
        }
    }
}
