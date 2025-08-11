/**
 * Simple test to verify the password analyzer works.
 */
public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("Testing Smart Password Strength Checker...");
        
        PasswordAnalyzer analyzer = new PasswordAnalyzer();
        
        // Test weak password
        PasswordStrengthFeedback result1 = analyzer.analyze("test123");
        System.out.println("Password: test123");
        System.out.println("Strength: " + result1.getStrength());
        System.out.println("Feedback: " + result1.getFeedback());
        System.out.println();
        
        // Test strong password
        PasswordStrengthFeedback result2 = analyzer.analyze("Str0ng!Passw0rd");
        System.out.println("Password: Str0ng!Passw0rd");
        System.out.println("Strength: " + result2.getStrength());
        System.out.println("Feedback: " + result2.getFeedback());
        System.out.println();
        
        System.out.println("Test completed successfully!");
    }
}
