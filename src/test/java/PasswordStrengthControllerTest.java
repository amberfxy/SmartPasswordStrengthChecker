import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the PasswordStrengthController class (Controller component).
 * Tests the MVC controller logic and coordination between Model and View.
 */
public class PasswordStrengthControllerTest {
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
    void testControllerWithValidPassword() {
        controller.onPasswordChanged("Str0ng!Passw0rd");
        
        assertEquals("Strong", mockGUI.getLastStrength());
        assertTrue(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testControllerWithWeakPassword() {
        controller.onPasswordChanged("password");
        
        assertEquals("Weak", mockGUI.getLastStrength());
        assertFalse(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testControllerWithNullPassword() {
        controller.onPasswordChanged(null);
        
        assertEquals("Weak", mockGUI.getLastStrength());
        assertFalse(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testControllerWithEmptyPassword() {
        controller.onPasswordChanged("");
        
        assertEquals("Weak", mockGUI.getLastStrength());
        assertFalse(mockGUI.getLastFeedback().isEmpty());
    }

    @Test
    void testControllerSetGui() {
        MockGUI newGUI = new MockGUI();
        controller.setGui(newGUI);
        
        controller.onPasswordChanged("test");
        assertEquals("Weak", newGUI.getLastStrength());
    }

    @Test
    void testControllerWithNullGui() {
        controller.setGui(null);
        
        // Should not throw exception
        assertDoesNotThrow(() -> controller.onPasswordChanged("test"));
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
