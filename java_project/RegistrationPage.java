import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage extends Frame implements ActionListener {
    TextField usernameField, passwordField, confirmPasswordField;
    Button registerButton, backButton; // Added backButton

    Connection connection;

    RegistrationPage() {
        // Establishing database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Registration Page
        setTitle("E-Ticketing Registration");
        setSize(600, 400); // Set size similar to the login page
        setLayout(new BorderLayout()); // Use BorderLayout for main panel
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(new Color(173, 216, 230)); // Sky blue background
        setExtendedState(Frame.MAXIMIZED_BOTH); // Set to full screen

        // Headline Panel
        Panel headlinePanel = new Panel();
        headlinePanel.setBackground(Color.green);
        Label headlineLabel = new Label("Register for E-Ticketing System");
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Larger title font
        headlinePanel.add(headlineLabel);
        add(headlinePanel, BorderLayout.NORTH);

        // Input Panel
        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5)); // Adjusted gap between components
        inputPanel.setBackground(new Color(255 ,182,193)); // Sky blue background
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Label font
        Font textFont = new Font("Arial", Font.PLAIN, 16); // Text field font
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(labelFont);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(labelFont);
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        usernameField = new TextField(20); // Reduced text field size
        usernameField.setFont(textFont);
        passwordField = new TextField(20);
        passwordField.setFont(textFont);
        confirmPasswordField = new TextField(20);
        confirmPasswordField.setFont(textFont);
        confirmPasswordField.setEchoChar('*');
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(confirmPasswordLabel);
        inputPanel.add(confirmPasswordField);
        add(inputPanel, BorderLayout.CENTER);

        // Register Button
        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(new Color(254, 216, 177)); // Sky blue background
        registerButton = new Button("Register");
        registerButton.addActionListener(this);
        registerButton.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font for button
        registerButton.setBackground(Color.blue);
        registerButton.setForeground(Color.white);
        buttonPanel.add(registerButton);

        // Back Button
        backButton = new Button("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font for button
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            // Perform registration
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                try {
                    // Inserting data into the database
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO userdata (Username, Password, CoPass) VALUES (?, ?, ?)");
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, confirmPassword);
                    statement.executeUpdate();
                    new RegistrationSuccessPage("Registration successful");
                    dispose(); // Close registration page
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Passwords don't match, show error dialog
                //JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) { // Handle back button action
            new ETicketingProject(); // Go back to the main page
            dispose(); // Close registration page
        }
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }
}
