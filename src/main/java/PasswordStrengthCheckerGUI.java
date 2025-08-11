import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * VIEW COMPONENT of MVC Pattern
 * 
 * This class represents the View in the MVC architecture.
 * It handles all user interface elements and user interactions.
 * The View is responsible for:
 * - Displaying the GUI components
 * - Capturing user input
 * - Updating the display based on data from the Controller
 * - Delegating user actions to the Controller
 */
public class PasswordStrengthCheckerGUI extends JFrame implements GUI {
    private final JTextField passwordField;
    private final JLabel strengthLabel;
    private final JTextArea feedbackArea;
    public PasswordStrengthController controller; // Reference to Controller

    public PasswordStrengthCheckerGUI(PasswordStrengthController controller) {
        this.controller = controller;
        setTitle("Smart Password Strength Checker - MVC Implementation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create GUI components
        JLabel prompt = new JLabel("Enter your password:");
        prompt.setFont(new Font("Arial", Font.BOLD, 14));
        
        passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        
        strengthLabel = new JLabel("Strength: ");
        strengthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        feedbackArea = new JTextArea(8, 40);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Arial", Font.PLAIN, 12));
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);

        // Layout components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.add(prompt);
        topPanel.add(passwordField);

        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(strengthLabel);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        JLabel feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 12));
        bottomPanel.add(feedbackLabel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // MVC: View delegates user input to Controller
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controller != null) {
                    // Delegate user action to Controller
                    controller.onPasswordChanged(passwordField.getText());
                }
            }
        });
    }

    /**
     * MVC: View method to update display based on data from Controller
     * @param strength the password strength to display
     */
    @Override
    public void updateStrength(String strength) {
        strengthLabel.setText("Strength: " + strength);
        switch (strength) {
            case "Strong":
                strengthLabel.setForeground(new Color(0, 150, 0)); // Green
                break;
            case "Medium":
                strengthLabel.setForeground(new Color(255, 140, 0)); // Orange
                break;
            default:
                strengthLabel.setForeground(new Color(220, 20, 60)); // Red
        }
    }

    /**
     * MVC: View method to update display based on data from Controller
     * @param feedback the feedback messages to display
     */
    @Override
    public void updateFeedback(List<String> feedback) {
        if (feedback.isEmpty()) {
            feedbackArea.setText("Great! Your password meets all requirements.");
        } else {
            feedbackArea.setText(String.join("\n• ", feedback));
        }
    }
}
