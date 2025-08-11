public class CommonPatternRule implements PasswordRule {
  private static final String[] COMMON_PATTERNS = {
      "password", "123456", "qwerty", "letmein", "admin"
  };

  @Override
  public boolean validate(String password) {
    if (password == null) return false;
    String lower = password.toLowerCase();
    for (String pattern : COMMON_PATTERNS) {
      if (lower.contains(pattern)) return false;
    }
    return true;
  }

  @Override
  public String getFeedback(String password) {
    if (!validate(password)) {
      return "Password contains a common pattern or word.";
    }
    return null;
  }
}