import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the complete MVC system.
 * Tests the interaction between all components working together.
 */
public class IntegrationTest {
    private PasswordAnalyzer analyzer;
    private MockGUI mockGUI;
    private PasswordStrengthController controller;

    @BeforeEach
    void setUp() {
        analyzer = new PasswordAnalyzer();
        mockGUI = new MockGUI();
        controller = new PasswordStrengthController(analyzer, mockGUI);
    }

    @Test
    void testCompleteMVCFlow() {
        // Test the complete flow from user input to display update
        controller.onPasswordChanged("Str0ng!Passw0rd");
        
        // Verify Model processed correctly
        PasswordStrengthFeedback directResult = analyzer.analyze("Str0ng!Passw0rd");
        assertEquals("Strong", directResult.getStrength());
        
        // Verify Controller updated View correctly
        assertEquals("Strong", mockGUI.getLastStrength());
        assertTrue(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testCompleteMVCWithWeakPassword() {
        controller.onPasswordChanged("weak");
        
        // Verify Model processed correctly
        PasswordStrengthFeedback directResult = analyzer.analyze("weak");
        assertEquals("Weak", directResult.getStrength());
        
        // Verify Controller updated View correctly
        assertEquals("Weak", mockGUI.getLastStrength());
        assertFalse(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testMultiplePasswordChanges() {
        // Test multiple password changes
        controller.onPasswordChanged("weak");
        assertEquals("Weak", mockGUI.getLastStrength());
        
        controller.onPasswordChanged("Password123!");
        assertEquals("Medium", mockGUI.getLastStrength());
        
        controller.onPasswordChanged("Str0ng!Passw0rd");
        assertEquals("Strong", mockGUI.getLastStrength());
    }

    @Test
    void testEdgeCases() {
        // Test various edge cases
        controller.onPasswordChanged("");
        assertEquals("Weak", mockGUI.getLastStrength());
        
        controller.onPasswordChanged(null);
        assertEquals("Weak", mockGUI.getLastStrength());
        
        controller.onPasswordChanged("12345678");
        assertEquals("Weak", mockGUI.getLastStrength()); // Fixed: 12345678 is Weak due to common pattern
    }

    // Mock GUI class for testing
    private static class MockGUI implements GUI {
        private String lastStrength;
        private java.util.List<String> lastFeedback;

        @Override
        public void updateStrength(String strength) {
            this.lastStrength = strength;
        }

        @Override
        public void updateFeedback(java.util.List<String> feedback) {
            this.lastFeedback = feedback;
        }

        public String getLastStrength() {
            return lastStrength;
        }

        public java.util.List<String> getLastFeedback() {
            return lastFeedback;
        }
    }
}
