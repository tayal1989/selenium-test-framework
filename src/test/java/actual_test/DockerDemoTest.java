package actual_test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import config.RemoteWebDriverConfig;

public class DockerDemoTest {
    
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        driver = RemoteWebDriverConfig.getWebDriver();
        if (driver == null) {
            Assert.fail("❌ WebDriver initialization failed");
        }
    }
    
    @Test
    public void testDockerSeleniumBasic() {
        System.out.println("🐳 === Docker Selenium Demo Test ===");
        
        // Test 1: Navigate to a simple HTML page
        driver.get("data:text/html,<html><head><title>Docker Selenium Test</title></head><body><h1 id='header'>Hello Docker Selenium!</h1><p>This test is running in a Docker container.</p></body></html>");
        
        // Verify title
        String title = driver.getTitle();
        System.out.println("📄 Page Title: " + title);
        Assert.assertEquals(title, "Docker Selenium Test", "Title should match");
        
        // Find header element
        WebElement header = driver.findElement(By.id("header"));
        String headerText = header.getText();
        System.out.println("📝 Header Text: " + headerText);
        Assert.assertEquals(headerText, "Hello Docker Selenium!", "Header text should match");
        
        System.out.println("✅ Basic Docker Selenium test passed!");
    }
    
    @Test
    public void testDockerSeleniumFeatures() {
        System.out.println("🔧 === Testing Docker Selenium Features ===");
        
        // Test browser capabilities
        driver.get("data:text/html,<html><head><title>Feature Test</title></head><body><h1>Testing Browser Features</h1><input id='textbox' type='text' placeholder='Type here...'><button id='btn' onclick='document.getElementById(\"result\").innerHTML=\"Button Clicked!\"'>Click Me</button><div id='result'></div></body></html>");
        
        // Test text input
        WebElement textbox = driver.findElement(By.id("textbox"));
        textbox.sendKeys("Docker Selenium Test");
        System.out.println("⌨️  Text input successful");
        
        // Test button click
        WebElement button = driver.findElement(By.id("btn"));
        button.click();
        System.out.println("🖱️  Button click successful");
        
        // Test dynamic content
        WebElement result = driver.findElement(By.id("result"));
        String resultText = result.getText();
        System.out.println("📊 Result: " + resultText);
        Assert.assertEquals(resultText, "Button Clicked!", "Button click should update result");
        
        System.out.println("✅ All Docker Selenium features working!");
    }
    
    @Test
    public void testBrowserInfo() {
        System.out.println("🔍 === Browser Information ===");
        
        // Navigate to a page that shows browser info
        driver.get("data:text/html,<html><head><title>Browser Info</title></head><body><h1>Browser Information</h1><script>document.body.innerHTML += '<p>User Agent: ' + navigator.userAgent + '</p>'; document.body.innerHTML += '<p>Platform: ' + navigator.platform + '</p>'; document.body.innerHTML += '<p>Language: ' + navigator.language + '</p>';</script></body></html>");
        
        // Wait for JavaScript to execute
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String pageSource = driver.getPageSource();
        System.out.println("🌐 Browser running in Docker container");
        System.out.println("📱 Platform detected in page: " + (pageSource.contains("Linux") ? "Linux ✅" : "Other"));
        
        Assert.assertTrue(pageSource.contains("Chrome"), "Should be running Chrome browser");
        System.out.println("✅ Browser info verification complete!");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Docker Selenium session closed");
        }
    }
}
