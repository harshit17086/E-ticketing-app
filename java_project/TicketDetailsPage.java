import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TicketDetailsPage extends Frame implements ActionListener {
    TextField destinationField, dateField, numberOfTicketsField;
    Button proceedButton, backButton; // Add backButton

    Connection connection;

    TicketDetailsPage() {
        setBackground(new Color(173, 216, 230)); // Set background color to sky blue
        setTitle("Ticket Details");
        setSize(600, 400); // Set size similar to the login page
        setLayout(new BorderLayout()); // Use BorderLayout for main panel
        setResizable(false);
        setLocationRelativeTo(null);

        // Components
        Font labelFont = new Font("Arial", Font.BOLD, 24); // Label font - increased size
        Font textFont = new Font("Arial", Font.PLAIN, 18); // Text field font - increased size
        Label enterTicketLabel = new Label("Enter Ticket Details");
        enterTicketLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Larger font for title
        enterTicketLabel.setAlignment(Label.CENTER); // Center align the label
        Label destinationLabel = new Label("Destination:");
        destinationLabel.setFont(labelFont);
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(labelFont);
        Label numberOfTicketsLabel = new Label("Number of Tickets:");
        numberOfTicketsLabel.setFont(labelFont);
        destinationField = new TextField(20); // Reduced width of text fields
        destinationField.setFont(textFont);
        dateField = new TextField(20);
        dateField.setFont(textFont);
        numberOfTicketsField = new TextField(20);
        numberOfTicketsField.setFont(textFont);
        proceedButton = new Button("Proceed to Payment");
        proceedButton.addActionListener(this);
        proceedButton.setFont(new Font("Arial", Font.BOLD, 18)); // Medium font for button
        proceedButton.setPreferredSize(new Dimension(300, 50)); // Medium size for button

        // Back Button
        backButton = new Button("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.BOLD, 18)); // Medium font for button
        backButton.setPreferredSize(new Dimension(100, 50)); // Medium size for button

        // Panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Adjusted gap between components
        panel.setBackground(new Color(173, 216, 230)); // Sky blue background
        panel.add(destinationLabel);
        panel.add(destinationField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(numberOfTicketsLabel);
        panel.add(numberOfTicketsField);

        // Add Components
        add(enterTicketLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        Panel buttonPanel = new Panel();
        buttonPanel.add(backButton);
        buttonPanel.add(proceedButton);
        buttonPanel.setBackground(new Color(173, 216, 230)); // Sky blue background
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        // Establishing database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            // Insert data into SQL table
            String destination = destinationField.getText();
            String date = dateField.getText();
            int numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ticketdet (destination, date, noticket) VALUES (?, ?, ?)");
                statement.setString(1, destination);
                statement.setString(2, date);
                statement.setInt(3, numberOfTickets);
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Navigate to payment gateway page
            new PaymentGatewayPage(destination, date, numberOfTickets);
            dispose(); // Close ticket details page
        } else if (e.getSource() == backButton) {
            // Navigate back to login page
            new ETicketingProject();
            dispose(); // Close ticket details page
        }
    }

    public static void main(String[] args) {
        new TicketDetailsPage();
    }
}
