package Module3.Decorator;

import Module3.DTO.Order;

public class BasicOrderService implements OrderService {
    @Override
    public void process(Order order) {
        System.out.println("Обработка базового заказа: " + order.getProduct());
    }
}
