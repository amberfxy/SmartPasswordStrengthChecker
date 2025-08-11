import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Tests for the PasswordAnalyzer class (Model component).
 * Tests the core business logic for password strength analysis.
 */
public class PasswordAnalyzerTest {
    private PasswordAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new PasswordAnalyzer();
    }

    @Test
    void testStrongPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("Str0ng!Passw0rd");
        assertEquals("Strong", feedback.getStrength());
        assertTrue(feedback.getFeedback().isEmpty(), "Strong password should have no feedback");
    }

    @Test
    void testMediumPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("Password123!");
        assertEquals("Medium", feedback.getStrength());
        assertFalse(feedback.getFeedback().isEmpty(), "Medium password should have some feedback");
    }

    @Test
    void testWeakPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("password");
        assertEquals("Weak", feedback.getStrength());
        assertFalse(feedback.getFeedback().isEmpty(), "Weak password should have feedback");
    }

    @Test
    void testNullPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze(null);
        assertEquals("Weak", feedback.getStrength());
        assertFalse(feedback.getFeedback().isEmpty(), "Null password should have feedback");
    }

    @Test
    void testEmptyPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("");
        assertEquals("Weak", feedback.getStrength());
        assertFalse(feedback.getFeedback().isEmpty(), "Empty password should have feedback");
    }

    @Test
    void testShortPassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("abc123");
        assertEquals("Weak", feedback.getStrength());
        assertTrue(feedback.getFeedback().stream().anyMatch(f -> f.contains("8 characters")), 
            "Should mention length requirement");
    }

    @Test
    void testPasswordWithoutVariety() {
        PasswordStrengthFeedback feedback = analyzer.analyze("password123");
        assertEquals("Weak", feedback.getStrength());
        assertTrue(feedback.getFeedback().stream().anyMatch(f -> f.contains("upper and lower case")), 
            "Should mention character variety");
    }

    @Test
    void testPasswordWithCommonPattern() {
        PasswordStrengthFeedback feedback = analyzer.analyze("mypassword123");
        assertEquals("Weak", feedback.getStrength());
        assertTrue(feedback.getFeedback().stream().anyMatch(f -> f.contains("common pattern")), 
            "Should mention common pattern");
    }

    @Test
    void testMultipleIssues() {
        PasswordStrengthFeedback feedback = analyzer.analyze("pass");
        assertEquals("Weak", feedback.getStrength());
        assertTrue(feedback.getFeedback().size() > 1, "Should have multiple feedback items");
    }

    @Test
    void testEdgeCasePassword() {
        PasswordStrengthFeedback feedback = analyzer.analyze("12345678");
        assertEquals("Weak", feedback.getStrength()); // Fixed: 12345678 is Weak due to common pattern
        assertFalse(feedback.getFeedback().isEmpty(), "Should have some feedback");
    }

    @Test
    void testSpecialCharactersOnly() {
        PasswordStrengthFeedback feedback = analyzer.analyze("!@#$%^&*");
        assertEquals("Medium", feedback.getStrength());
        assertFalse(feedback.getFeedback().isEmpty(), "Should have some feedback");
    }
}
