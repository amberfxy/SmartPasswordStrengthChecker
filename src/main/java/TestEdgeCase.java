public class TestEdgeCase {
    public static void main(String[] args) {
        PasswordAnalyzer analyzer = new PasswordAnalyzer();
        
        System.out.println("Testing edge case: 12345678");
        PasswordStrengthFeedback result = analyzer.analyze("12345678");
        System.out.println("Strength: " + result.getStrength());
        System.out.println("Feedback: " + result.getFeedback());
        
        System.out.println("\nTesting: Password123!");
        result = analyzer.analyze("Password123!");
        System.out.println("Strength: " + result.getStrength());
        System.out.println("Feedback: " + result.getFeedback());
    }
}
