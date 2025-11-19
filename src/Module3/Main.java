package Module3;

import Module3.Adapter.PaymentAdapter;
import Module3.Adapter.PaymentProcessor;
import Module3.ChainofResponsibility.DiscountHandler;
import Module3.ChainofResponsibility.HolidayDiscount;
import Module3.ChainofResponsibility.LoyaltyDiscount;
import Module3.DTO.Order;
import Module3.Decorator.BasicOrderService;
import Module3.Decorator.GiftWrapDecorator;
import Module3.Decorator.OrderService;
import Module3.Proxy.ShippingProxy;
import Module3.Proxy.ShippingService;
import Module3.Strategy.DeliveryStrategy;
import Module3.Strategy.ExpressDelivery;

public class Main {
    public static void main(String[] args)
    {
        // 1. Сборка заказа с помощью Builder
        Order order = new Order.Builder()
                .setCustomerName("VIP")
                .setProduct("Наушники")
                .setPrice(120.0)
                .wrapAsGift()
                .build();

        System.out.println("Создан заказ: " + order);

        // 2. Применение скидок через Chain of Responsibility
        DiscountHandler loyalty = new LoyaltyDiscount();
        DiscountHandler holiday = new HolidayDiscount();
        loyalty.setNext(holiday);
        loyalty.applyDiscount(order);

        // 3. Оплата через Adapter
        PaymentProcessor payment = new PaymentAdapter();
        payment.processPayment(order.getPrice());

        // 4. Обработка заказа с Decorator
        OrderService service = new GiftWrapDecorator(new BasicOrderService());
        service.process(order);

        // 5. Выбор доставки (Strategy)
        DeliveryStrategy delivery = new ExpressDelivery();
        delivery.deliver(order);

        // 6. Отправка через Proxy
        ShippingService shipping = new ShippingProxy(true); // авторизован
        shipping.ship(order);
    }
}
