
import java.util.ArrayList;

/**
 * Represents an invoice containing customer information
 * and a collection of purchased items.
 */
public class Invoice {

    private String title;
    private String customerAddress;
    private ArrayList<LineItem> items;

    /**
     * Creates an invoice.
     *
     * @param title the invoice title
     * @param customerAddress the customer's address
     */
    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a line item to the invoice.
     *
     * @param item the line item to add
     */
    public void addItem(LineItem item) {
        items.add(item);
    }

    /**
     * Calculates the total amount due.
     *
     * @return the sum of all line item totals
     */
    public double calculateTotal() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public String getTitle() {
        return title;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }
}
