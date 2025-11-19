package Module3.Strategy;

import Module3.DTO.Order;

public class ExpressDelivery implements DeliveryStrategy {
    public void deliver(Order order) {
        System.out.println(" Доставка заказа " + order.getProduct() + " экспресс-службой.");
    }
}