import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests for the PasswordStrengthFeedback data class.
 * Tests the data structure that holds password analysis results.
 */
public class PasswordStrengthFeedbackTest {

    @Test
    void testConstructorAndGetters() {
        List<String> feedback = Arrays.asList("Too short", "Missing uppercase");
        PasswordStrengthFeedback result = new PasswordStrengthFeedback("Weak", feedback);
        
        assertEquals("Weak", result.getStrength());
        assertEquals(feedback, result.getFeedback());
    }

    @Test
    void testEmptyFeedback() {
        List<String> feedback = Arrays.asList();
        PasswordStrengthFeedback result = new PasswordStrengthFeedback("Strong", feedback);
        
        assertEquals("Strong", result.getStrength());
        assertTrue(result.getFeedback().isEmpty());
    }

    @Test
    void testMultipleFeedbackItems() {
        List<String> feedback = Arrays.asList("Too short", "Missing uppercase", "Common pattern");
        PasswordStrengthFeedback result = new PasswordStrengthFeedback("Weak", feedback);
        
        assertEquals("Weak", result.getStrength());
        assertEquals(3, result.getFeedback().size());
        assertTrue(result.getFeedback().contains("Too short"));
        assertTrue(result.getFeedback().contains("Missing uppercase"));
        assertTrue(result.getFeedback().contains("Common pattern"));
    }

    @Test
    void testDifferentStrengths() {
        PasswordStrengthFeedback weak = new PasswordStrengthFeedback("Weak", Arrays.asList("Issue"));
        PasswordStrengthFeedback medium = new PasswordStrengthFeedback("Medium", Arrays.asList("Issue"));
        PasswordStrengthFeedback strong = new PasswordStrengthFeedback("Strong", Arrays.asList());
        
        assertEquals("Weak", weak.getStrength());
        assertEquals("Medium", medium.getStrength());
        assertEquals("Strong", strong.getStrength());
    }

    @Test
    void testFeedbackImmutability() {
        List<String> originalFeedback = new ArrayList<>(Arrays.asList("Original"));
        PasswordStrengthFeedback result = new PasswordStrengthFeedback("Weak", originalFeedback);
        
        List<String> retrievedFeedback = result.getFeedback();
        
        // Test that modifying the retrieved list doesn't affect the original
        // Note: The current implementation may allow modification, so we test the actual behavior
        try {
            retrievedFeedback.add("Modified");
            // If we can modify, verify the original is unchanged
            assertEquals(1, result.getFeedback().size());
            assertFalse(result.getFeedback().contains("Modified"));
        } catch (UnsupportedOperationException e) {
            // If list is unmodifiable, that's also acceptable
            assertTrue(true, "List is properly protected from modification");
        }
    }
}
