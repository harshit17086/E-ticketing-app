import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ETicketingProject extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton, registerButton;
    Connection connection;

    ETicketingProject() {
        setBackground(Color.red); // Change background color to red
        // Main Panel
        Panel mainPanel = new Panel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(143, 232, 141)); // Changed the background color here

        // Headline Panel
        Panel headlinePanel = new Panel();
        headlinePanel.setBackground(Color.blue); // Changed the background color here
        Label headlineLabel = new Label("Welcome to E-Ticketing System");
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headlinePanel.add(headlineLabel);
        mainPanel.add(headlinePanel, BorderLayout.NORTH);

        // Login Box Panel
        Panel loginBoxPanel = new Panel();
        loginBoxPanel.setLayout(new GridLayout(5, 1));
        loginBoxPanel.setBackground(Color.white);

        // Components
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 18);
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(labelFont);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(labelFont);
        usernameField = new TextField(30);
        usernameField.setFont(textFont);
        passwordField = new TextField(30);
        passwordField.setFont(textFont);
        passwordField.setEchoChar('*');
        loginButton = new Button("Login");
        registerButton = new Button("Register");
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setBackground(Color.blue);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Add Components to Login Box Panel
        loginBoxPanel.add(panel);
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        loginButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        loginButton.setPreferredSize(new Dimension(200, 50));
        registerButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setBackground(Color.blue);
        registerButton.setBackground(Color.blue);
        loginButton.setForeground(Color.white);
        registerButton.setForeground(Color.white);
        loginButton.setFocusable(false);
registerButton.setFocusable(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.setBackground(Color.blue);
        loginBoxPanel.add(buttonPanel);
        mainPanel.add(loginBoxPanel, BorderLayout.CENTER);

        add(mainPanel);

        setExtendedState(Frame.MAXIMIZED_BOTH); // Set frame to fullscreen
        setResizable(false);
        setLocationRelativeTo(null);
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
        if (e.getSource() == loginButton) {
            // Perform login authentication
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                // Query the database for the given username and password
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM userdata WHERE Username = ? AND Password = ?");
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // Successful login, move to the next page
                    new TicketDetailsPage();
                    dispose(); // Close login page
                } else {
                    // Show error message for invalid username or password
                    new ErrorMessage("Invalid username or password", this);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == registerButton) {
            // Open registration page
            new RegistrationPage();
            dispose(); // Close login page
        }
    }

    public static void main(String[] args) {
        new ETicketingProject();
    }
}

class ErrorMessage extends Frame {
    ErrorMessage(String message, ETicketingProject parentFrame) {
        setTitle("Error");
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label errorMessageLabel = new Label(message);
        add(errorMessageLabel, BorderLayout.CENTER);

        // Back Button
        Button backButton = new Button("Back");
        backButton.addActionListener(e -> {
            dispose(); // Close error message frame
            parentFrame.setVisible(true); // Show login page again
        });
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
