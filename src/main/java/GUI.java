import java.util.List;

/**
 * Interface for GUI components in the MVC pattern.
 * This allows for easy testing with mock implementations.
 */
public interface GUI {
    void updateStrength(String strength);
    void updateFeedback(List<String> feedback);
}
