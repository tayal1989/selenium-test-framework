package singletonpattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPattern {

    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        driver = WebDriverManager.getInstance(browser).getDriver();
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.co.in");
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Google");
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quitBrowser();
    }
}
