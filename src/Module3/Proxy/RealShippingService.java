package Module3.Proxy;

import Module3.DTO.Order;

public class RealShippingService implements ShippingService {
    @Override
    public void ship(Order order) {
        System.out.println("✈️ Заказ " + order.getProduct() + " отправлен.");
    }
}