import java.util.Collections;
import java.util.List;

public class PasswordStrengthFeedback {
  private final String strength;
  private final List<String> feedback;

  public PasswordStrengthFeedback(String strength, List<String> feedback) {
    this.strength = strength;
    // Defensive copy and make unmodifiable
    this.feedback = Collections.unmodifiableList(feedback == null ? List.of() : List.copyOf(feedback));
  }

  public String getStrength() {
    return strength;
  }

  public List<String> getFeedback() {
    return feedback;
  }
}