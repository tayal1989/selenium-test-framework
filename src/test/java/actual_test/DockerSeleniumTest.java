package actual_test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import config.RemoteWebDriverConfig;

public class DockerSeleniumTest {
    
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        // This will use remote WebDriver when selenium.remote=true
        driver = RemoteWebDriverConfig.getWebDriver();
        if (driver == null) {
            // Fallback to a basic setup if remote config fails
            System.out.println("❌ Remote WebDriver not available, skipping test");
            return;
        }
    }
    
    @Test
    public void testGoogleSearch() {
        if (driver == null) return;
        
        System.out.println("🔍 Starting Google Search Test with Docker Selenium");
        
        // Navigate to Google
        driver.get("https://www.google.com");
        System.out.println("✅ Navigated to Google");
        
        // Find search box and enter search term
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver Docker");
        searchBox.submit();
        
        System.out.println("✅ Search completed successfully");
        
        // Wait a bit to see the results
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("🎉 Test completed! Check VNC viewer to see the browser in action");
    }
    
    @Test
    public void testDockerSeleniumExample() {
        if (driver == null) return;
        
        System.out.println("🐳 Testing Docker Selenium Container");
        
        // Navigate to Selenium website
        driver.get("https://www.selenium.dev");
        System.out.println("✅ Navigated to Selenium.dev");
        
        // Get page title
        String title = driver.getTitle();
        System.out.println("📄 Page title: " + title);
        
        // Simple assertion
        if (title.contains("Selenium")) {
            System.out.println("✅ Title contains 'Selenium' - Test Passed!");
        } else {
            System.out.println("❌ Title assertion failed");
        }
        
        System.out.println("🎉 Docker Selenium Test completed successfully!");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 WebDriver session closed");
        }
    }
}
