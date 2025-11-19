package Module3.DTO;

public class Order {
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

    public static class Builder {
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
