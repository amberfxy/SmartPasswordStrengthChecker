public class CharacterVarietyRule implements PasswordRule {
  @Override
  public boolean validate(String password) {
    if (password == null) return false;
    boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
    for (char c : password.toCharArray()) {
      if (Character.isUpperCase(c)) hasUpper = true;
      else if (Character.isLowerCase(c)) hasLower = true;
      else if (Character.isDigit(c)) hasDigit = true;
      else hasSpecial = true;
    }
    return hasUpper && hasLower && hasDigit && hasSpecial;
  }

  @Override
  public String getFeedback(String password) {
    if (!validate(password)) {
      return "Password should include upper and lower case letters, digits, and special characters.";
    }
    return null;
  }
}