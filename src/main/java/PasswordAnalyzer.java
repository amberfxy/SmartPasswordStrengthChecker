import java.util.ArrayList;
import java.util.List;

/**
 * MODEL COMPONENT of MVC Pattern
 * 
 * This class represents the Model in the MVC architecture.
 * It contains the business logic for password analysis and validation.
 * The Model is responsible for:
 * - Storing and managing password validation rules
 * - Performing password strength analysis
 * - Providing data to the Controller
 */
public class PasswordAnalyzer {
    private final List<PasswordRule> rules;

    public PasswordAnalyzer() {
        rules = new ArrayList<>();
        rules.add(new LengthRule(8));
        rules.add(new CharacterVarietyRule());
        rules.add(new CommonPatternRule());
    }

    /**
     * Core business logic method that analyzes password strength
     * @param password the password to analyze
     * @return PasswordStrengthFeedback object with analysis results
     */
    public PasswordStrengthFeedback analyze(String password) {
        List<String> feedback = new ArrayList<>();
        int passed = 0;
        
        // Apply all validation rules
        for (PasswordRule rule : rules) {
            if (rule.validate(password)) {
                passed++;
            } else {
                String fb = rule.getFeedback(password);
                if (fb != null) feedback.add(fb);
            }
        }
        
        // Determine strength based on passed rules
        String strength;
        if (passed == rules.size()) strength = "Strong";
        else if (passed >= rules.size() - 1) strength = "Medium";
        else strength = "Weak";
        
        return new PasswordStrengthFeedback(strength, feedback);
    }
}
