
/**
 * Represents one line item on an invoice.
 * Each line item contains a product and quantity.
 */
public class LineItem {

    private Product product;
    private int quantity;

    /**
     * Creates a line item.
     *
     * @param product the product being purchased
     * @param quantity the number of units purchased
     */
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Calculates the total price for this line item.
     *
     * @return quantity multiplied by unit price
     */
    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }
}
