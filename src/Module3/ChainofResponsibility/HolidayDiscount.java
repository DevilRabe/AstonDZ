package Module3.ChainofResponsibility;

import Module3.DTO.Order;

public class HolidayDiscount extends DiscountHandler {
    public void applyDiscount(Order order) {
        if (order.getPrice() > 100) {
            double newPrice = order.getPrice() - 10;
            System.out.println("Применена праздничная скидка. Новая цена: " + newPrice);
            order.setPrice(newPrice);
        } else if (next != null) {
            next.applyDiscount(order);
        }
    }
}