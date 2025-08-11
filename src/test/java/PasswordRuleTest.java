import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the PasswordRule interface implementations.
 * Tests all password validation rules: LengthRule, CharacterVarietyRule, CommonPatternRule.
 */
public class PasswordRuleTest {
    private LengthRule lengthRule;
    private CharacterVarietyRule varietyRule;
    private CommonPatternRule patternRule;

    @BeforeEach
    void setUp() {
        lengthRule = new LengthRule(8);
        varietyRule = new CharacterVarietyRule();
        patternRule = new CommonPatternRule();
    }

    // LengthRule Tests
    @Test
    void testLengthRuleValidPassword() {
        assertTrue(lengthRule.validate("password123"));
        assertNull(lengthRule.getFeedback("password123"));
    }

    @Test
    void testLengthRuleInvalidPassword() {
        assertFalse(lengthRule.validate("short"));
        assertNotNull(lengthRule.getFeedback("short"));
        assertTrue(lengthRule.getFeedback("short").contains("8 characters"));
    }

    @Test
    void testLengthRuleNullPassword() {
        assertFalse(lengthRule.validate(null));
        assertNotNull(lengthRule.getFeedback(null));
    }

    @Test
    void testLengthRuleEmptyPassword() {
        assertFalse(lengthRule.validate(""));
        assertNotNull(lengthRule.getFeedback(""));
    }

    @Test
    void testLengthRuleExactLength() {
        assertTrue(lengthRule.validate("12345678"));
        assertNull(lengthRule.getFeedback("12345678"));
    }

    // CharacterVarietyRule Tests
    @Test
    void testVarietyRuleValidPassword() {
        assertTrue(varietyRule.validate("Password123!"));
        assertNull(varietyRule.getFeedback("Password123!"));
    }

    @Test
    void testVarietyRuleMissingUpperCase() {
        assertFalse(varietyRule.validate("password123!"));
        assertNotNull(varietyRule.getFeedback("password123!"));
    }

    @Test
    void testVarietyRuleMissingLowerCase() {
        assertFalse(varietyRule.validate("PASSWORD123!"));
        assertNotNull(varietyRule.getFeedback("PASSWORD123!"));
    }

    @Test
    void testVarietyRuleMissingDigit() {
        assertFalse(varietyRule.validate("Password!"));
        assertNotNull(varietyRule.getFeedback("Password!"));
    }

    @Test
    void testVarietyRuleMissingSpecialChar() {
        assertFalse(varietyRule.validate("Password123"));
        assertNotNull(varietyRule.getFeedback("Password123"));
    }

    @Test
    void testVarietyRuleNullPassword() {
        assertFalse(varietyRule.validate(null));
        assertNotNull(varietyRule.getFeedback(null));
    }

    // CommonPatternRule Tests
    @Test
    void testPatternRuleValidPassword() {
        assertTrue(patternRule.validate("MySecurePass123!"));
        assertNull(patternRule.getFeedback("MySecurePass123!"));
    }

    @Test
    void testPatternRuleContainsPassword() {
        assertFalse(patternRule.validate("mypassword123"));
        assertNotNull(patternRule.getFeedback("mypassword123"));
    }

    @Test
    void testPatternRuleContains123456() {
        assertFalse(patternRule.validate("user123456"));
        assertNotNull(patternRule.getFeedback("user123456"));
    }

    @Test
    void testPatternRuleContainsQwerty() {
        assertFalse(patternRule.validate("qwerty123"));
        assertNotNull(patternRule.getFeedback("qwerty123"));
    }

    @Test
    void testPatternRuleCaseInsensitive() {
        assertFalse(patternRule.validate("PASSWORD123"));
        assertNotNull(patternRule.getFeedback("PASSWORD123"));
    }

    @Test
    void testPatternRuleNullPassword() {
        assertFalse(patternRule.validate(null));
        assertNotNull(patternRule.getFeedback(null));
    }
}
