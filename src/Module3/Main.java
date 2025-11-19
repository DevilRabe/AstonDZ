package Module3;

public class Main {
    public static void main(String[] args)
    {
// ---------- 1. Builder ----------
        class Order {
            private String customerName;
            private String product;
            private double price;
            private boolean isGiftWrapped;

            private Order(Builder builder) {
                this.customerName = builder.customerName;
                this.product = builder.product;
                this.price = builder.price;
                this.isGiftWrapped = builder.isGiftWrapped;
            }

            // getters
            public String getCustomerName() { return customerName; }
            public String getProduct() { return product; }
            public double getPrice() { return price; }
            public boolean isGiftWrapped() { return isGiftWrapped; }
            public void setPrice(double price) { this.price = price; }

            static class Builder {
                private String customerName;
                private String product;
                private double price;
                private boolean isGiftWrapped = false;

                public Builder setCustomerName(String name) { this.customerName = name; return this; }
                public Builder setProduct(String product) { this.product = product; return this; }
                public Builder setPrice(double price) { this.price = price; return this; }
                public Builder wrapAsGift() { this.isGiftWrapped = true; return this; }

                public Order build() {
                    return new Order(this);
                }
            }

            @Override
            public String toString() {
                return String.format("Order{customer='%s', product='%s', price=%.2f, giftWrapped=%s}",
                        customerName, product, price, isGiftWrapped);
            }
        }

// ---------- 2. Strategy (Delivery Strategy) ----------
        interface DeliveryStrategy {
            void deliver(Order order);
        }

        class ExpressDelivery implements DeliveryStrategy {
            public void deliver(Order order) {
                System.out.println("🚚 Доставка заказа " + order.getProduct() + " экспресс-службой.");
            }
        }

        class PostDelivery implements DeliveryStrategy {
            public void deliver(Order order) {
                System.out.println("📬 Доставка заказа " + order.getProduct() + " почтой.");
            }
        }

// ---------- 3. Chain of Responsibility (Discounts) ----------
        abstract class DiscountHandler {
            protected DiscountHandler next;

            public void setNext(DiscountHandler next) {
                this.next = next;
            }

            public abstract void applyDiscount(Order order);
        }

        class LoyaltyDiscount extends DiscountHandler {
            public void applyDiscount(Order order) {
                if (order.getCustomerName().equals("VIP")) {
                    double newPrice = order.getPrice() * 0.9; // 10% скидка
                    System.out.println("🎁 Применена скидка постоянного клиента. Новая цена: " + newPrice);
                    order.setPrice(newPrice);
                } else if (next != null) {
                    next.applyDiscount(order);
                }
            }
        }

        class HolidayDiscount extends DiscountHandler {
            public void applyDiscount(Order order) {
                if (order.getPrice() > 100) {
                    double newPrice = order.getPrice() - 10; // фиксированная скидка
                    System.out.println("🎄 Применена праздничная скидка. Новая цена: " + newPrice);
                    order.setPrice(newPrice);
                } else if (next != null) {
                    next.applyDiscount(order);
                }
            }
        }

// ---------- 4. Adapter (Payment System) ----------
// Представим, что у нас есть сторонняя система
        class ExternalPaymentSystem {
            public void pay(double amount) {
                System.out.println("💳 Оплата " + amount + " обработана через внешнюю систему.");
            }
        }

// Адаптер под наш интерфейс
        interface PaymentProcessor {
            void processPayment(double amount);
        }

        class PaymentAdapter implements PaymentProcessor {
            private ExternalPaymentSystem externalSystem = new ExternalPaymentSystem();

            @Override
            public void processPayment(double amount) {
                externalSystem.pay(amount);
            }
        }

// ---------- 5. Decorator (Gift Wrapping) ----------
        interface OrderService {
            void process(Order order);
        }

        class BasicOrderService implements OrderService {
            @Override
            public void process(Order order) {
                System.out.println("📦 Обработка базового заказа: " + order.getProduct());
            }
        }

        class GiftWrapDecorator implements OrderService {
            private OrderService wrappedService;

            public GiftWrapDecorator(OrderService service) {
                this.wrappedService = service;
            }

            @Override
            public void process(Order order) {
                wrappedService.process(order);
                if (order.isGiftWrapped()) {
                    System.out.println("🎀 Добавлена подарочная упаковка!");
                }
            }
        }

// ---------- 6. Proxy (Access Control for Shipping) ----------
        interface ShippingService {
            void ship(Order order);
        }

        class RealShippingService implements ShippingService {
            @Override
            public void ship(Order order) {
                System.out.println("✈️ Заказ " + order.getProduct() + " отправлен.");
            }
        }

        class ShippingProxy implements ShippingService {
            private RealShippingService realService = new RealShippingService();
            private boolean isAuthenticated;

            public ShippingProxy(boolean isAuthenticated) {
                this.isAuthenticated = isAuthenticated;
            }

            @Override
            public void ship(Order order) {
                if (isAuthenticated) {
                    realService.ship(order);
                } else {
                    System.out.println("❌ Доступ запрещён: пользователь не аутентифицирован.");
                }
            }
        }

// ---------- Main Application ----------
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
