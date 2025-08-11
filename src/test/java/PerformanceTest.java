import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

/**
 * Performance tests for the password analyzer.
 * Tests performance with various input sizes and scenarios.
 */
public class PerformanceTest {
    private PasswordAnalyzer analyzer;
    private Random random;

    @BeforeEach
    void setUp() {
        analyzer = new PasswordAnalyzer();
        random = new Random(42); // Fixed seed for reproducible tests
    }

    @Test
    void testPerformanceWithShortPasswords() {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            analyzer.analyze("short");
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Should complete 1000 analyses in reasonable time (less than 1 second)
        assertTrue(duration < 1000, "Performance test failed: " + duration + "ms for 1000 short passwords");
    }

    @Test
    void testPerformanceWithLongPasswords() {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            analyzer.analyze("VeryLongPasswordWithManyCharacters123!@#");
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Should complete 1000 analyses in reasonable time (less than 1 second)
        assertTrue(duration < 1000, "Performance test failed: " + duration + "ms for 1000 long passwords");
    }

    @Test
    void testPerformanceWithMixedPasswords() {
        String[] passwords = {
            "short", "Password123!", "Str0ng!Passw0rd", "verylongpasswordwithmanycharacters123!@#",
            "", null, "12345678", "qwerty", "admin", "password123"
        };
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            String password = passwords[i % passwords.length];
            analyzer.analyze(password);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Should complete 1000 analyses in reasonable time (less than 1 second)
        assertTrue(duration < 1000, "Performance test failed: " + duration + "ms for 1000 mixed passwords");
    }

    @Test
    void testMemoryUsage() {
        // Test that memory usage doesn't grow excessively
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        
        for (int i = 0; i < 10000; i++) {
            analyzer.analyze("TestPassword" + i + "!");
        }
        
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = finalMemory - initialMemory;
        
        // Memory increase should be reasonable (less than 10MB)
        assertTrue(memoryIncrease < 10 * 1024 * 1024, 
            "Memory usage increased too much: " + memoryIncrease + " bytes");
    }

    @Test
    void testConcurrentAccess() {
        // Test that the analyzer can handle concurrent access
        Thread[] threads = new Thread[10];
        
        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    analyzer.analyze("Password" + threadId + j + "!");
                }
            });
        }
        
        long startTime = System.currentTimeMillis();
        
        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                fail("Thread interrupted: " + e.getMessage());
            }
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Should complete concurrent analysis in reasonable time (less than 2 seconds)
        assertTrue(duration < 2000, "Concurrent performance test failed: " + duration + "ms");
    }
}
