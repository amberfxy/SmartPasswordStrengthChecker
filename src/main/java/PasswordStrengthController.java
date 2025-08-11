/**
 * CONTROLLER COMPONENT of MVC Pattern
 * 
 * This class represents the Controller in the MVC architecture.
 * It acts as an intermediary between the Model and View.
 * The Controller is responsible for:
 * - Receiving user input from the View
 * - Processing user requests by calling Model methods
 * - Updating the View with results from the Model
 * - Managing the flow of data between Model and View
 */
public class PasswordStrengthController {
    private final PasswordAnalyzer analyzer; // Reference to Model
    private GUI gui;  // Reference to View (using interface)

    public PasswordStrengthController(PasswordAnalyzer analyzer, GUI gui) {
        this.analyzer = analyzer;
        this.gui = gui;
    }

    /**
     * MVC: Controller method to set the View reference
     * @param gui the GUI view component
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * MVC: Controller method that handles user input from View
     * and coordinates with Model to update View
     * @param password the password entered by user
     */
    public void onPasswordChanged(String password) {
        if (gui != null) {
            // MVC: Controller calls Model to process data
            PasswordStrengthFeedback result = analyzer.analyze(password);
            
            // MVC: Controller updates View with Model results
            gui.updateStrength(result.getStrength());
            gui.updateFeedback(result.getFeedback());
        }
    }
}
