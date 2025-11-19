package Module3.Proxy;

import Module3.DTO.Order;

public class ShippingProxy implements ShippingService {
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
            System.out.println("Доступ запрещён: пользователь не аутентифицирован.");
        }
    }
}