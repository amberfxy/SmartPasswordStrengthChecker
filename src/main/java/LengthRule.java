public class LengthRule implements PasswordRule {
  private final int minLength;

  public LengthRule(int minLength) {
    this.minLength = minLength;
  }

  @Override
  public boolean validate(String password) {
    return password != null && password.length() >= minLength;
  }

  @Override
  public String getFeedback(String password) {
    if (!validate(password)) {
      return "Password should be at least " + minLength + " characters long.";
    }
    return null;
  }
}