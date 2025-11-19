package Module3.Decorator;

import Module3.DTO.Order;

public class GiftWrapDecorator implements OrderService {
    private OrderService wrappedService;

    public GiftWrapDecorator(OrderService service) {
        this.wrappedService = service;
    }

    @Override
    public void process(Order order) {
        wrappedService.process(order);
        if (order.isGiftWrapped()) {
            System.out.println("Добавлена подарочная упаковка!");
        }
    }
}
