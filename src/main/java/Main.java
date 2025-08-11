import javax.swing.SwingUtilities;

/**
 * Main class to launch the Smart Password Strength Checker application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Create components
                PasswordAnalyzer analyzer = new PasswordAnalyzer();
                PasswordStrengthController controller = new PasswordStrengthController(analyzer, null);
                PasswordStrengthCheckerGUI gui = new PasswordStrengthCheckerGUI(controller);
                
                // Update the controller with the GUI reference
                controller.setGui(gui);
                
                // Make GUI visible
                gui.setVisible(true);
                
                System.out.println("Smart Password Strength Checker started successfully!");
                
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
