package factorypattern;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public static BrowserDriver getDriver(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriverManager();

            case "firefox":
                return new FirefoxDriverManager();

            default:
                throw new IllegalArgumentException("Incompatible browser type : " + browserType);
        }
    }
}
