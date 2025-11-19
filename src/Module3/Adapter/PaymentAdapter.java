package Module3.Adapter;

public class PaymentAdapter implements PaymentProcessor {
    private ExternalPaymentSystem externalSystem = new ExternalPaymentSystem();

    @Override
    public void processPayment(double amount) {
        externalSystem.pay(amount);
    }
}