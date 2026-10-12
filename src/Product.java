
/**
 * Represents a product on an invoice.
 * Stores the product name and unit price.
 */
public class Product {

    private String name;
    private double unitPrice;

    /**
     * Creates a product.
     *
     * @param name the product name
     * @param unitPrice the price per unit
     */
    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
