package factorypattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome").createDriver();
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
    }

    @Test
    public void testLoginTitle() {
        Assert.assertEquals(driver.getTitle(), "Account Login");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
