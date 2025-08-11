public interface PasswordRule {
  boolean validate(String password);
  String getFeedback(String password);
}