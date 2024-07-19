import java.awt.*;
import java.awt.event.*;

public class PaymentGatewayPage extends Frame implements ActionListener {
    TextField cardNumberField, expiryDateField, cvvField;
    Button payButton, backButton; // Add backButton

    PaymentGatewayPage() {
        setBackground(new Color(173, 216, 230)); // Set background color to sky blue
        setTitle("Payment Gateway");
        setSize(600, 400); // Set size similar to other pages
        setLayout(new BorderLayout()); // Use BorderLayout for main panel
        setResizable(false);
        setLocationRelativeTo(null);

        // Components
        Font labelFont = new Font("Arial", Font.BOLD, 24); // Label font
        Font textFont = new Font("Arial", Font.PLAIN, 18); // Text field font
        Label enterPaymentLabel = new Label("Enter Payment Details");
        enterPaymentLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Larger font for title
        enterPaymentLabel.setAlignment(Label.CENTER); // Center align the label
        Label cardNumberLabel = new Label("Card Number:");
        cardNumberLabel.setFont(labelFont);
        Label expiryDateLabel = new Label("Expiry Date:");
        expiryDateLabel.setFont(labelFont);
        Label cvvLabel = new Label("CVV:");
        cvvLabel.setFont(labelFont);
        cardNumberField = new TextField(20); // Reduced width of text fields
        cardNumberField.setFont(textFont);
        expiryDateField = new TextField(20);
        expiryDateField.setFont(textFont);
        cvvField = new TextField(20);
        cvvField.setFont(textFont);
        payButton = new Button("Pay");
        payButton.addActionListener(this);
        payButton.setFont(new Font("Arial", Font.BOLD, 18)); // Medium font for button
        payButton.setPreferredSize(new Dimension(300, 50)); // Medium size for button
        payButton.setBackground(Color.blue);
        payButton.setForeground(Color.white);

        // Back Button
        backButton = new Button("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.BOLD, 18)); // Medium font for button
        backButton.setPreferredSize(new Dimension(100, 50)); // Medium size for button

        // Panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Adjusted gap between components
        panel.setBackground(new Color(173, 216, 230)); // Sky blue background
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(expiryDateLabel);
        panel.add(expiryDateField);
        panel.add(cvvLabel);
        panel.add(cvvField);

        // Add Components
        add(enterPaymentLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        Panel buttonPanel = new Panel();
        buttonPanel.add(backButton); // Add backButton to the button panel
        buttonPanel.add(payButton);
        buttonPanel.setBackground(new Color(173, 216, 230)); // Sky blue background
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            // Process payment (not implemented in this example)
            boolean paymentSuccessful = processPayment(); // Placeholder for payment processing
            if (paymentSuccessful) {
               new PaymentStatusPage("Payment Successful");
           } else {
                new PaymentStatusPage("Payment Failed");
            
            }
            dispose(); // Close payment gateway page
        } else if (e.getSource() == backButton) {
            // Navigate back to login page
            new ETicketingProject();
            dispose(); // Close payment gateway page
        }
    }

    // Placeholder for payment processing
    private boolean processPayment() {
        // Simulate payment processing
        // Return true for successful payment, false otherwise
        return Math.random() < 0.8; // 80% chance of success
    }

    public static void main(String[] args) {
        new PaymentGatewayPage();
    }
}