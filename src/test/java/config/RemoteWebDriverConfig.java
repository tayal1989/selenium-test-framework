package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class RemoteWebDriverConfig {
    
    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
    
    public static WebDriver createRemoteChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        
        // Add Chrome options for better performance in Docker
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        
        try {
            WebDriver driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Hub URL: " + SELENIUM_HUB_URL, e);
        }
    }
    
    public static WebDriver createLocalChromeDriver() {
        // Fallback to local WebDriver using WebDriverManager
        try {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            
            WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            System.err.println("❌ Failed to create local Chrome driver: " + e.getMessage());
            return null;
        }
    }
    
    public static WebDriver getWebDriver() {
        // Check system property to decide between local and remote
        String useRemote = System.getProperty("selenium.remote", "false");
        
        if ("true".equalsIgnoreCase(useRemote)) {
            System.out.println("🐳 Using Remote WebDriver (Docker Container)");
            return createRemoteChromeDriver();
        } else {
            System.out.println("💻 Using Local WebDriver");
            return createLocalChromeDriver();
        }
    }
}
