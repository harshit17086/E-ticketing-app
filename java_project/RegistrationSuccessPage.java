import java.awt.*;
import java.awt.event.*;

public class RegistrationSuccessPage extends Frame implements ActionListener {
    Button okButton; // Change backButton to okButton

    RegistrationSuccessPage(String message) {
        // Registration Success Page
        setTitle("Registration Success");
        setSize(400, 200);
        setLayout(new BorderLayout()); // Use BorderLayout for main panel
        setLocationRelativeTo(null);
        
        // Components
        Label successLabel = new Label(message);
        successLabel.setForeground(Color.green);
        successLabel.setAlignment(Label.CENTER); // Center align success message

        // Add Components
        add(successLabel, BorderLayout.CENTER);

        // OK Button
        okButton = new Button("OK");
        okButton.addActionListener(this);
        okButton.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Larger font for button
        okButton.setPreferredSize(new Dimension(100, 40)); // Larger OK button
        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(Color.lightGray); // Light gray background
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            new ETicketingProject(); // Go back to the login page
            dispose(); // Close registration success page
        }
    }

    public static void main(String[] args) {
        new RegistrationSuccessPage("Registration successful");
    }
}
