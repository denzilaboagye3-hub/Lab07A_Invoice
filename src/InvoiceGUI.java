
import javax.swing.*;
import java.awt.*;

/**
 * GUI for entering invoice information
 * and displaying a completed invoice.
 */
public class InvoiceGUI extends JFrame {

    private JTextField titleField;
    private JTextField addressField;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;

    private JTextArea invoiceArea;
    private Invoice invoice;

    public InvoiceGUI() {
        setTitle("Invoice Generator");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        createInputPanel();
        createInvoiceArea();
        createButtonPanel();
    }

    private void createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        titleField = new JTextField("Invoice");
        addressField = new JTextField();
        productField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        inputPanel.add(new JLabel("Invoice Title:"));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Customer Address:"));
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productField);

        inputPanel.add(new JLabel("Unit Price:"));
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        add(inputPanel, BorderLayout.NORTH);
    }

    private void createInvoiceArea() {
        invoiceArea = new JTextArea();
        invoiceArea.setEditable(false);
        invoiceArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(invoiceArea), BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Item");
        JButton displayButton = new JButton("Display Invoice");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        addButton.addActionListener(e -> addItem());
        displayButton.addActionListener(e -> displayInvoice());
        clearButton.addActionListener(e -> clearForm());
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addItem() {
        try {
            String title = titleField.getText().trim();
            String address = addressField.getText().trim();
            String productName = productField.getText().trim();

            if (title.isEmpty() || address.isEmpty()
                    || productName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please complete all text fields.");
                return;
            }

            double price = Double.parseDouble(
                    priceField.getText().trim());

            int quantity = Integer.parseInt(
                    quantityField.getText().trim());

            if (price < 0 || quantity <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid price and quantity.");
                return;
            }

            if (invoice == null) {
                invoice = new Invoice(title, address);
            }

            Product product = new Product(productName, price);
            LineItem item = new LineItem(product, quantity);

            invoice.addItem(item);

            JOptionPane.showMessageDialog(this,
                    "Item added successfully.");

            productField.setText("");
            priceField.setText("");
            quantityField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Price must be a number and quantity must be a whole number.");
        }
    }

    /**
     * Displays the invoice and calculates the amount due.
     */
    private void displayInvoice() {
        if (invoice == null || invoice.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please add an item first.");
            return;
        }

        StringBuilder receipt = new StringBuilder();

        receipt.append(invoice.getTitle()).append("\n");
        receipt.append(invoice.getCustomerAddress()).append("\n");
        receipt.append("---------------------------------------------\n");

        receipt.append(String.format(
                "%-16s %5s %9s %10s%n",
                "Product", "Qty", "Price", "Total"));

        receipt.append("---------------------------------------------\n");

        for (LineItem item : invoice.getItems()) {
            receipt.append(String.format(
                    "%-16.16s %5d %9.2f %10.2f%n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getUnitPrice(),
                    item.getTotal()));
        }

        receipt.append("---------------------------------------------\n");
        receipt.append(String.format(
                "Total Amount Due: $%.2f%n",
                invoice.calculateTotal()));

        invoiceArea.setText(receipt.toString());
    }

    private void clearForm() {
        invoice = null;

        titleField.setText("Invoice");
        addressField.setText("");
        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        invoiceArea.setText("");
    }
}
